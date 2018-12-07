package BDA;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
 * The Class PostGui.
 */
public class PostGui  {

	/** The frame. */
	private JFrame frame;
	
	/** The facebookhandler. */
	private FacebookAPI facebookhandler;
	
	/**
	 * Instantiates a new post gui.
	 *
	 * @param frameTitle the frame title
	 */
	public PostGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(400, 100);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addContent();
	}

	
	/**
	 * Adds the content.
	 */
	public void addContent() {
		
		facebookhandler = new FacebookAPI();
		frame.setLayout(new BorderLayout());
		
		JTextField postText = new JTextField();

		JButton postButton = new JButton("Post !!!");
		postButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				facebookhandler.post(postText.getText());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		frame.add(postText, BorderLayout.CENTER);
		frame.add(postButton, BorderLayout.PAGE_END);

	}

	/**
	 * Open.
	 */
	public void open() {
		frame.setVisible(true);
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		PostGui gui = new PostGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent();
		gui.open();
	}

}
