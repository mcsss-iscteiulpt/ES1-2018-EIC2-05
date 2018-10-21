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
		JLabel bda=new JLabel(new ImageIcon("Imgs/BDALogo.png"));
		frame.add(bda,BorderLayout.NORTH);
	  
	    

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
