package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Child_Search_Main extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JLabel LogoBox;
	public JLabel TextBox;
	public JTextField SearchText;
	public JLabel goBox;
	public MainPage mainpage;
	public JLabel Blast;
	public JScrollPane scrollpane;
	public JScrollBar scrollbar;
	public Choicepanel choicepanel;
	public Information information;
	public boolean flag = false;
	public JLabel AdvancedSearch;
	public JLabel BackGround;
	/**
	 * Create the panel.
	 */
	public Child_Search_Main(MainPage mainpage1) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					requestFocus();
			}
		});
		mainpage = mainpage1;
		if(mainpage.small == false){
			setBounds(0, 0, 1366, 670);
		}
		else if(mainpage.small == true){
			setBounds(0, 0, 1280, 670);
		}
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		LogoBox = new JLabel("");
		if(mainpage.small == false){
			LogoBox.setBounds(486, 35, 410, 187);
		}
		else if(mainpage.small == true){
			LogoBox.setBounds(455, 35, 410, 187);
		}
		LogoBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Logo.png")));
		add(LogoBox);
		
		SearchText = new JTextField();
		if(mainpage.small == false){
			SearchText.setBounds(373, 237, 426, 65);
		}
		else if(mainpage.small == true){
			SearchText.setBounds(349, 237, 426, 65);
		}
		SearchText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == KeyEvent.VK_ENTER){
					if(SearchText.getText().equals("")) return;
					Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Search_Main){
						mainpage.child_search_main_current = (Child_Search_Main) component;
					}
					Child_Search child_search = new Child_Search(mainpage, SearchText.getText(), choicepanel.information, 1, choicepanel.confirmed_clicked);
					
					mainpage.Mainpanel.removeAll();
					child_search.textField.setText(SearchText.getText());
					mainpage.Mainpanel.add(child_search);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 11;
				}
			}
		});
		SearchText.setBorder(null);
		SearchText.setFont(new Font("Arial", Font.PLAIN, 40));
		SearchText.setVisible(true);
		add(SearchText);
		SearchText.setColumns(20);
		
		goBox = new JLabel("");
		if(mainpage.small == false){
			goBox.setBounds(799, 237, 105, 64);
		}
		else if(mainpage.small == true){
			goBox.setBounds(776, 237, 105, 64);
		}
		goBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(SearchText.getText().equals("")) return;
					requestFocus();
					Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Search_Main){
						mainpage.child_search_main_current = (Child_Search_Main) component;
					}
					
					Child_Search child_search = new Child_Search(mainpage, SearchText.getText(), choicepanel.information , 1, choicepanel.confirmed_clicked);
					
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_search);
					child_search.textField.setText(SearchText.getText());
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 11;
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				goBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		add(goBox);
		
		choicepanel = new Choicepanel();
		choicepanel.setPreferredSize(new Dimension(623, 489));
		
		scrollpane = new JScrollPane(choicepanel);
		if(mainpage.small == false){
			scrollpane.setBounds(358, 360, 641, 289);
		}
		else if(mainpage.small == true){
			scrollpane.setBounds(335, 360, 641, 289);
		}
		scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(50);
		scrollpane.setVerticalScrollBar(scrollbar);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.validate();
		scrollpane.setVisible(false);
		add(scrollpane);
		
		Blast = new JLabel("");
		if(mainpage.small == false){
			Blast.setBounds(908, 237, 84, 64);
		}
		else if(mainpage.small == true){
			Blast.setBounds(885, 237, 84, 64);
		}
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(SearchText.getText().equals("")) return;
					requestFocus();
					Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Search_Main){
						mainpage.child_search_main_current = (Child_Search_Main) component;
					}
					
					Child_Search child_search = new Child_Search(mainpage, SearchText.getText(), choicepanel.information , 2, choicepanel.confirmed_clicked);
					
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_search);
					child_search.textField.setText(SearchText.getText());
					mainpage.Mainpanel.updateUI();
					
					mainpage.CurrentPage = 11;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Blast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast_enter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
			}
		});
		Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
		add(Blast);
		
		AdvancedSearch = new JLabel();
		if(mainpage.small == false){
			AdvancedSearch.setBounds(367, 308, 194, 34);
		}
		else if(mainpage.small == true){
			AdvancedSearch.setBounds(343, 308, 194, 34);
		}
		AdvancedSearch.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/advancedsearch.png")));
		AdvancedSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					AdvancedSearch.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/advancedsearch_clicked.png")));
					if(flag == false){
						scrollpane.setVisible(true);
						flag = true;
					}
					else if(flag == true){
						scrollpane.setVisible(false);
						flag = false;
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AdvancedSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				AdvancedSearch.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/advancedsearch_clicked.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AdvancedSearch.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/advancedsearch.png")));
			}
		});
		add(AdvancedSearch);
		
		BackGround = new JLabel("");
		if(mainpage.small == false){
			BackGround.setBounds(0, 0, 1366, 670);
		}
		else if(mainpage.small == true){
			BackGround.setBounds(0, 0, 1280, 670);
		}
		BackGround.setVisible(true);
		BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/BackGround.png")));
		add(BackGround);
	}
}
