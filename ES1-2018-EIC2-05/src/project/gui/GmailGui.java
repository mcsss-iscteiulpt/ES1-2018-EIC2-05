package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;


public class GmailGui extends MainGui {

	public GmailGui(String frameTitle) {
		super(frameTitle);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * GUI de cada interface
	 * 
	 * @param nameOfAPI
	 */
	public void addContent(String nameOfAPI) {
		frame.remove(frame);
		frame.setLayout(new BorderLayout());
		JLabel bda = new JLabel(new ImageIcon("Imgs/BDA_logo.png"));

		JMenuBar menuBar = new JMenuBar();
		JMenu timeLineMenu = new JMenu("TimeLine");
		JMenu searchMenu = new JMenu("Search");

		JMenuItem filterSubmenu = new JMenuItem("Choose you filter!!!");

		filterSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchGui searchGui = new SearchGui("BDA(BOM DIA ACADEMIA)");
				searchGui.addContent();
				searchGui.open();
			}
		});

		JMenuItem hourSubmenu = new JMenuItem("Every Hour");
		JMenuItem twentyFourhSubmenu = new JMenuItem("24 Hours");
		JMenuItem weekSubmenu = new JMenuItem("Week");
		JMenuItem mounthSubmenu = new JMenuItem("Mounth");

//		Date date=new Date(0);
//		int hours=date.getHours();
//		int day=date.getDay();
//		int mounth=date.getMonth(); Ver método para obter data;

		hourSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Ver como percorrer jtable
				// if(hour==actualHour)
				// showiT;
				System.out.println("Current Hour");
			}
		});

		twentyFourhSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("24 Hours");
			}
		});

		weekSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Week");
			}
		});

		mounthSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Month");
			}
		});

		timeLineMenu.add(hourSubmenu);
		timeLineMenu.add(twentyFourhSubmenu);
		timeLineMenu.add(weekSubmenu);
		timeLineMenu.add(mounthSubmenu);

		searchMenu.add(filterSubmenu);

		menuBar.add(timeLineMenu);
		menuBar.add(searchMenu);

		JPanel centerPanel = new JPanel();

		JTable table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);

		JLabel apiLabel = new JLabel(new ImageIcon("Imgs/gmail_logo.png"));
		apiLabel.setFont(apiLabel.getFont().deriveFont(34.0f));

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(2, 1));

		JButton tweetButton = new JButton("Mail");
		tweetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MailGui gui = new MailGui("BDA(BOM DIA ACADEMIA)");
				gui.addContent();
				gui.open();
			}
		});

		eastPanel.add(tweetButton);

		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		eastPanel.add(refreshButton);

		frame.add(bda, BorderLayout.PAGE_END);
		frame.add(menuBar, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(apiLabel, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);

	}

	public static void main(String[] args) {
		GmailGui gui = new GmailGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent("Twitter");
		gui.open();
	}

}
