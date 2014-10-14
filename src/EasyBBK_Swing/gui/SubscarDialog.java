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
import data_center.BbkUpload.SpecifiedSubscar;

public class SubscarDialog extends JDialog {
	public MainPage parent;
	public SubscarDialog sd;
	/**
	 * Create the dialog.
	 */
	public SubscarDialog(MainPage parent1, String title, boolean modal){
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
		
		final JTextField inputtext = new JTextField();
		inputtext.setFont(new Font("Arial", Font.PLAIN, 20));
		inputtext.setColumns(10);
		inputtext.setBounds(50, 50, 200, 30);
		this.getContentPane().add(inputtext);
		
		JButton Confirme = new JButton("Confirme");
		Confirme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(inputtext.getText().equals("")) return;
					UploadCenter uploadcenter = new UploadCenter();
					SpecifiedSubscar ss = uploadcenter.getSubscarForSequenceToken(inputtext.getText());
					if(ss != null){
						parent.subscarstring = inputtext.getText();
						parent.subscar = ss;
						sd.dispose();
					}
					else{
						final JDialog jd = new JDialog(sd, true);
						jd.getContentPane().setLayout(new BorderLayout());
						jd.setSize(new Dimension(240, 100));
						jd.setLocation(563, 294);
						
						JLabel text = new JLabel("This subscar doesn't exist.", JLabel.CENTER);
						text.setFont(new Font("Arial", Font.PLAIN, 20));
						jd.getContentPane().add(text, BorderLayout.CENTER);
						
						JButton Confirme = new JButton("Confirme");
						Confirme.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								jd.dispose();
							}
						});
						Confirme.setFont(new Font("Arial", Font.PLAIN, 20));
						Confirme.setSize(120, 30);
						jd.getContentPane().add(Confirme, BorderLayout.SOUTH);
						
						jd.setVisible(true);
					}
				}
			}
		});
		Confirme.setFont(new Font("Arial", Font.PLAIN, 20));
		Confirme.setBounds(15, 130, 120, 30);
		this.getContentPane().add(Confirme);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					sd.dispose();
			}
		});
		Cancel.setFont(new Font("Arial", Font.PLAIN, 20));
		Cancel.setBounds(165, 130, 120, 30);
		this.getContentPane().add(Cancel);
		
		this.setVisible(true);
	}
}
