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

import project.facebook.FacebookAPI;


public class SearchGuiInFacebook {
	private FacebookAPI facebookAPI;
	private JFrame frame;
	
	public SearchGuiInFacebook(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(400, 100);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * Janela onde vamos pesquisar pela palavra chave que queremos
	 */
	public void addContentTable(JTable table) {
		facebookAPI=new FacebookAPI();
		frame.setLayout(new BorderLayout());
		

		JTextField searchText = new JTextField();

		JButton searchButton = new JButton("Search !!!");
		searchButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = { "Time", "Content", "User" };
				DefaultTableModel model = new DefaultTableModel(facebookAPI.getPostsOnTheApi(), columnNames);
				table.setModel(model);
				model.setDataVector(facebookAPI.searckWordInFacebook(searchText.getText()), columnNames);
				table.repaint();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		
		
		
		frame.add(searchText, BorderLayout.CENTER);
		frame.add(searchButton, BorderLayout.PAGE_END);
	}

	public void open() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SearchGuiInFacebook s=new SearchGuiInFacebook("");
		s.addContentTable(null);
		s.open();
	}
}
