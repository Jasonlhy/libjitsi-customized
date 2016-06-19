package fianlproject.communication.example;

import org.jitsi.service.libjitsi.LibJitsi;

import finalproject.communication.VoiceSenderReceiver;

public class ReceiveVoice {
	public static void main(String[] args) throws Exception {

		String localPort = "9999";
		String remoteAddress = "127.0.0.1";
		String remotePort = "5000";

		LibJitsi.start();
		try {
			VoiceSenderReceiver avReceive = new VoiceSenderReceiver(localPort, remoteAddress, remotePort);
			String result = avReceive.start();
			
			if (result == null) {
				try {
					/*
					 * Wait for the media to be received and played back.
					 * AVTransmit2 transmits for 1 minute so AVReceive2 waits
					 * for 2 minutes to allow AVTransmit2 to start the
					 * tranmission with a bit of a delay (if necessary).
					 */
					long then = System.currentTimeMillis();
					long waitingPeriod = 2 * 60000;

					try {
						while ((System.currentTimeMillis() - then) < waitingPeriod)
							Thread.sleep(1000);
					} catch (InterruptedException ie) {
					}
				} finally {
					avReceive.stop();
				}

				System.err.println("Exiting AVReceive2");
			} else {
				System.err.println("Failed to initialize the sessions.");
			}
		} finally {
			LibJitsi.stop();
		}

	}
}
