package project.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ContentGui  {
	private JFrame frame;
	private JTextArea textArea;

	public ContentGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(400, 200);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void addContent()	{
		textArea=new JTextArea("");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textArea);
		frame.add(scroll);

	}
	
	public void changeText(String text)	{
		textArea.setText(text);
	}
	
	public void open() {
		frame.setVisible(true);
	}

	
	public static void main(String[] args) {
		ContentGui content=new ContentGui("BDA(BOM DIA ACADEMIA)");
		content.addContent();
		content.changeText("s");
		content.open();
	}
	
}
