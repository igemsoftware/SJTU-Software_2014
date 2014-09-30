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

import data_center.*;

public class Child_Search extends JPanel {
	public JTextField textField;
	public JPanel Result;
	public JLabel Search;
	public MainPage mainpage;
	public JScrollPane scrollPane;
	private JLabel Blast;
	public JPanel Details;
	public JLabel previouspage;
	public JLabel showpagenum;
	public JLabel page6;
	public JLabel page7;
	public JLabel page8;
	public JLabel page9;
	public JLabel page10;
	public JLabel nextpage;
	public int currentpage = 1;
	public SearchingResultPage searchingresultpage;
	public SearchResultList searchresultlist;
	public JScrollBar scrollbar;
	/**
	 * Create the panel.
	 */
	public Child_Search(MainPage mainpage1, String searchcontent) {
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
					}
					return;
				}
				else{
					searchresultlist = BbkDatabaseConnector.search(textField.getText());
					currentpage = 1;
					String s;
					s = "" + currentpage;
					showpagenum.setText(s);
					initializeresultpage();
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
						}
						return;
					}
					else{
						searchresultlist = BbkDatabaseConnector.search(textField.getText());
						currentpage = 1;
						String s;
						s = "" + currentpage;
						showpagenum.setText(s);
						initializeresultpage();
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
		
		showpagenum = new JLabel("1", SwingConstants.CENTER);
		showpagenum.setForeground(Color.blue);
		showpagenum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				showpagenum.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				showpagenum.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				showpagenum.setBorder(null);
			}
		});
		showpagenum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		showpagenum.setBounds(331, 635, 25, 25);
		Result.add(showpagenum);
		
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
		
		//searchresultlist = BbkDatabaseConnector.search(searchcontent);
		SearchCenter searchcenter = new SearchCenter();
		searchresultlist = searchcenter.search(searchcontent);
		BbkDetail bbkdetail = new BbkDetail();
		initializeresultpage();
		
		
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
	
	public void initializeresultpage(){
		searchingresultpage = new SearchingResultPage();
		
		scrollPane = new JScrollPane(searchingresultpage);
		scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(150);
		scrollPane.setVerticalScrollBar(scrollbar);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(51, 105, 576, 520);
		scrollPane.validate();
		Result.add(scrollPane);
		
		int numberofresults = searchresultlist.size();
		
		if(numberofresults <= 10){
			searchingresultpage = new SearchingResultPage(numberofresults);
			searchingresultpage.setPreferredSize(new Dimension(558,250*numberofresults));
			for(int i = 0; i < numberofresults; i++){
				showresult(i);
			}
			previouspage.setVisible(false);
			nextpage.setVisible(false);
		}
		
		
		if(numberofresults > 10){
			int num = (int) numberofresults / 10;
			int leftnum = numberofresults % 10;
			currentpage = 1;
			
			searchingresultpage.setPreferredSize(new Dimension(558,2500));
			for(int i = 0; i < 10; i++){
				showresult(i);
			}
			previouspage.setVisible(false);
			
			previouspage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String s;
					scrollbar.setValue(scrollbar.getMinimum());
					if(currentpage > 2){
						searchingresultpage.setPreferredSize(new Dimension(558,2500));
						for(int i = 10 * (currentpage - 2); i < 10 * (currentpage - 1); i++){
							showresult(i);
						}
						nextpage.setVisible(true);
						currentpage--;
						s = "" + currentpage;
						showpagenum.setText(s);
						return;
					}
					else if(currentpage == 2){
						searchingresultpage.setPreferredSize(new Dimension(558,2500));
						for(int i = 0; i < 10; i++){
							showresult(i);
						}
						nextpage.setVisible(true);
						previouspage.setVisible(false);
						currentpage--;
						s = "" + currentpage;
						showpagenum.setText(s);
						return;
					}
				}
			});
			
			nextpage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					scrollbar.setValue(scrollbar.getMinimum());
					String s;
					if(currentpage < num){
						searchingresultpage.setPreferredSize(new Dimension(558,2500));
						for(int i = 10 * currentpage; i < 10 * (currentpage + 1); i++){
							showresult(i);
						}
						previouspage.setVisible(true);
						currentpage++;
						s = "" + currentpage;
						showpagenum.setText(s);
						return;
					}
					else if(currentpage == num){
						searchingresultpage.setPreferredSize(new Dimension(558,250 * leftnum));
						for(int i = 10 * currentpage; i < numberofresults; i++){
							showresult(i);
						}
						previouspage.setVisible(true);
						nextpage.setVisible(false);
						currentpage++;
						s = "" + currentpage;
						showpagenum.setText(s);
						return;
					}
				}
			});
			
		}
	}
	
	public void showresult(int j){
		
		BbkOutline bbkoutline = searchresultlist.get(j);
		int i = j % 10;
		searchingresultpage.searchingresult.get(i).ID_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DetailsofResults detailsofresults = new DetailsofResults();
				detailsofresults.ID_Content.setText(bbkoutline.name);
				detailsofresults.Type_Content.setText(bbkoutline.type);
				detailsofresults.Author_Content.setText(bbkoutline.author);
				detailsofresults.EnteredDate_Content.setText(bbkoutline.enterDate);
				detailsofresults.URL_Content.setText(bbkoutline.url);
				detailsofresults.ReleasedStatus_Content.setText(bbkoutline.releaseStatus);
				if(bbkoutline.rating.average_stars.equals("No Stars")){
					detailsofresults.AverageStar_Content.setText(bbkoutline.rating.average_stars);
				}
				else if(bbkoutline.rating.average_stars.length() == 1){
					detailsofresults.AverageStar_Content.setText(bbkoutline.rating.average_stars);
				}
				else if(bbkoutline.rating.average_stars.length() >= 3){
					detailsofresults.AverageStar_Content.setText(bbkoutline.rating.average_stars.substring(0,3));
				}
				detailsofresults.ResultsInGoogle_Content.setText(bbkoutline.rating.google_items);
				detailsofresults.Description.setText(bbkoutline.shortDesc);
				String score = "" + bbkoutline.getScore();
				//detailsofresults.Score.setText(score);
				
				Details.removeAll();
				Details.add(detailsofresults);
				Details.updateUI();
			}
		});
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
	
	public void showdetails(){
		
	}
}
