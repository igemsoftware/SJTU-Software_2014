package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
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

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import data_center.BbkOutline;
import data_center.SearchResultList;

public class Child_Search extends JPanel {
	public JTextField textField;
	public JPanel Result;
	public JLabel Search;
	public MainPage mainpage;
	public JScrollPane scrollPane;
	private JLabel Blast;
	public JPanel Details;
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
	public static int currentpage = 1;
	/**
	 * Create the panel.
	 */
	public Child_Search(MainPage mainpage1, SearchResultList searchresultlist) {
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
		Back.setBounds(30, 30, 50, 50);
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
		Forward.setBounds(86, 30, 70, 50);
		Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
		Result.add(Forward);
		
		JLabel Text_BackGround = new JLabel();
		Text_BackGround.setBounds(169, 30, 300, 50);
		Text_BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		Result.add(Text_BackGround);
		

		Search = new JLabel();

		Search.setBounds(481, 30, 100, 50);
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
							child_search_main.SearchText.requestFocus();
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
				
			}
		});
		Text_BackGround.add(textField);
		textField.setColumns(20);
		
		previouspage = new JLabel("<previous page", SwingConstants.CENTER);
		previouspage.setForeground(Color.blue);
		previouspage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				previouspage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				previouspage.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				previouspage.setBorder(null);
			}
		});
		previouspage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		previouspage.setBounds(156, 635, 95, 25);
		Result.add(previouspage);
		
		page1 = new JLabel("1", SwingConstants.CENTER);
		page1.setForeground(Color.blue);
		page1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				page1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				page1.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				page1.setBorder(null);
			}
		});
		page1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page1.setBounds(261, 635, 25, 25);
		Result.add(page1);
		
		page2 = new JLabel("2", SwingConstants.CENTER);
		page2.setForeground(Color.blue);
		page2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				page2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				page2.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				page2.setBorder(null);
			}
		});
		page2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page2.setBounds(296, 635, 25, 25);
		Result.add(page2);
		
		page3 = new JLabel("3", SwingConstants.CENTER);
		page3.setForeground(Color.blue);
		page3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				page3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				page3.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				page3.setBorder(null);
			}
		});
		page3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page3.setBounds(331, 635, 25, 25);
		Result.add(page3);
		
		page4 = new JLabel("4", SwingConstants.CENTER);
		page4.setForeground(Color.blue);
		page4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				page4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				page4.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				page4.setBorder(null);
			}
		});
		page4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page4.setBounds(366, 635, 25, 25);
		Result.add(page4);
		
		page5 = new JLabel("5", SwingConstants.CENTER);
		page5.setForeground(Color.blue);
		page5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				page5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				page5.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				page5.setBorder(null);
			}
		});
		page5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		page5.setBounds(401, 635, 25, 25);
		Result.add(page5);
		
		nextpage = new JLabel("next page>", SwingConstants.CENTER);
		nextpage.setForeground(Color.blue);
		nextpage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				nextpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				nextpage.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextpage.setBorder(null);
			}
		});
		nextpage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		nextpage.setBounds(436, 635, 75, 25);
		Result.add(nextpage);
		
		
		SearchingResultPage searchingresultpage = new SearchingResultPage();
		searchingresultpage.setPreferredSize(new Dimension(558,2500));
		
		initializeresultpage(searchingresultpage, searchresultlist);
		
		
		scrollPane = new JScrollPane(searchingresultpage);
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(150);
		scrollPane.setVerticalScrollBar(scrollbar);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(51, 105, 576, 520);
		scrollPane.validate();
		Result.add(scrollPane);
		
		Blast = new JLabel("");
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast1_enter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast1.png")));
			}
		});
		Blast.setBounds(591, 39, 82, 32);
		Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast1.png")));
		Result.add(Blast);
		
		Details = new JPanel();
		Details.setBounds(684, 0, 683, 670);
		Details.setBackground(new Color(255, 255, 255));
		Details.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Details);
		Details.setLayout(null);
	}
	
	public void initializeresultpage(SearchingResultPage searchingresultpage, SearchResultList searchresultlist){
		int numberofresults = searchresultlist.size();
		int num = numberofresults/10;
		
		if(numberofresults <= 10){
			for(int i = 0; i < numberofresults; i++){
				showresult(searchingresultpage, searchresultlist, i);
			}
			previouspage.setVisible(false);
			page1.setForeground(Color.black);
			page2.setVisible(false);
			page3.setVisible(false);
			page4.setVisible(false);
			page5.setVisible(false);
			nextpage.setVisible(false);
		}
		
		if(numberofresults > 10 && numberofresults <= 20){
			for(int i = 0; i < 10; i++){
				showresult(searchingresultpage, searchresultlist, i);
			}
			previouspage.setVisible(false);
			page1.setForeground(Color.black);
			page3.setVisible(false);
			page4.setVisible(false);
			page5.setVisible(false);
			
			previouspage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(currentpage == 2){
						for(int i = 0; i < 10; i++){
							showresult(searchingresultpage, searchresultlist, i);
						}
						page1.setForeground(Color.black);
						page2.setForeground(Color.blue);
						previouspage.setVisible(false);
						nextpage.setVisible(true);
						currentpage = 1;
					}
				}
			});
			
			nextpage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(currentpage == 1){
						for(int i = 10; i < numberofresults; i++){
							showresult(searchingresultpage, searchresultlist, i);
						}
						page1.setForeground(Color.blue);
						page2.setForeground(Color.black);
						nextpage.setVisible(false);
						previouspage.setVisible(true);
						currentpage = 2;
					}
				}
			});
			
			page1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(currentpage == 1) return;
					else{
						for(int i = 0; i < 10; i++){
							showresult(searchingresultpage, searchresultlist, i);
						}
						page1.setForeground(Color.black);
						page2.setForeground(Color.blue);
						previouspage.setVisible(false);
						nextpage.setVisible(true);
						currentpage = 1;
					}
				}
			});
			
			
		}
	}
	
	public void showresult(SearchingResultPage searchingresultpage, SearchResultList searchresultlist, int i){
		
		BbkOutline bbkoutline = searchresultlist.get(i);
		searchingresultpage.searchingresult.get(i).ID_Content.setText(bbkoutline.name);
		searchingresultpage.searchingresult.get(i).Type_Content.setText(bbkoutline.type);
		searchingresultpage.searchingresult.get(i).Author_Content.setText(bbkoutline.author);
		searchingresultpage.searchingresult.get(i).EnteredDate_Content.setText(bbkoutline.enterDate);
		searchingresultpage.searchingresult.get(i).URL_Content.setText(bbkoutline.url);
		searchingresultpage.searchingresult.get(i).ReleasedStatus_Content.setText(bbkoutline.releaseStatus);
		if(bbkoutline.rating.average_stars.equals("No Stars")){
			searchingresultpage.searchingresult.get(i).AverageStar_Content.setText(bbkoutline.rating.average_stars);
		}
		else if(bbkoutline.rating.average_stars.length() == 1){
			searchingresultpage.searchingresult.get(i).AverageStar_Content.setText(bbkoutline.rating.average_stars);
		}
		else if(bbkoutline.rating.average_stars.length() >= 3){
			searchingresultpage.searchingresult.get(i).AverageStar_Content.setText(bbkoutline.rating.average_stars.substring(0,3));
		}
		searchingresultpage.searchingresult.get(i).ResultsInGoogle_Content.setText(bbkoutline.rating.google_items);
		
		String shortdescription = bbkoutline.shortDesc;
		if(shortdescription.length()<=29){
			searchingresultpage.searchingresult.get(i).Description1.setText(shortdescription);
		}
		else{
			searchingresultpage.searchingresult.get(i).Description1.setText(shortdescription.substring(0, 29));
			searchingresultpage.searchingresult.get(i).Description2.setText(shortdescription.substring(29));
		}
		
		String score = "" + bbkoutline.getScore();
		searchingresultpage.searchingresult.get(i).Score.setText(score);
	}
}
