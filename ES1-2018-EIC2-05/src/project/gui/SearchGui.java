package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class SearchGui extends Gui {

	
	public SearchGui(String frameTitle) {
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

		JTextField searchFiltrer = new JTextField();

		JButton searchButton = new JButton("Search !!!");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		frame.add(searchFiltrer, BorderLayout.CENTER);
		frame.add(searchButton, BorderLayout.PAGE_END);

	}

	public static void main(String[] args) {
		SearchGui gui = new SearchGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent();
		gui.open();
	}

}
