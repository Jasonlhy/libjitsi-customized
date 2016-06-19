package fianlproject.communication.example;

import org.jitsi.service.libjitsi.LibJitsi;

import finalproject.communication.ScreenReceiver;

public class ReceiveScreen {
	public static void main(String[] args) throws Exception {

		String localPort = "9999";
		String remoteAddress = "127.0.0.1";
		String remotePort = "5000";

		LibJitsi.start();
		try {
			ScreenReceiver avReceive = new ScreenReceiver(localPort, remoteAddress, remotePort);

			if (avReceive.initialize()) {
				avReceive.addSimpleListener("GOD");
				avReceive.start();
				
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
					avReceive.close();
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
