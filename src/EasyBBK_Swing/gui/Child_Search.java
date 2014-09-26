package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Child_Search extends JPanel {
	
	public JPanel Choicepanel;
	public JCheckBox chckbxReleased;
	public JCheckBox chckbxNewCheckBox;
	public JCheckBox chckbxNotReleased;
	public JCheckBox chckbxAvailable;
	public JCheckBox chckbxPlanning;
	public JCheckBox chckbxInformational;
	public JCheckBox chckbxNotDeleted;
	public JCheckBox chckbxDeleted;
	public JTextField textField;
	/**
	 * Create the panel.
	 */
	public Child_Search() {
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		JPanel Result = new JPanel();
		Result.setBounds(0, 0, 683, 670);
		Result.setBackground(new Color(255, 255, 255));
		Result.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Result);
		Result.setLayout(null);
		
		final JLabel Back = new JLabel();
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
			}
		});
		Back.setBounds(51, 42, 50, 50);
		Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
		Result.add(Back);
		
		final JLabel Forward = new JLabel();
		Forward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
			}
		});
		Forward.setBounds(107, 42, 70, 50);
		Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
		Result.add(Forward);
		
		JLabel Text_BackGround = new JLabel();
		Text_BackGround.setBounds(207, 42, 300, 50);
		Text_BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		Result.add(Text_BackGround);
		
		final JLabel Search = new JLabel();
		Search.setBounds(530, 42, 100, 50);
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Choicepanel.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		Result.add(Search);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER){
					
				}
			}
		});
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField.setVisible(true);
		textField.setBounds(10, 5, 283, 32);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Choicepanel.setVisible(true);
			}
		});
		Text_BackGround.add(textField);
		textField.setColumns(20);
		
		Choicepanel = new JPanel();
		Choicepanel.setBackground(new Color(255, 255, 255));
		Choicepanel.setBounds(62, 117, 558, 211);
		Choicepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		Choicepanel.setVisible(false);
		Result.add(Choicepanel);
		Choicepanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filters:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 68, 28);
		Choicepanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Release status:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(39, 48, 90, 17);
		Choicepanel.add(lblNewLabel_1);
		
		ItemListener itemListener1 = new ItemListener() {
            JCheckBox jCheckBox;
 
            public void itemStateChanged(ItemEvent e) {
                jCheckBox = (JCheckBox) e.getSource();
 
                if (jCheckBox.isSelected()) {
                    if(jCheckBox == chckbxReleased){
                    	chckbxNewCheckBox.setSelected(false);
                    	chckbxNotReleased.setSelected(false);
                    }
                    else if(jCheckBox == chckbxNewCheckBox){
                    	chckbxReleased.setSelected(false);
                    	chckbxNotReleased.setSelected(false);
                    }
                    else if(jCheckBox == chckbxNotReleased){
                    	chckbxReleased.setSelected(false);
                    	chckbxNewCheckBox.setSelected(false);
                    }
                } else {
                    
                }
 
            }
        };
        
        chckbxReleased = new JCheckBox("Released");
        //chckbxReleased.addItemListener(itemListener1);
		chckbxReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxReleased.setBounds(131, 46, 103, 23);
		chckbxReleased.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxReleased);
		
		chckbxNewCheckBox = new JCheckBox("Deleted");
		//chckbxNewCheckBox.addItemListener(itemListener1);
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox.setBounds(235, 46, 103, 23);
		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxNewCheckBox);
		
		chckbxNotReleased = new JCheckBox("Not Released");
		//chckbxNotReleased.addItemListener(itemListener1);
		chckbxNotReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNotReleased.setBounds(340, 46, 121, 23);
		chckbxNotReleased.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxNotReleased);
		
		JLabel lblDnaStatus = new JLabel("DNA status:");
		lblDnaStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDnaStatus.setBounds(39, 75, 90, 15);
		Choicepanel.add(lblDnaStatus);
		
		chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxAvailable.setBounds(131, 72, 103, 23);
		chckbxAvailable.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxAvailable);
		
		chckbxPlanning = new JCheckBox("Planning");
		chckbxPlanning.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxPlanning.setBounds(235, 71, 103, 23);
		chckbxPlanning.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxPlanning);
		
		chckbxInformational = new JCheckBox("Informational");
		chckbxInformational.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxInformational.setBounds(340, 72, 121, 23);
		chckbxInformational.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxInformational);
		
		JLabel lblNewLabel_2 = new JLabel("Whether or not deleted:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(39, 101, 151, 15);
		Choicepanel.add(lblNewLabel_2);
		
		chckbxNotDeleted = new JCheckBox("Not Deleted");
		chckbxNotDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNotDeleted.setBounds(196, 98, 103, 23);
		chckbxNotDeleted.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxNotDeleted);
		
		chckbxDeleted = new JCheckBox("Deleted");
		chckbxDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxDeleted.setBounds(311, 98, 103, 23);
		chckbxDeleted.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxDeleted);
		
		JLabel lblAverageStarsGiven = new JLabel("Average stars Given by previous teams:");
		lblAverageStarsGiven.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAverageStarsGiven.setBounds(39, 126, 238, 15);
		Choicepanel.add(lblAverageStarsGiven);
		
		JCheckBox checkBox = new JCheckBox(">=4");
		checkBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkBox.setBounds(283, 123, 55, 23);
		checkBox.setBackground(new Color(255, 255, 255));
		Choicepanel.add(checkBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("2-4");
		chckbxNewCheckBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox_1.setBounds(359, 122, 55, 23);
		chckbxNewCheckBox_1.setBackground(new Color(255, 255, 255));
		Choicepanel.add(chckbxNewCheckBox_1);
		
		JCheckBox checkBox_1 = new JCheckBox("<=2");
		checkBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkBox_1.setBounds(432, 122, 55, 23);
		checkBox_1.setBackground(new Color(255, 255, 255));
		Choicepanel.add(checkBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Preferences:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 151, 99, 15);
		Choicepanel.add(lblNewLabel_3);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblStatus.setBounds(39, 176, 47, 15);
		Choicepanel.add(lblStatus);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner.setBounds(88, 171, 41, 27);
		Choicepanel.add(spinner);
		
		JLabel lblQuality = new JLabel("Quality:");
		lblQuality.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblQuality.setBounds(147, 177, 54, 15);
		Choicepanel.add(lblQuality);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_1.setBounds(213, 171, 41, 27);
		Choicepanel.add(spinner_1);
		
		JLabel lblFeedbacks = new JLabel("Feedbacks:");
		lblFeedbacks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFeedbacks.setBounds(264, 177, 74, 15);
		Choicepanel.add(lblFeedbacks);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_2.setBounds(353, 171, 41, 27);
		Choicepanel.add(spinner_2);
		
		JLabel lblPublication = new JLabel("Publication:");
		lblPublication.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPublication.setBounds(404, 177, 74, 15);
		Choicepanel.add(lblPublication);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_3.setBounds(488, 171, 41, 27);
		Choicepanel.add(spinner_3);
		
		JPanel Details = new JPanel();
		Details.setBounds(684, 0, 683, 670);
		Details.setBackground(new Color(255, 255, 255));
		Details.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Details);
		Details.setLayout(null);
	}
}
