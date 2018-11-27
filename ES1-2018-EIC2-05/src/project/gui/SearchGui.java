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
//import javax.swing.table.DefaultTableModel;



public class SearchGui extends TwitterGui {
	
	
	public SearchGui(String frameTitle) {
		super(frameTitle);
		frame = new JFrame(frameTitle);
		frame.setSize(400, 100);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addContentTable();
	}

	/**
	 * Janela onde vamos pesquisar pela palavra chave que queremos
	 */
	public JTable addContentTable() {
		frame.setLayout(new BorderLayout());
		JTable t=new JTable();

		JTextField searchText = new JTextField();

		JButton searchButton = new JButton("Search !!!");
		searchButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
//				String[] columnNames = { "Time", "Content", "User" };
//				DefaultTableModel model = new DefaultTableModel(twitterHandler.tweetsOnTwitterAPI(), columnNames);
//				t.setModel(model);
//				model.setDataVector(twitterHandler.searchWordInTweet(searchText.getText()), columnNames);
//				t.repaint();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		frame.add(searchText, BorderLayout.CENTER);
		frame.add(searchButton, BorderLayout.PAGE_END);
		
		return t;
	}

	public static void main(String[] args) {
		SearchGui gui = new SearchGui("BDA(BOM DIA ACADEMIA)");
		gui.open();
	}

}