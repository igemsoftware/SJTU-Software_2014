package EasyBBK_Swing.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sun.misc.BASE64Encoder;
import data_center.*;

public class WaitingDialog extends JDialog {
	public MainPage parent;
	public WaitingDialog wd;
	public JLabel inputtext;
	public JButton Confirmed;
	public Child_Upload child_upload;
	/**
	 * Create the dialog.
	 */
	public WaitingDialog(MainPage parent1, String title, boolean modal, Child_Upload child_upload1){
		super(parent1, title, modal);
		child_upload = child_upload1;
		this.parent = parent1;
		wd = this;
		init();
		
	}
	
	public void init(){
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 280));
		this.setResizable(false);
		this.setLocation(533, 264);
		
		inputtext = new JLabel("<html>" + "Uploading your biobrick" + "<br>" + " to offical website." + "</html>");
		inputtext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		inputtext.setBounds(50, 50, 300, 60);
		this.getContentPane().add(inputtext);
		
		Confirmed = new JButton("Confirmed");
		Confirmed.setVisible(false);
		Confirmed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					wd.dispose();
			}
		});
		Confirmed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Confirmed.setBounds(140, 160, 120, 30);
		this.getContentPane().add(Confirmed);
		
		this.setVisible(true);
	}
	
	
}
