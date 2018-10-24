package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class SubGui extends Gui {

	public SubGui(String frameTitle) {
		super(frameTitle);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public void addContent(String nameOfAPI) {
		frame.remove(frame);
		frame.setLayout(new BorderLayout());
		JLabel bda = new JLabel(new ImageIcon("Imgs/BDALogo.png"));

		JMenuBar menuBar = new JMenuBar();
		JMenu timeLineMenu = new JMenu("TimeLine");
		JMenu searchMenu = new JMenu("Search");
		
		JMenuItem filterSubmenu = new JMenuItem("Choose you filter!!!");
		
		filterSubmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchGui searchGui=new SearchGui("BDA(BOM DIA ACADEMIA)");
				searchGui.addContent();
				searchGui.open();
			}
		});
		

		JMenuItem hourSubmenu = new JMenuItem("Every Hour");
		JMenuItem twentyFourhSubmenu = new JMenuItem("24 Hours");
		JMenuItem weekSubmenu = new JMenuItem("Week");
		JMenuItem mounthSubmenu = new JMenuItem("Mounth");

		
		
		timeLineMenu.add(hourSubmenu);
		timeLineMenu.add(twentyFourhSubmenu);
		timeLineMenu.add(weekSubmenu);
		timeLineMenu.add(mounthSubmenu);

		searchMenu.add(filterSubmenu);
		
		menuBar.add(timeLineMenu);
		menuBar.add(searchMenu);

		JPanel centerPanel = new JPanel();

		String[] columnNames = { "Time", "Content", "User" };
		Object[][] data = { { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, { "12:44", "---------", "User1" },
				{ "12:44", "---------", "User1" }, { "12:44", "---------", "User1" }, };

		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);

		JLabel apiLabel = new JLabel(nameOfAPI);
		apiLabel.setFont (apiLabel.getFont ().deriveFont (34.0f));
		
		frame.add(bda, BorderLayout.PAGE_END);
		frame.add(menuBar, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(apiLabel, BorderLayout.WEST);

	}

	public static void main(String[] args) {
		SubGui gui = new SubGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent("Twitter");
		gui.open();
	}

}
