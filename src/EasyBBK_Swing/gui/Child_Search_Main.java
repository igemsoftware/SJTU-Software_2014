package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import data_center.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Child_Search_Main extends JPanel {
	public JLabel LogoBox;
	public JLabel BackBox;
	public JLabel ForwardBox;
	public JLabel TextBox;
	public JTextField SearchText;
	public JLabel goBox;
	public JPanel Choicepanel;
	public JCheckBox chckbxReleased;
	public JCheckBox chckbxNewCheckBox;
	public JCheckBox chckbxNotReleased;
	public JCheckBox chckbxAvailable;
	public JCheckBox chckbxPlanning;
	public JCheckBox chckbxInformational;
	public JCheckBox chckbxNotDeleted;
	public JCheckBox chckbxDeleted;
	public MainPage mainpage;
	/**
	 * Create the panel.
	 */
	public Child_Search_Main(MainPage mainpage1) {
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		LogoBox = new JLabel("");
		LogoBox.setBounds(504, 48, 357, 187);
		LogoBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Logo.png")));
		add(LogoBox);
		
		BackBox = new JLabel("");
		BackBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BackBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BackBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
			}
		});
		BackBox.setBounds(316, 307, 50, 50);
		BackBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
		add(BackBox);
		
		ForwardBox = new JLabel("");
		ForwardBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ForwardBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				ForwardBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
			}
		});
		ForwardBox.setBounds(372, 307, 70, 50);
		ForwardBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
		add(ForwardBox);
		
	    TextBox = new JLabel("");
		TextBox.setBounds(471, 307, 426, 50);
		TextBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text1.png")));
		add(TextBox);
		
		SearchText = new JTextField();
		SearchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == arg0.VK_ENTER){
					Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Search_Main){
						mainpage.child_search_main_current = (Child_Search_Main) component;
					}
					Child_Search child_search = new Child_Search(mainpage);
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_search);
					child_search.textField.setText(SearchText.getText());
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 11;
				}
			}
		});
		SearchText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Choicepanel.setVisible(true);
			}
		});
		SearchText.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		SearchText.setVisible(true);
		SearchText.setBounds(12, 5, 403, 32);
		TextBox.add(SearchText);
		SearchText.setColumns(20);
		
		goBox = new JLabel("");
		goBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Component component = mainpage.Mainpanel.getComponent(0);
				if(component instanceof Child_Search_Main){
					mainpage.child_search_main_current = (Child_Search_Main) component;
				}
				Child_Search child_search = new Child_Search(mainpage);
				mainpage.Mainpanel.removeAll();
				mainpage.Mainpanel.add(child_search);
				child_search.textField.setText(SearchText.getText());
				mainpage.Mainpanel.updateUI();
				mainpage.CurrentPage = 11;
				String s = ""+ mainpage.CurrentPage;
				mainpage.test.setText(s);
				//SearchResultList searchresultlist = BbkDatabaseConnector.search(SearchText.getText());
				//BbkBlaster.MODE_INPUT_FILE_PATH
				//BbkBlaster.blast(input, mode);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		goBox.setBounds(949, 307, 100, 50);
		goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		add(goBox);
		
		Choicepanel = new JPanel();
		Choicepanel.setBackground(new Color(255, 255, 255));
		Choicepanel.setBounds(408, 402, 558, 211);
		Choicepanel.setBorder(BorderFactory.createLineBorder(Color.black));
		Choicepanel.setVisible(false);
		add(Choicepanel);
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
	}
}
