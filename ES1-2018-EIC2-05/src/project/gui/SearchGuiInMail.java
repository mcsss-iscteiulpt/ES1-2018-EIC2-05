package project.gui;

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

import project.mail.ReceiverOfMails;


// TODO: Auto-generated Javadoc
/**
 * The Class SearchGuiInMail.
 */
public class SearchGuiInMail {
	
	/** The receiver of mails. */
	private ReceiverOfMails receiverOfMails;
	
	/** The frame. */
	private JFrame frame;
	
	/**
	 * Instantiates a new search gui in mail.
	 *
	 * @param frameTitle the frame title
	 */
	public SearchGuiInMail(String frameTitle) {
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
		receiverOfMails=new ReceiverOfMails();
		frame.setLayout(new BorderLayout());
		

		JTextField searchText = new JTextField();

		JButton searchButton = new JButton("Search !!!");
		searchButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = { "Time", "Content", "User" };
				DefaultTableModel model = new DefaultTableModel(receiverOfMails.receiveMailsOnApi("es1.eic2.5@gmail.com", "MiguelNeto15"), columnNames);
				table.setModel(model);
				model.setDataVector(receiverOfMails.searchWordInGmail(searchText.getText()), columnNames);
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
}
