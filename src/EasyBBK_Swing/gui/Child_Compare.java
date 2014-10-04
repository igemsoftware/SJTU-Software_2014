package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import data_center.*;

public class Child_Compare extends JPanel {
	public MainPage mainpage;
	public JPanel details1;
	public JPanel details2;
	public JPanel details3;
	public JLabel Back2;
	public JLabel Back3;
	public JLabel Forward2;
	public JLabel Forward3;
	public JLabel Search1;
	public JLabel Search2;
	public JLabel Search3;
	public JTextField textField1;
	public JTextField textField2;
	public JTextField textField3;
	public JScrollPane scrollpanel1;
	public JScrollPane scrollpanel2;
	public JScrollPane scrollpanel3;
	public JPanel scrollpanecontainer1;
	public JPanel scrollpanecontainer2;
	public JPanel scrollpanecontainer3;
	public DataCenter datacenter1;
	public DataCenter datacenter2;
	public DataCenter datacenter3;
	public DetailsofResults_Compare detailsofresult1;
	public DetailsofResults_Compare detailsofresult2;
	public DetailsofResults_Compare detailsofresult3;
	/**
	 * Create the panel.
	 */
	public Child_Compare(MainPage mainpage1) {
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		details1 = new JPanel();
		details1.setBounds(0, 0, 455, 670);
		details1.setBackground(new Color(255, 255, 255));
		add(details1);
		details1.setLayout(null);
		
		JLabel Text_Background1 = new JLabel();
		Text_Background1.setBounds(22, 10, 300, 50);
		Text_Background1.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		details1.add(Text_Background1);
		
		Search1 = new JLabel();
		Search1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField1.getText() == null || textField1.getText().trim().equals("")) return;
				scrollpanecontainer1.removeAll();
				InitializeDetailPage initializedetailpage1 = new InitializeDetailPage(textField1.getText(), datacenter1, scrollpanecontainer1, scrollpanel1, detailsofresult1, 0);
				initializedetailpage1.start();
				scrollpanecontainer1.updateUI();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Search1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Search1.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Search1.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		Search1.setBounds(345, 10, 100, 50);
		Search1.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		details1.add(Search1);
		
		datacenter1 = new DataCenter();
		
		textField1 = new JTextField();
		textField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER){
					if(textField1.getText() == null || textField1.getText().trim().equals("")) return;
					scrollpanecontainer1.removeAll();
					InitializeDetailPage initializedetailpage1 = new InitializeDetailPage(textField1.getText(), datacenter1, scrollpanecontainer1, scrollpanel1, detailsofresult1, 0);
					initializedetailpage1.start();
					scrollpanecontainer1.updateUI();
				}
			}
		});
		textField1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField1.setVisible(true);
		textField1.setBounds(10, 5, 282, 32);
		Text_Background1.add(textField1);
		textField1.setColumns(20);
		
		scrollpanecontainer1 = new JPanel();
		scrollpanecontainer1.setBounds(0, 79, 455, 591);
		scrollpanecontainer1.setBackground(new Color(255, 255, 255));
		details1.add(scrollpanecontainer1);
		scrollpanecontainer1.setLayout(null);
		
		
		details2 = new JPanel();
		details2.setBounds(456, 0, 455, 670);
		details2.setBackground(new Color(255, 255, 255));
		add(details2);
		details2.setLayout(null);
		
		JLabel Text_Background2 = new JLabel();
		Text_Background2.setBounds(22, 10, 300, 50);
		Text_Background2.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		details2.add(Text_Background2);
		
		Search2 = new JLabel();
		Search2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField2.getText() == null || textField2.getText().trim().equals("")) return;
				scrollpanecontainer2.removeAll();
				InitializeDetailPage initializedetailpage2 = new InitializeDetailPage(textField2.getText(), datacenter2, scrollpanecontainer2, scrollpanel2, detailsofresult2, 1);
				initializedetailpage2.start();
				scrollpanecontainer2.updateUI();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Search2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Search2.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Search2.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		Search2.setBounds(345, 10, 100, 50);
		Search2.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		details2.add(Search2);
		
		datacenter2 = new DataCenter();
		
		textField2 = new JTextField();
		textField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER){
					if(textField2.getText() == null || textField2.getText().trim().equals("")) return;
					scrollpanecontainer2.removeAll();
					InitializeDetailPage initializedetailpage2 = new InitializeDetailPage(textField2.getText(), datacenter2, scrollpanecontainer2, scrollpanel2, detailsofresult2, 1);
					initializedetailpage2.start();
					scrollpanecontainer2.updateUI();
				}
			}
		});
		textField2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField2.setVisible(true);
		textField2.setBounds(10, 5, 282, 32);
		Text_Background2.add(textField2);
		textField2.setColumns(20);
		
		scrollpanecontainer2 = new JPanel();
		scrollpanecontainer2.setBounds(0, 79, 455, 591);
		scrollpanecontainer2.setBackground(new Color(255, 255, 255));
		details2.add(scrollpanecontainer2);
		scrollpanecontainer2.setLayout(null);
		
		
		details3 = new JPanel();
		details3.setBounds(912, 0, 455, 670);
		details3.setBackground(new Color(255, 255, 255));
		add(details3);
		details3.setLayout(null);
		
		JLabel Text_Background3 = new JLabel();
		Text_Background3.setBounds(22, 10, 300, 50);
		Text_Background3.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		details3.add(Text_Background3);
		
		Search3 = new JLabel();
		Search3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField3.getText() == null || textField3.getText().trim().equals("")) return;
				scrollpanecontainer3.removeAll();
				InitializeDetailPage initializedetailpage3 = new InitializeDetailPage(textField3.getText(), datacenter3, scrollpanecontainer3, scrollpanel3, detailsofresult3, 2);
				initializedetailpage3.start();
				scrollpanecontainer3.updateUI();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Search3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Search3.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Search3.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		Search3.setBounds(345, 10, 100, 50);
		Search3.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		details3.add(Search3);
		
		datacenter3 = new DataCenter();
		
		textField3 = new JTextField();
		textField3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER){
					if(textField3.getText() == null || textField3.getText().trim().equals("")) return;
					scrollpanecontainer3.removeAll();
					InitializeDetailPage initializedetailpage3 = new InitializeDetailPage(textField3.getText(), datacenter3, scrollpanecontainer3, scrollpanel3, detailsofresult3, 2);
					initializedetailpage3.start();
					scrollpanecontainer1.updateUI();
				}
			}
		});
		textField3.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField3.setVisible(true);
		textField3.setBounds(10, 5, 282, 32);
		Text_Background3.add(textField3);
		textField3.setColumns(20);
		
		scrollpanecontainer3 = new JPanel();
		scrollpanecontainer3.setBounds(0, 79, 455, 591);
		scrollpanecontainer3.setBackground(new Color(255, 255, 255));
		details3.add(scrollpanecontainer3);
		scrollpanecontainer3.setLayout(null);
		
		setVisible(true);
	}
	
	public void initializedetailpage(String keyword, DataCenter datacenter, JPanel container, JScrollPane scrollpanel, DetailsofResults_Compare detailsofresults, int num){
		BbkDetail bbkdetail = datacenter.compareCenter.assignDetail(keyword, num);
		
		if(bbkdetail == null){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Times New Roman", Font.BOLD, 30));
			noresults.setBounds(20, 0, 300, 50);
			container.add(noresults);
		}
		else{
			showresult(container, scrollpanel, detailsofresults, bbkdetail);
		}
	}
	
	public void showresult(JPanel container, JScrollPane scrollpanel, DetailsofResults_Compare detailsofresults, BbkDetail bbkdetail){
		detailsofresults = new DetailsofResults_Compare();
		detailsofresults.ID_Content.setText(bbkdetail.name);
		detailsofresults.Type_Content.setText(bbkdetail.type);
		detailsofresults.Author_Content.setText(bbkdetail.author);
		detailsofresults.EnteredDate_Content.setText(bbkdetail.enterDate);
		detailsofresults.URL_Content.setText(bbkdetail.url);
		detailsofresults.ReleasedStatus_Content.setText(bbkdetail.releaseStatus);
		if(bbkdetail.rating.average_stars.equals("No Stars")){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars);
		}
		else if(bbkdetail.rating.average_stars.length() == 1){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars);
		}
		else if(bbkdetail.rating.average_stars.length() >= 3){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars.substring(0,3));
		}
		detailsofresults.ResultsInGoogle_Content.setText(bbkdetail.rating.google_items);
		
		String shortdescription = bbkdetail.shortDesc;
		if(shortdescription.length()<=40){
			detailsofresults.Description1.setText(shortdescription);
			detailsofresults.Description2.setText(null);
		}
		else{
			detailsofresults.Description1.setText(shortdescription.substring(0, 40));
			detailsofresults.Description2.setText(shortdescription.substring(40));
		}
		
		detailsofresults.ResultsInGoogle_Content.setText(bbkdetail.rating.google_items);
		
		String score = "" + bbkdetail.getScore();
		detailsofresults.Score.setText(score);
		
		scrollpanel = new JScrollPane(detailsofresults);
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(100);
		scrollpanel.setVerticalScrollBar(scrollbar);
		scrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel.setBounds(0, 0, 455, 591);
		scrollpanel.validate();
		
		container.removeAll();;
		container.add(scrollpanel);
		container.updateUI();
	}
}
