package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import project.bdaAPI.AnalyseData;
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
		JMenuItem allSubMenu=new JMenuItem("All");

		apisMenu.add(twitterSubmenu);
		apisMenu.add(mailSubmenu);
		apisMenu.add(facebookSubmenu);

		String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

		String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
		String actualDate = actualSplitTimeAndDate[0];
		String actualTime = actualSplitTimeAndDate[1];

		String[] actualSplitDate = actualDate.split("/");
		String actualMounth = actualSplitDate[1];
		String actualYear=actualSplitDate[0];
		Calendar cal = Calendar.getInstance();
	    int actualWeek = cal.get(Calendar.WEEK_OF_YEAR);
		String actualDay = actualSplitDate[2];

		String[] actualSplitTime = actualTime.split(":");
		String actualHour = actualSplitTime[0];
		
		
		timeLineMenu.add(hourSubmenu);
		timeLineMenu.add(twentyFourhSubmenu);
		timeLineMenu.add(weekSubmenu);
		timeLineMenu.add(mounthSubmenu);
		timeLineMenu.add(allSubMenu);
		

		searchMenu.add(filterSubmenu);

		menuBar.add(apisMenu);
		menuBar.add(timeLineMenu);
		menuBar.add(searchMenu);

		JPanel centerPanel = new JPanel();

		String[] columnNames = { "API", "Time","Title", "Content", "User" };

		

		AnalyseData analyseData=new AnalyseData();
		DefaultTableModel model = new DefaultTableModel(analyseData.analyseData(), columnNames);
		JTable table = new JTable(analyseData.analyseData(), columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(800, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);
		
		
		hourSubmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(analyseData.analyseDataAtThisHour(actualHour, actualDay, actualMounth, actualYear),columnNames);
				table.repaint();
				
			}
		});
		
		twentyFourhSubmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(analyseData.analyseDataAtThis24Hours( actualDay, actualMounth, actualYear),columnNames);
				table.repaint();
				
			}
		});
		
		weekSubmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				table.setModel(model);
				model.setDataVector(analyseData.analyseDataAtThisWeek(actualWeek,actualMounth, actualYear),columnNames);
				table.repaint();
			}
		});
		
		mounthSubmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(analyseData.analyseDataAtThisMounth(actualMounth, actualYear),columnNames);
				table.repaint();
				
			}
		});
		
		allSubMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(analyseData.analyseData(), columnNames);
				table.repaint();
				
			}
		});

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
