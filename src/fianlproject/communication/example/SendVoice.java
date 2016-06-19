package fianlproject.communication.example;

import org.jitsi.service.libjitsi.LibJitsi;

import finalproject.communication.VoiceSenderReceiver;

public class SendVoice {

	public static void main(String[] args) throws Exception{


		String localPort = "5000";
		String remoteAddress = "127.0.0.1";
		String remotePort = "9999";
		LibJitsi.start();
		try {
			// Create a audio transmit object with the specified params.
			VoiceSenderReceiver at = new VoiceSenderReceiver(localPort, remoteAddress, remotePort);
			// Start the transmission
			String result = at.start();

			// result will be non-null if there was an error. The return
			// value is a String describing the possible error. Print it.
			if (result == null) {
				System.err.println("Start transmission for 60 seconds...");

				// Transmit for 60 seconds and then close the processor
				// This is a safeguard when using a capture data source
				// so that the capture device will be properly released
				// before quitting.
				// The right thing to do would be to have a GUI with a
				// "Stop" button that would call stop on AVTransmit2
				try {
					Thread.sleep(60000);
				} catch (InterruptedException ie) {
				}

				// Stop the transmission
				at.stop();

				System.err.println("...transmission ended.");
			} else {
				System.err.println("Error : " + result);
			}
		} finally {
			LibJitsi.stop();
		}

	
	}

}
