package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import project.twitter.TwitterTimeline;

public class MainGui {

	/**
	 * 
	 */
	protected JFrame frame;
	protected TwitterTimeline twitterTimeline;

	/**
	 * construtor da GUI
	 * 
	 * @param frameTitle
	 */
	public MainGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(900, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		twitterTimeline = new TwitterTimeline();
		addContent();
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * conteudo da GUI: timeline de todas as interfaces menu para escolher qual
	 * interface mostrar filtro para a timeline (não operacional) botao de search
	 * para pesquisar as publicações com aquela palavra chave(não operacional)
	 */
	public void addContent() {
		frame.setLayout(new BorderLayout());
		JLabel bda = new JLabel(new ImageIcon("Imgs/BDA_logo.png"));

		JMenuBar menuBar = new JMenuBar();

		JMenu apisMenu = new JMenu("APIS");
		JMenu timeLineMenu = new JMenu("TimeLine");
		JMenu searchMenu = new JMenu("Search");

		JMenuItem twitterSubmenu = new JMenuItem("Twitter");
		twitterSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TwitterGui twitterGui = new TwitterGui("BDA(BOM DIA ACADEMIA)");
				twitterGui.addContent("Twitter");
				twitterGui.open();
			}
		});

		JMenuItem mailSubmenu = new JMenuItem("Mail");
		mailSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GmailGui gmailGui = new GmailGui("BDA(BOM DIA ACADEMIA)");
				gmailGui.addContent("Gmail");
				gmailGui.open();

			}
		});

		JMenuItem facebookSubmenu = new JMenuItem("Facebook");
		facebookSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FacebookGui facebookGui = new FacebookGui("BDA(BOM DIA ACADEMIA)");
				facebookGui.addContent("Facebook");
				facebookGui.open();

			}
		});

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

		apisMenu.add(twitterSubmenu);
		apisMenu.add(mailSubmenu);
		apisMenu.add(facebookSubmenu);

		timeLineMenu.add(hourSubmenu);
		timeLineMenu.add(twentyFourhSubmenu);
		timeLineMenu.add(weekSubmenu);
		timeLineMenu.add(mounthSubmenu);

		searchMenu.add(filterSubmenu);

		menuBar.add(apisMenu);
		menuBar.add(timeLineMenu);
		menuBar.add(searchMenu);

		JPanel centerPanel = new JPanel();

		String[] columnNames = { "API", "Time", "Content", "User" };

		JTable table = new JTable(twitterTimeline.tweetsInGeneral(), columnNames);
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

	/**
	 * Devolve a timeline do twitter
	 * 
	 * @return timeline do twitter
	 */
	public TwitterTimeline getTwitterTimeLine() {
		return twitterTimeline;
	}

	/**
	 * Para executar a GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainGui gui = new MainGui("BDA(BOM DIA ACADEMIA)");
		gui.open();
	}
}
