package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

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
	public JPanel Result;
	public JLabel Search;
	public JLabel previouspage;
	public JLabel page1;
	public JLabel page2;
	public JLabel page3;
	public JLabel page4;
	public JLabel page5;
	public JLabel page6;
	public JLabel page7;
	public JLabel page8;
	public JLabel page9;
	public JLabel page10;
	public JLabel nextpage;
	public MainPage mainpage;
	/**
	 * Create the panel.
	 */
	public Child_Search(MainPage mainpage1) {
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		Result = new JPanel();
		Result.setBounds(0, 0, 683, 670);
		Result.setBackground(new Color(255, 255, 255));
		Result.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Result);
		Result.setLayout(null);
		
		JLabel Back = new JLabel();
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
		
		JLabel Forward = new JLabel();
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
		
		Search = new JLabel();
		Search.setBounds(530, 42, 100, 50);
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField.getText() == null || textField.getText().trim().equals("")){
					Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Search){
						mainpage.child_search_current = (Child_Search) component;
						Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(child_search_main);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 1;
						String s = ""+ mainpage.CurrentPage;
						mainpage.test.setText(s);
					}
					return;
				}
				Choicepanel.setVisible(false);
				searchingresultpage.setVisible(true);
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
					if(textField.getText() == null || textField.getText().trim().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
							String s = ""+ mainpage.CurrentPage;
							mainpage.test.setText(s);
						}
						return;
					}
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
				searchingresultpage.setVisible(false);
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
		
		
		
		previouspage = new JLabel("<previous page");
		previouspage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		previouspage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		previouspage.setBounds(95, 627, 95, 25);
		Result.add(previouspage);
		
		page1 = new JLabel("1", JLabel.CENTER);
		page1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page1.setBounds(196, 627, 25, 25);
		Result.add(page1);
		
		page2 = new JLabel("2", JLabel.CENTER);
		page2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page2.setBounds(227, 627, 25, 25);
		Result.add(page2);
		
		page3 = new JLabel("3", SwingConstants.CENTER);
		page3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page3.setBounds(258, 627, 25, 25);
		Result.add(page3);
		
		page4 = new JLabel("4", SwingConstants.CENTER);
		page4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page4.setBounds(289, 627, 25, 25);
		Result.add(page4);
		
		page5 = new JLabel("5", SwingConstants.CENTER);
		page5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page5.setBounds(320, 627, 25, 25);
		Result.add(page5);
		
		page6 = new JLabel("6", SwingConstants.CENTER);
		page6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page6.setBounds(351, 627, 25, 25);
		Result.add(page6);
		
		page7 = new JLabel("7", SwingConstants.CENTER);
		page7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page7.setBounds(382, 627, 25, 25);
		Result.add(page7);
		
		page8 = new JLabel("8", SwingConstants.CENTER);
		page8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page8.setBounds(413, 627, 25, 25);
		Result.add(page8);
		
		page9 = new JLabel("9", SwingConstants.CENTER);
		page9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page9.setBounds(444, 627, 25, 25);
		Result.add(page9);
		
		page10 = new JLabel("10", SwingConstants.CENTER);
		page10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		page10.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page10.setBounds(475, 627, 25, 25);
		Result.add(page10);
		
		nextpage = new JLabel("next page>");
		nextpage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		nextpage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		nextpage.setBounds(506, 627, 75, 25);
		Result.add(nextpage);
		
		JPanel Details = new JPanel();
		Details.setBounds(684, 0, 683, 670);
		Details.setBackground(new Color(255, 255, 255));
		Details.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Details);
		Details.setLayout(null);
	}
}
