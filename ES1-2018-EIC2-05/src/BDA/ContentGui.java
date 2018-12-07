package BDA;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


/**
 * The Class ContentGui.
 */
public class ContentGui  {
	
	/** The frame. */
	private JFrame frame;
	
	/** The text area. */
	private JTextArea textArea;

	/**
	 * Instantiates a new content gui.
	 *
	 * @param frameTitle the frame title
	 */
	public ContentGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(400, 200);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Adds the content.
	 */
	public void addContent()	{
		textArea=new JTextArea("");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textArea);
		frame.add(scroll);

	}
	
	/**
	 * Change text.
	 *
	 * @param text the text
	 */
	public void changeText(String text)	{
		textArea.setText(text);
		textArea.setEditable(false);
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
		ContentGui content=new ContentGui("BDA(BOM DIA ACADEMIA)");
		content.addContent();
		content.changeText("s");
		content.open();
	}
	
}
