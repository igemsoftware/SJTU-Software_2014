package EasyBBK_Swing.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import data_center.UploadCenter;

public class SequenceDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public MainPage parent;
	public SequenceDialog sd;
	/**
	 * Create the dialog.
	 */
	public SequenceDialog(MainPage parent1, String title, boolean modal){
		super(parent1, title, modal);
		this.parent = parent1;
		sd = this;
		init();
	}
	
	public void init(){
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(300, 200));
		this.setResizable(false);
		//this.setLocation(533, 264);
		this.setLocationRelativeTo(null);
		
		final JTextArea SequenceInformation = new JTextArea(4, 15);
		SequenceInformation.setEditable(true);
		SequenceInformation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SequenceInformation.setLineWrap(true);
		SequenceInformation.setWrapStyleWord(true);
		SequenceInformation.setBackground(new Color(255, 255, 255));
		
		JScrollPane textscrollpanel = new JScrollPane(SequenceInformation);
		textscrollpanel.setBounds(0, 0, 295, 120);
		JScrollBar scrollbar1 = new JScrollBar();
		scrollbar1.setUnitIncrement(20);
		textscrollpanel.setVerticalScrollBar(scrollbar1);
		textscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textscrollpanel.validate();
		this.getContentPane().add(textscrollpanel);
		
		JButton Confirm = new JButton("Confirm");
		Confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(SequenceInformation.getText().equals("")) return;
					UploadCenter uploadcenter = new UploadCenter();
					if(uploadcenter.isSequanceValid(SequenceInformation.getText())){
						parent.sequencestring = SequenceInformation.getText();
						sd.dispose();
					}
					else{
						final JDialog jd = new JDialog(sd, true);
						jd.getContentPane().setLayout(new BorderLayout());
						jd.setSize(new Dimension(200, 100));
						//jd.setLocation(608, 294);
						jd.setLocationRelativeTo(null);
						
						JLabel text = new JLabel("Input Error.", JLabel.CENTER);
						text.setFont(new Font("Times New Roman", Font.PLAIN, 20));
						jd.getContentPane().add(text, BorderLayout.CENTER);
						
						JButton Confirm = new JButton("Confirm");
						Confirm.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								if(e.getButton() == MouseEvent.BUTTON1)
									jd.dispose();
							}
						});
						Confirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
						Confirm.setSize(120, 30);
						jd.getContentPane().add(Confirm, BorderLayout.SOUTH);
						
						jd.setVisible(true);
					}
				}
			}
		});
		Confirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Confirm.setBounds(15, 130, 120, 30);
		this.getContentPane().add(Confirm);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					sd.dispose();
			}
		});
		Cancel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Cancel.setBounds(165, 130, 120, 30);
		this.getContentPane().add(Cancel);
		
		this.setVisible(true);
	}
}
