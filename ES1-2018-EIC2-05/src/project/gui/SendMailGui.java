package project.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import project.mail.SenderOfMails;

public class SendMailGui {
	
	private SenderOfMails sender;
	private JFrame frame;
	
	public SendMailGui(String frameTitle) {
		frame = new JFrame(frameTitle);
		frame.setSize(500, 200);
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
		JPanel center=new JPanel();
		JPanel endOfPage=new JPanel();
		center.setLayout(new GridLayout(3, 1));

		
		JLabel sendToLabel=new JLabel("To:");
		JTextField sendToText=new JTextField();
		JLabel subjectLabel=new JLabel("Subject:");
		JTextField subjectText=new JTextField();
		JLabel messageLabel=new JLabel("Message:");
		JTextField messageText=new JTextField();
		
		center.add(sendToLabel);
		center.add(sendToText);
		center.add(subjectLabel);
		center.add(subjectText);
		center.add(messageLabel);
		center.add(messageText);

		JButton sendMailButton = new JButton("Send Mail !!!");
		sendMailButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sender=new SenderOfMails();
				sender.sendMail(sendToText.getText(),subjectText.getText(),messageText.getText());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});

		
		endOfPage.add(sendMailButton, BorderLayout.PAGE_END);
		
		frame.add(center,BorderLayout.CENTER);
		frame.add(endOfPage, BorderLayout.PAGE_END);
	}	

	public void open() {
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SendMailGui gui = new SendMailGui("BDA(BOM DIA ACADEMIA)");
		gui.open();
	}

}
