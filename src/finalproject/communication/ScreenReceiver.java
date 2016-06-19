package finalproject.communication;

import java.awt.Component;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
import org.jitsi.util.swing.VideoContainer;


public class ScreenReceiver {
	/**
	 * The port which is the target of the transmission i.e. on which the media
	 * is to be received.
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
	 * The <tt>InetAddress</tt> of the host which is the target of the receipt
	 * i.e. from which the media is to be received.
	 *
	 * @see #REMOTE_HOST_ARG_NAME
	 */
	private InetAddress remoteAddr;

	/**
	 * The port which is the target of the receipt i.e. from which the media is
	 * to be received.
	 *
	 * @see #REMOTE_PORT_BASE_ARG_NAME
	 */
	private int remotePortBase;

	/**
	 * Initializes a new <tt>AVReceive2</tt> instance which is to receive audio
	 * and video from a specific host and a specific port.
	 *
	 * @param localPortBase
	 *            the port on which the audio and video are to be received
	 * @param remoteHost
	 *            the name of the host from which the media is transmitted
	 * @param remotePortBase
	 *            the port from which the media is transmitted
	 * @throws Exception
	 *             if any error arises during the parsing of the specified
	 *             <tt>localPortBase</tt>, <tt>remoteHost</tt> and
	 *             <tt>remotePortBase</tt>
	 */
	public ScreenReceiver(String localPortBase, String remoteHost, String remotePortBase) throws Exception {
		this.localPortBase = (localPortBase == null) ? -1 : Integer.parseInt(localPortBase);
		this.remoteAddr = InetAddress.getByName(remoteHost);
		this.remotePortBase = Integer.parseInt(remotePortBase);
	}

	/**
	 * Initializes the receipt of audio and video.
	 *
	 * @return <tt>true</tt> if this instance has been successfully initialized
	 *         to receive audio and video
	 * @throws Exception
	 *             if anything goes wrong while initializing this instance for
	 *             the receipt of audio and video
	 */
	public boolean initialize() throws Exception {
		/*
		 * Prepare for the start of the transmission i.e. initialize the
		 * MediaStream instances.
		 */
		MediaService mediaService = LibJitsi.getMediaService();
		int localPort = localPortBase;
		int remotePort = remotePortBase;

		MediaType mediaType = MediaType.VIDEO;
		MediaDevice device = mediaService.getDefaultDevice(mediaType, MediaUseCase.DESKTOP);
		MediaStream mediaStream = mediaService.createMediaStream(device);
		mediaStream.setDirection(MediaDirection.RECVONLY);

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
		
		// start and record
		mediaStream.start();
		this.mediaStream = mediaStream;

		return true;
	}
	
	/**
	 * OPen the mediaStrea sharing
	 * 
	 */
	public void start(){
		mediaStream.start();
	}
	
	/**
	 * Close the <tt>MediaStream</tt>s.
	 */
	public void close() {
		mediaStream.close();
	}
	
	
	public void addVideoListener(VideoListener listener){
		final VideoMediaStreamImpl videoStream = (VideoMediaStreamImpl) mediaStream;
		videoStream.addVideoListener(listener);
	}
	
	/**
	 * Add simple listener with a video frame with customization of title
	 * And close the socket when it is closed
	 * 
	 * @param title
	 */
	public void addSimpleListener(final String title){
		final VideoMediaStreamImpl videoStream = (VideoMediaStreamImpl) mediaStream;
		final ScreenReceiver reciver = this;
		
		videoStream.addVideoListener(new VideoListener() {

			@Override
			public void videoAdded(VideoEvent event) {
				System.out.println("Video added");
				List<Component> videos = videoStream.getVisualComponents();

				if (!videos.isEmpty()) {
					System.out.println("Found Video!");

					final Component video = videos.get(0);
					System.out.println("video component calss: " + video.getClass());

					VideoFrame videoFrame = new VideoFrame(video);
					videoFrame.setTitle(title);
					// video.setSize(d);
					videoFrame.setVisible(true);
				}
			}

			@Override
			public void videoRemoved(VideoEvent event) {
				System.out.println("The video has been removed");
				JOptionPane.showMessageDialog(null, "分享巳經停止!");
				reciver.close();
			}

			@Override
			public void videoUpdate(VideoEvent event) {
				
			}

		});
	}
	
}
