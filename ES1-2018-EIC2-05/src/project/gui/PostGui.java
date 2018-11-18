package project.gui;

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


public class PostGui extends MainGui {

	public PostGui(String frameTitle) {
		super(frameTitle);
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
	 * Janela onde vamos pesquisar pela palavra chave que queremos
	 */
	public void addContent() {
		frame.setLayout(new BorderLayout());

		JTextField tweetText = new JTextField();

		JButton searchButton = new JButton("Post !!!");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//fAZER O pOST				
//				SendTweets senderOfTweet=new SendTweets();
//				senderOfTweet.sendTweet(tweetText.getText());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		frame.add(tweetText, BorderLayout.CENTER);
		frame.add(searchButton, BorderLayout.PAGE_END);

	}

	public static void main(String[] args) {
		PostGui gui = new PostGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent();
		gui.open();
	}

}
