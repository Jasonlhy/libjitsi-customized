package finalproject.communication;

/**
 * Call back used to notify the sender that receiver has actively stopped the sharing
 * It is used by the internal video frame
 * 
 * It the sharing is stopped by receiver
 * The receiver will close the socket itself
 * And notify the opponent to close the socket
 * 
 * If the sharing is stopped by sender
 * The sender will close the socket itself
 * The receiver will close its socket in video removed event
 * 
 * @author 
 *
 */

@FunctionalInterface
public interface StoppedSharingCallback {
	public void sharingStopped();
}
