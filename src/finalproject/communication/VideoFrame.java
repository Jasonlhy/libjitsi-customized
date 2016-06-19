package finalproject.communication;

import java.awt.Component;

import javax.swing.JFrame;

import org.jitsi.util.swing.VideoContainer;

public class VideoFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private VideoContainer vc;

	public VideoFrame(Component video) {
		super("Video");
		vc = new VideoContainer(video, false);
		// vc.setSize(5, 5);
		this.add(vc);
		this.pack();
		// this.setSize(1000, 1000);
	}
}