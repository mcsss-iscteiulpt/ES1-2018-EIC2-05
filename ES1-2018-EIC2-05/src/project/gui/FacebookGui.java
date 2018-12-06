package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import project.facebook.FacebookAPI;

public class FacebookGui {

	private JFrame frame;

	public FacebookGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(900, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

	

		JMenuItem hourSubmenu = new JMenuItem("Every Hour");
		JMenuItem twentyFourhSubmenu = new JMenuItem("24 Hours");
		JMenuItem weekSubmenu = new JMenuItem("Week");
		JMenuItem mounthSubmenu = new JMenuItem("Mounth");
		JMenuItem allSubmenu = new JMenuItem("All");

		String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

		String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
		String actualDate = actualSplitTimeAndDate[0];
		String actualTime = actualSplitTimeAndDate[1];

		String[] actualSplitDate = actualDate.split("/");
		String actualMounth = actualSplitDate[1];
		String actualYear = actualSplitDate[0];
		Calendar cal = Calendar.getInstance();
		int actualWeek = cal.get(Calendar.WEEK_OF_YEAR);
		String actualDay = actualSplitDate[2];

		String[] actualSplitTime = actualTime.split(":");
		String actualHour = actualSplitTime[0];

		timeLineMenu.add(hourSubmenu);
		timeLineMenu.add(twentyFourhSubmenu);
		timeLineMenu.add(weekSubmenu);
		timeLineMenu.add(mounthSubmenu);
		timeLineMenu.add(allSubmenu);

		searchMenu.add(filterSubmenu);

		menuBar.add(timeLineMenu);
		menuBar.add(searchMenu);

		JPanel centerPanel = new JPanel();

		String[] columnNames = { "Time", "Content", "User" };

		FacebookAPI facebookAPI = new FacebookAPI();
		DefaultTableModel model = new DefaultTableModel(facebookAPI.getPostsOnTheApi(), columnNames);
		JTable table = new JTable(facebookAPI.getPostsOnTheApi(), columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);

//Mudar Imagem para a do face		
		JLabel apiLabel = new JLabel(new ImageIcon("Imgs/facebook_logo.png"));
		apiLabel.setFont(apiLabel.getFont().deriveFont(34.0f));

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(2, 1));

		JButton tweetButton = new JButton("Post");
		tweetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PostGui gui = new PostGui("BDA(BOM DIA ACADEMIA)");
				gui.addContent();
				gui.open();
			}
		});

		eastPanel.add(tweetButton);

		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(facebookAPI.getPostsOnTheApi(), columnNames);
				table.repaint();
			}
		});

		eastPanel.add(refreshButton);

		hourSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(facebookAPI.getPostsOnThisHour(actualHour, actualDay, actualMounth, actualYear),
						columnNames);
				table.repaint();

			}
		});

		twentyFourhSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(facebookAPI.getPostsOnThisDay(actualDay, actualMounth, actualYear), columnNames);
				table.repaint();
				;
			}
		});

		weekSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(facebookAPI.getPostsOnThisWeek(actualWeek, actualMounth, actualYear), columnNames);
				table.repaint();
			}
		});

		mounthSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(facebookAPI.getPostsOnThisMounth(actualMounth, actualYear), columnNames);
				table.repaint();
			}
		});

		allSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(facebookAPI.getPostsOnTheApi(), columnNames);
				table.repaint();
			}
		});

		filterSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchGuiInFacebook searchGui = new SearchGuiInFacebook("BDA(BOM DIA ACADEMIA)");
				searchGui.addContentTable(table);
				searchGui.open();

			}
		});

		ArrayList<String> content = facebookAPI.getContent();
		ContentGui contentGui = new ContentGui("BDA(BOM DIA ACADEMIA)");
		contentGui.addContent();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (col == 1) {
					contentGui.changeText(content.get(row));
					System.out.println(content.get(row));
					contentGui.open();
				}
			}
		});

		frame.add(bda, BorderLayout.PAGE_END);
		frame.add(menuBar, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(apiLabel, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);

	}

	public void open() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		FacebookGui gui = new FacebookGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent("Twitter");
		gui.open();
	}

}
