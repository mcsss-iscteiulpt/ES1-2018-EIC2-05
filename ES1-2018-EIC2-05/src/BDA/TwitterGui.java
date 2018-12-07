package BDA;

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


/**
 * The Class TwitterGui.
 */
public class TwitterGui  {

	/** The table. */
	JTable table;
	
	/** The frame. */
	private JFrame frame;
	
	/** The twitter handler. */
	private TwitterHandler twitterHandler;
	
	/**
	 * Instantiates a new twitter gui.
	 *
	 * @param frameTitle the frame title
	 */
	public TwitterGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(900, 600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		twitterHandler = new TwitterHandler();

		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	
	/**
	 * Adds the content.
	 *
	 * @param nameOfAPI the name of API
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
		JMenuItem allSubmenu=new JMenuItem("All");

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
		timeLineMenu.add(allSubmenu);

		searchMenu.add(filterSubmenu);

		menuBar.add(timeLineMenu);
		menuBar.add(searchMenu);

		JPanel centerPanel = new JPanel();

		String[] columnNames = { "Time", "Content", "User" };

		DefaultTableModel model = new DefaultTableModel(twitterHandler.tweetsOnTwitterAPI(), columnNames);
		table = new JTable(twitterHandler.tweetsOnTwitterAPI(), columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPanel = new JScrollPane(table);

		centerPanel.add(scrollPanel);

		JLabel apiLabel = new JLabel(new ImageIcon("Imgs/twitter_logo.png"));
		apiLabel.setFont(apiLabel.getFont().deriveFont(34.0f));

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridLayout(2, 1));

		JButton tweetButton = new JButton("Tweet");
		tweetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TweetGui gui = new TweetGui("BDA(BOM DIA ACADEMIA)");
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
				model.setDataVector(twitterHandler.tweetsOnTwitterAPI(), columnNames);
				table.repaint();

			}
		});

		eastPanel.add(refreshButton);

		hourSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(twitterHandler.getTwittersOnThisHour(actualHour,actualDay,actualMounth,actualYear), columnNames);
				table.repaint();

			}
		});

		twentyFourhSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(twitterHandler.getTwittersOnThisDay(actualDay,actualMounth,actualYear), columnNames);
				table.repaint();
			}
		});

		weekSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(twitterHandler.getTwittersOnThisWeek(actualWeek,actualMounth,actualYear), columnNames);
				table.repaint();
			}
		});

		mounthSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(model);
				model.setDataVector(twitterHandler.getTwittersOnThisMounth(actualMounth,actualYear), columnNames);
				table.repaint();
			}
		});
		
		allSubmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setModel(model);
				model.setDataVector(twitterHandler.tweetsOnTwitterAPI(), columnNames);
				table.repaint();
			}
		});
		
		filterSubmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SearchGuiInTwitter searchGui = new SearchGuiInTwitter("BDA(BOM DIA ACADEMIA)");
				searchGui.addContentTable(table);
				searchGui.open();
				
				
			}
		});
		ArrayList<String>content=twitterHandler.getContent();
		ContentGui contentGui=new ContentGui("BDA(BOM DIA ACADEMIA)");
		contentGui.addContent();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {

		    	int row = table.rowAtPoint(evt.getPoint());
		    	int col = table.columnAtPoint(evt.getPoint());
		        if(col==1)	{
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
	
	/**
	 * Gets the jtable.
	 *
	 * @return the jtable
	 */
	public JTable getJtable()	{
		return table;
	}
	
	/**
	 * Open.
	 */
	public void open() {
		frame.setVisible(true);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		TwitterGui gui = new TwitterGui("BDA(BOM DIA ACADEMIA)");
		gui.addContent("Twitter");
		gui.open();
		
	}

}
