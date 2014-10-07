package EasyBBK_Swing.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data_center.*;

public class SubpartDialog extends JDialog {
	public MainPage parent;
	public SubpartDialog sd;
	/**
	 * Create the dialog.
	 */
	public SubpartDialog(MainPage parent1, String title, boolean modal){
		super(parent1, title, modal);
		this.parent = parent1;
		sd = this;
		init();
	}
	
	public void init(){
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(300, 200));
		this.setResizable(false);
		this.setLocation(533, 264);
		
		JTextField inputtext = new JTextField();
		inputtext.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		inputtext.setColumns(10);
		inputtext.setBounds(50, 50, 200, 30);
		this.getContentPane().add(inputtext);
		
		JButton Confirmed = new JButton("Confirmed");
		Confirmed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(inputtext.getText().equals("")) return;
				UploadCenter uploadcenter = new UploadCenter();
				BbkDetail bbkdetail = uploadcenter.getSubpartForSequenceToken(inputtext.getText());
				if(bbkdetail != null){
					parent.subpartstring = inputtext.getText();
					parent.subpart_bbkdetail = bbkdetail;
					sd.dispose();
				}
				else{
					JDialog jd = new JDialog(sd, true);
					jd.getContentPane().setLayout(new BorderLayout());
					jd.setSize(new Dimension(240, 100));
					jd.setLocation(563, 294);
					
					JLabel text = new JLabel("This subpart doesn't exist.", JLabel.CENTER);
					text.setFont(new Font("Times New Roman", Font.PLAIN, 20));
					jd.getContentPane().add(text, BorderLayout.CENTER);
					
					JButton Confirmed = new JButton("Confirmed");
					Confirmed.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							jd.dispose();
						}
					});
					Confirmed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
					Confirmed.setSize(120, 30);
					jd.getContentPane().add(Confirmed, BorderLayout.SOUTH);
					
					jd.setVisible(true);
				}
			}
		});
		Confirmed.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Confirmed.setBounds(15, 130, 120, 30);
		this.getContentPane().add(Confirmed);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sd.dispose();
			}
		});
		Cancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Cancel.setBounds(165, 130, 120, 30);
		this.getContentPane().add(Cancel);
		
		this.setVisible(true);
	}
}
