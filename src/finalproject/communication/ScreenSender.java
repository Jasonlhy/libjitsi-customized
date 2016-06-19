package finalproject.communication;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.jitsi.impl.neomedia.VideoMediaStreamImpl;
import org.jitsi.impl.neomedia.format.VideoMediaFormatImpl;
import org.jitsi.service.libjitsi.LibJitsi;
import org.jitsi.service.neomedia.DefaultStreamConnector;
import org.jitsi.service.neomedia.MediaDirection;
import org.jitsi.service.neomedia.MediaService;
import org.jitsi.service.neomedia.MediaStream;
import org.jitsi.service.neomedia.MediaStreamTarget;
import org.jitsi.service.neomedia.MediaType;
import org.jitsi.service.neomedia.MediaUseCase;
import org.jitsi.service.neomedia.StreamConnector;
import org.jitsi.service.neomedia.device.MediaDevice;
import org.jitsi.service.neomedia.format.MediaFormat;
import org.jitsi.util.event.VideoEvent;
import org.jitsi.util.event.VideoListener;

public class ScreenSender {

	/**
	 * The port which is the source of the transmission i.e. from which the
	 * media is to be transmitted.
	 *
	 * @see #LOCAL_PORT_BASE_ARG_NAME
	 */
	private int localPortBase;

	/**
	 * The <tt>MediaStream</tt> instances initialized by this instance indexed
	 * by their respective <tt>MediaType</tt> ordinal.
	 */
	private MediaStream mediaStream;

	/**
	 * The <tt>InetAddress</tt> of the host which is the target of the
	 * transmission i.e. to which the media is to be transmitted.
	 *
	 * @see #REMOTE_HOST_ARG_NAME
	 */
	private InetAddress remoteAddr;

	/**
	 * The port which is the target of the transmission i.e. to which the media
	 * is to be transmitted.
	 *
	 * @see #REMOTE_PORT_BASE_ARG_NAME
	 */
	private int remotePortBase;

	/**
	 * Initializes a new <tt>AVTransmit2</tt> instance which is to transmit
	 * audio and video to a specific host and a specific port.
	 *
	 * @param localPortBase
	 *            the port which is the source of the transmission i.e. from
	 *            which the media is to be transmitted
	 * @param remoteHost
	 *            the name of the host which is the target of the transmission
	 *            i.e. to which the media is to be transmitted
	 * @param remotePortBase
	 *            the port which is the target of the transmission i.e. to which
	 *            the media is to be transmitted
	 * @throws Exception
	 *             if any error arises during the parsing of the specified
	 *             <tt>localPortBase</tt>, <tt>remoteHost</tt> and
	 *             <tt>remotePortBase</tt>
	 */
	public ScreenSender(String localPortBase, String remoteHost, String remotePortBase) throws Exception {
		this.localPortBase = (localPortBase == null) ? -1 : Integer.parseInt(localPortBase);
		this.remoteAddr = InetAddress.getByName(remoteHost);
		this.remotePortBase = Integer.parseInt(remotePortBase);
	}

	/**
	 * Starts the transmission. Returns null if transmission started ok.
	 * Otherwise it returns a string with the reason why the setup failed.
	 */
	public String start() throws Exception {
		/*
		 * Prepare for the start of the transmission i.e. initialize the
		 * MediaStream instance.
		 */
		MediaService mediaService = LibJitsi.getMediaService();
		int localPort = localPortBase;
		int remotePort = remotePortBase;

		MediaType mediaType = MediaType.VIDEO;
		MediaDevice device = mediaService.getDefaultDevice(mediaType, MediaUseCase.DESKTOP);
		MediaStream mediaStream = mediaService.createMediaStream(device);
		mediaStream.setDirection(MediaDirection.SENDONLY);

		// format
		String encoding;
		double clockRate;
		byte dynamicRTPPayloadType;

		encoding = "H264";
		clockRate = VideoMediaFormatImpl.DEFAULT_CLOCK_RATE;
		dynamicRTPPayloadType = 99;

		MediaFormat format = mediaService.getFormatFactory().createMediaFormat(encoding, clockRate);
		mediaStream.addDynamicRTPPayloadType(dynamicRTPPayloadType, format);
		mediaStream.setFormat(format);

		// connector
		StreamConnector connector;

		if (localPortBase == -1) {
			connector = new DefaultStreamConnector();
		} else {
			int localRTPPort = localPort++;
			int localRTCPPort = localPort++;

			connector = new DefaultStreamConnector(new DatagramSocket(localRTPPort), new DatagramSocket(localRTCPPort));
		}
		mediaStream.setConnector(connector);

		// target
		/*
		 * The AVTransmit2 and AVReceive2 examples follow the common practice
		 * that the RTCP port is right after the RTP port.
		 */
		int remoteRTPPort = remotePort++;
		int remoteRTCPPort = remotePort++;

		mediaStream.setTarget(new MediaStreamTarget(new InetSocketAddress(remoteAddr, remoteRTPPort),
				new InetSocketAddress(remoteAddr, remoteRTCPPort)));

		mediaStream.setName(mediaType.toString());
		mediaStream.start();
		
		
		this.mediaStream = mediaStream;
		
		final VideoMediaStreamImpl videoStream = (VideoMediaStreamImpl) mediaStream;
		videoStream.addVideoListener(new VideoListener(){

			@Override
			public void videoAdded(VideoEvent event) {
				System.out.println("Video add");
			}

			@Override
			public void videoRemoved(VideoEvent event) {
				System.out.println("video removed");
			}

			@Override
			public void videoUpdate(VideoEvent event) {
				System.out.println("video update");
			}
			
		});

		return null;
	}

	/**
	 * Stops the transmission if already started
	 */
	public void stop() {
		mediaStream.close();
	}

	

}
