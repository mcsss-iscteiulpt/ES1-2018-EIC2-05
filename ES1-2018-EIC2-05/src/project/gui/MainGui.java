package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import project.facebook.FacebookAPI;
import project.mail.ReceiverOfMails;
import project.twitter.TwitterHandler;


public class MainGui {

	protected JFrame frame;
	protected static TwitterHandler twitterHandler;
	protected static ReceiverOfMails mailHandler;
	protected static FacebookAPI facebookHandler;
	
	

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
		twitterHandler = new TwitterHandler();
		addContent();
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * conteudo da GUI: timeline de todas as interfaces menu para escolher qual
	 * interface mostrar filtro para a timeline (n�o operacional) botao de search
	 * para pesquisar as publica��es com aquela palavra chave(n�o operacional)
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
				SendMailGui searchGui = new SendMailGui("BDA(BOM DIA ACADEMIA)");
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

		String[] columnNames = { "API", "Time","Title", "Content", "User" };

		
//		JTable table = new JTable(allData(), columnNames);
		AnalyseData analyseData=new AnalyseData();
		JTable table = new JTable(analyseData.analyseData(), columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(800, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);

		frame.add(bda, BorderLayout.PAGE_END);
		frame.add(menuBar, BorderLayout.PAGE_START);
		frame.add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * Torna a GUI visivel
	 */
	public void open() {
		frame.setVisible(true);
	}

	public Object[][] allData()	{
		twitterHandler=new TwitterHandler();
		mailHandler=new ReceiverOfMails();
		facebookHandler=new FacebookAPI();
		Object [][] data1=twitterHandler.tweetsInGeneral();
		Object [][] data2=mailHandler.receiveMailsInGeneral("es1.eic2.5@gmail.com", "MiguelNeto15");
		Object [][] data3=facebookHandler.getPostsInGeneral();
		
		Object[][] result1 = new Object[data1.length + data2.length][];

		System.arraycopy(data1, 0, result1, 0, data1.length);
		System.arraycopy(data2, 0, result1, data1.length, data2.length);
		
		
		Object[][] result2 = new Object[result1.length + data3.length][];
		
		System.arraycopy(result1, 0, result2, 0, result1.length);
		System.arraycopy(data3, 0, result2, result1.length, data3.length);
		
		
		
		return result2;
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
