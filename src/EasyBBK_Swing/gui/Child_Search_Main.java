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
					Child_Search child_search = new Child_Search(mainpage, SearchText.getText(), choicepanel.information, 1, choicepanel.confirmed_clicked);
					
					mainpage.Mainpanel.removeAll();
					child_search.textField.setText(SearchText.getText());
					mainpage.Mainpanel.add(child_search);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 11;
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
				
				Child_Search child_search = new Child_Search(mainpage, SearchText.getText(), choicepanel.information , 1, choicepanel.confirmed_clicked);
				
				mainpage.Mainpanel.removeAll();
				mainpage.Mainpanel.add(child_search);
				child_search.textField.setText(SearchText.getText());
				mainpage.Mainpanel.updateUI();
				mainpage.CurrentPage = 11;
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
		goBox.setBounds(915, 307, 100, 50);
		goBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		add(goBox);
		
		choicepanel = new Choicepanel();
		choicepanel.setPreferredSize(new Dimension(623, 489));
		
		scrollpane = new JScrollPane(choicepanel);
		scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(50);
		scrollpane.setVerticalScrollBar(scrollbar);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(358, 409, 641, 240);
		scrollpane.validate();
		scrollpane.setVisible(false);
		add(scrollpane);
		
		Blast = new JLabel("");
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(SearchText.getText() == null || SearchText.getText().trim().equals("")) return;
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
		Blast.setBounds(1034, 307, 125, 50);
		Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
		add(Blast);
		
		JLabel AddvancedSearch = new JLabel("Addvanced Search");
		AddvancedSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(flag == false){
					scrollpane.setVisible(true);
					flag = true;
				}
				else if(flag == true){
					scrollpane.setVisible(false);
					flag = false;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				AddvancedSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		AddvancedSearch.setFont(new Font("Times New Roman", Font.BOLD, 20));
		AddvancedSearch.setBounds(358, 374, 174, 25);
		add(AddvancedSearch);
	}
}
