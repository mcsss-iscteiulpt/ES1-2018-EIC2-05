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

import project.twitter.TwitterHandler;

public class TweetGui  {
	
	private JFrame frame;
	private TwitterHandler twitterHandler;
	
	public TweetGui(String frameTitle) {
		twitterHandler=new TwitterHandler();
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

		JButton searchButton = new JButton("Tweet !!!");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				twitterHandler.sendTweet(tweetText.getText());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		frame.add(tweetText, BorderLayout.CENTER);
		frame.add(searchButton, BorderLayout.PAGE_END);

	}

	public void open() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		TweetGui gui = new TweetGui("BDA(BOM DIA ACADEMIA)");
		gui.open();
	}

}
