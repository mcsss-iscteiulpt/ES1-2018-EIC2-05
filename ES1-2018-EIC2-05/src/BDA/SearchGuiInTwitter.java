package BDA;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;



/**
 * The Class SearchGuiInTwitter.
 */
public class SearchGuiInTwitter {
	
	/** The twitter handler. */
	private TwitterHandler twitterHandler;
	
	/** The frame. */
	private JFrame frame;
	
	/**
	 * Instantiates a new search gui in twitter.
	 *
	 * @param frameTitle the frame title
	 */
	public SearchGuiInTwitter(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(400, 100);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	
	/**
	 * Adds the content table.
	 *
	 * @param table the table
	 */
	public void addContentTable(JTable table) {
		twitterHandler=new TwitterHandler();
		frame.setLayout(new BorderLayout());
		

		JTextField searchText = new JTextField();

		JButton searchButton = new JButton("Search !!!");
		searchButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = { "Time", "Content", "User" };
				DefaultTableModel model = new DefaultTableModel(twitterHandler.tweetsOnTwitterAPI(), columnNames);
				table.setModel(model);
				model.setDataVector(twitterHandler.searchWordInTweet(searchText.getText()), columnNames);
				table.repaint();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		
		
		
		frame.add(searchText, BorderLayout.CENTER);
		frame.add(searchButton, BorderLayout.PAGE_END);
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
		
		
	}

}