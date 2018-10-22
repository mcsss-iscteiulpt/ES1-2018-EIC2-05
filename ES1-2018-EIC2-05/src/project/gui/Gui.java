package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class Gui {

	private JFrame frame;

	public Gui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(900, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addContent();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void addContent() {
		frame.setLayout(new BorderLayout());
		JLabel bda = new JLabel(new ImageIcon("Imgs/BDALogo.png"));

		JMenuBar menuBar = new JMenuBar();
		JMenu apisMenu = new JMenu("APIS");
		JMenu timeLineMenu = new JMenu("TimeLine");

		JMenuItem twitterSubmenu = new JMenuItem("Twitter");
		JMenuItem mailSubmenu = new JMenuItem("Mail");
		JMenuItem facebookSubmenu = new JMenuItem("Facebook");

		JMenuItem hourSubmenu = new JMenuItem("Every Hour");
		JMenuItem twentyFourhSubmenu = new JMenuItem("24 Hours");
		JMenuItem weekSubmenu = new JMenuItem("Week");
		JMenuItem mounthSubmenu = new JMenuItem("Mounth");

		apisMenu.add(twitterSubmenu);
		apisMenu.add(mailSubmenu);
		apisMenu.add(facebookSubmenu);

		timeLineMenu.add(hourSubmenu);
		timeLineMenu.add(twentyFourhSubmenu);
		timeLineMenu.add(weekSubmenu);
		timeLineMenu.add(mounthSubmenu);

		menuBar.add(apisMenu);
		menuBar.add(timeLineMenu);

		JPanel centerPanel = new JPanel();

		String[] columnNames = { "API", "Time", "Content", "User" };
		Object[][] data = { { "Facebook", "12:44", "---------", "User1" },
				{ "Twitter", "02:04", "---------", "User12" }, { "Gmail", "22:49", "---------", "User31" },
				{ "Facebook", "12:44", "---------", "User1" }, { "Twitter", "02:04", "---------", "User12" },
				{ "Gmail", "22:49", "---------", "User31" }, { "Facebook", "12:44", "---------", "User1" },
				{ "Twitter", "02:04", "---------", "User12" }, { "Gmail", "22:49", "---------", "User31" },
				{ "Facebook", "12:44", "---------", "User1" }, { "Twitter", "02:04", "---------", "User12" },
				{ "Gmail", "22:49", "---------", "User31" }, { "Facebook", "12:44", "---------", "User1" },
				{ "Twitter", "02:04", "---------", "User12" }, { "Gmail", "22:49", "---------", "User31" },
				{ "Facebook", "12:44", "---------", "User1" }, { "Twitter", "02:04", "---------", "User12" },
				{ "Gmail", "22:49", "---------", "User31" }, { "Facebook", "12:44", "---------", "User1" },
				{ "Twitter", "02:04", "---------", "User12" }, { "Gmail", "22:49", "---------", "User31" },
				{ "Facebook", "12:44", "---------", "User1" }, { "Twitter", "02:04", "---------", "User12" },
				{ "Gmail", "22:49", "---------", "User31" }, { "Facebook", "12:44", "---------", "User1" },
				{ "Twitter", "02:04", "---------", "User12" }, { "Gmail", "22:49", "---------", "User31" },
				{ "Facebook", "12:44", "---------", "User1" }, { "Twitter", "02:04", "---------", "User12" },
				{ "Gmail", "22:49", "---------", "User31" }, { "Facebook", "12:44", "---------", "User1" },
				{ "Twitter", "02:04", "---------", "User12" }, { "Gmail", "22:49", "---------", "User31" },
				{ "Facebook", "12:44", "---------", "User1" }, { "Twitter", "02:04", "---------", "User12" },
				{ "Gmail", "22:49", "---------", "User31" }, };

		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);

		frame.add(bda, BorderLayout.PAGE_END);
		frame.add(menuBar, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Gui gui = new Gui("BDA(BOM DIA ACADEMIA)");
		gui.addContent();
		gui.open();
	}
}
