package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	public Choicepanel choicepanel;
	public MainPage mainpage;
	public JLabel Blast;
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
		TextBox.setBounds(463, 307, 426, 50);
		TextBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text1.png")));
		add(TextBox);
		
		SearchText = new JTextField();
		SearchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == arg0.VK_ENTER){
					if(SearchText.getText() == null || SearchText.getText().trim().equals("")) return;
					Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Search_Main){
						mainpage.child_search_main_current = (Child_Search_Main) component;
					}
					Child_Search child_search = new Child_Search(mainpage, SearchText.getText());
					
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
				if(arg0.getClickCount()==2){
					choicepanel.setVisible(true);
				}
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
				if(SearchText.getText() == null || SearchText.getText().trim().equals("")) return;
				Component component = mainpage.Mainpanel.getComponent(0);
				if(component instanceof Child_Search_Main){
					mainpage.child_search_main_current = (Child_Search_Main) component;
				}
				
				Child_Search child_search = new Child_Search(mainpage, SearchText.getText());
				
				mainpage.Mainpanel.removeAll();
				mainpage.Mainpanel.add(child_search);
				child_search.textField.setText(SearchText.getText());
				mainpage.Mainpanel.updateUI();
				mainpage.CurrentPage = 11;
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
		goBox.setBounds(915, 307, 100, 50);
		goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		add(goBox);
		
		choicepanel = new Choicepanel();
		choicepanel.setBounds(408, 402, 558, 211);
		add(choicepanel);
		
		Blast = new JLabel("");
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast_enter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
			}
		});
		Blast.setBounds(1034, 307, 125, 50);
		Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
		add(Blast);
	}
}
