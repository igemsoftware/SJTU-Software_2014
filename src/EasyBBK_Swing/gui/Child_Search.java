package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
	public JScrollPane scrollPane1;
	private JLabel Blast;
	public JPanel Details;
	public JLabel page6;
	public JLabel page7;
	public JLabel page8;
	public JLabel page9;
	public JLabel page10;
	public int currentpage = 1;
	public SearchingResultPage searchingresultpage;
	public SearchResultList searchresultlist;
	public JScrollBar scrollbar;
	public JScrollBar scrollbar1;
	public SearchCenter searchcenter;
	public Information information;
	public int blast;
	public JPanel resultpanel;
	public JLabel previouspage;
	public JLabel nextpage;
	public JLabel showpagenum;
	public SearchResultList filteredlist;
	public boolean confirmed_clicked;
	/**
	 * Create the panel.
	 */
	public Child_Search(MainPage mainpage1, String searchcontent, Information information1, int blast1, boolean confirmed_clicked1) {
		confirmed_clicked = confirmed_clicked1;
		searchcenter = new SearchCenter();
		blast = blast1;
		information = information1;
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
				SearchResultList previousList = searchcenter.rollBack();
				if(previousList == null) return;
				else{
					textField.setText(previousList.keyword);
					resultpanel.removeAll();
					initializeresultpage(previousList);
					resultpanel.updateUI();
				}
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
				SearchResultList forwardList = searchcenter.goForward();
				if(forwardList == null) return;
				else{
					textField.setText(forwardList.keyword);
					resultpanel.removeAll();
					initializeresultpage(forwardList);
					resultpanel.updateUI();
				}
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
					searchresultlist = searchcenter.search(textField.getText());
					currentpage = 1;
					blast = 1;
					resultpanel.removeAll();
					initializeresultpage(searchresultlist);
					resultpanel.updateUI();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
						searchresultlist = searchcenter.search(textField.getText());
						currentpage = 1;
						blast = 1;
						resultpanel.removeAll();
						initializeresultpage(searchresultlist);
						resultpanel.updateUI();
					}
				}
			}
		});
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField.setVisible(true);
		textField.setBounds(10, 5, 283, 32);
		Text_BackGround.add(textField);
		textField.setColumns(20);
		
		resultpanel = new JPanel();
		resultpanel.setBackground(new Color(255, 255, 255));
		resultpanel.setBounds(51, 105, 576, 565);
		Result.add(resultpanel);
		resultpanel.setLayout(null);
		
		
		Blast = new JLabel("");
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
					searchresultlist = searchcenter.blast(textField.getText(), BlastingSearcher.MODE_INPUT_SEQUENCE);
					currentpage = 1;
					blast = 2;
					resultpanel.removeAll();
					initializeresultpage(searchresultlist);
					resultpanel.updateUI();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Blast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		updateUI();
		
		if(blast == 1){
			searchresultlist = searchcenter.search(searchcontent);
		}
		else if(blast == 2){
			searchresultlist = searchcenter.blast(searchcontent, BlastingSearcher.MODE_INPUT_SEQUENCE);
		}
		else if(blast == 3){
			searchresultlist = searchcenter.blast(searchcontent, BlastingSearcher.MODE_INPUT_FILE_PATH);
		}
		
		initializeresultpage(searchresultlist);
	}
	
	public void initializeresultpage(SearchResultList searchresultlist1){
		filteredlist = searchresultlist1;
		int numberofresults = filteredlist.size();
		
		if(numberofresults == 0){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Times New Roman", Font.BOLD, 30));
			noresults.setBounds(0, 0, 300, 50);
			resultpanel.add(noresults);
		}
		
		if(blast == 2){
			filteredlist.sortByBlastResult(true);
		}
		else if(blast == 1){
			if(confirmed_clicked == false){
				filteredlist.sortByTotalScore(true);
			}
			else if(confirmed_clicked == true){
				if(information.sortby == "" || information.sortby.trim().equals("")){
					filteredlist.sortByTotalScore(true);
				}
				else if(information.sortby == "Entered Date"){
					filteredlist.sortByEnterDate(true);
				}
				else if(information.sortby == "Google Qoute Number"){
					filteredlist.sortByGoogleQuoteNum(true);
				}
				else if(information.sortby == "Average Stars"){
					filteredlist.sortByAverageStars(true);
				}
				else if(information.sortby == "Confirmed Times"){
					filteredlist.sortByConfrimedTimes(true);
				}
				else if(information.sortby == "Total Score"){
					filteredlist.sortByTotalScore(true);
				}
				
				filteredlist.filterByType(information.type);
				filteredlist.filterByEnterYear(information.enteredyear);
				
				if(information.releasestatus.released == true){
					filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.RELEASED);
				}
				if(information.releasestatus.deleted == true){
					filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.DELETED);
				}
				if(information.releasestatus.notreleased == true){
					filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.NOT_RELEASED);
				}
				
				if(information.dnastatus.available == true){
					filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.AVAILABLE);
				}
				if(information.dnastatus.planning == true){
					filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.PLANNING);
				}
				if(information.dnastatus.informational == true){
					filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.INFORMATIONAL);
				}
				
				if(information.whetherornot == false){
					filteredlist.filterByDeletedOrNot(false);
				}
				if(information.whetherornot == true){
					filteredlist.filterByDeletedOrNot(true);
				}
				
				if(information.averagestars.high == true){
					ArrayList<Integer> starNumList = new ArrayList();
					starNumList.add(4);
					starNumList.add(5);
					filteredlist.filterByStarNum(starNumList);
				}
				if(information.averagestars.middle == true){
					ArrayList<Integer> starNumList = new ArrayList();
					starNumList.add(2);
					starNumList.add(3);
					filteredlist.filterByStarNum(starNumList);
				}
				if(information.averagestars.low == true){
					ArrayList<Integer> starNumList = new ArrayList();
					starNumList.add(0);
					starNumList.add(1);
					filteredlist.filterByStarNum(starNumList);
				}
				
				filteredlist.sortByTotalScore(true, information.preferences.status, information.preferences.quality, information.preferences.feedbacks, information.preferences.publication);
			}
		}
		
		if(numberofresults <= 10 && numberofresults > 0){
			searchingresultpage = new SearchingResultPage(numberofresults);
			searchingresultpage.setPreferredSize(new Dimension(558,250*numberofresults));
			for(int i = 0; i < numberofresults; i++){
				showresult(i);
			}
			
			scrollPane = new JScrollPane(searchingresultpage);
			scrollbar = new JScrollBar();
			scrollbar.setUnitIncrement(150);
			scrollPane.setVerticalScrollBar(scrollbar);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(0, 0, 576, 520);
			scrollPane.validate();
			resultpanel.add(scrollPane);
			
			showpagenum = new JLabel("1", SwingConstants.CENTER);
			showpagenum.setForeground(Color.blue);
			showpagenum.addMouseListener(new MouseAdapter() {
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
			showpagenum.setBounds(281, 530, 25, 25);
			resultpanel.add(showpagenum);
		}
		
		if(numberofresults > 10){
			int num = (int) numberofresults / 10;
			int leftnum = numberofresults % 10;
			currentpage = 1;
			
			searchingresultpage = new SearchingResultPage();
			searchingresultpage.setPreferredSize(new Dimension(558,2500));
			for(int i = 0; i < 10; i++){
				showresult(i);
			}
			
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
			previouspage.setBounds(119, 530, 95, 25);
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
			nextpage.setBounds(373, 530, 75, 25);
			
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
			showpagenum.setBounds(281, 530, 25, 25);
			
			scrollPane = new JScrollPane(searchingresultpage);
			scrollbar = new JScrollBar();
			scrollbar.setUnitIncrement(150);
			scrollPane.setVerticalScrollBar(scrollbar);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(0, 0, 576, 520);
			scrollPane.validate();
			resultpanel.add(scrollPane);
			
			resultpanel.add(previouspage);
			resultpanel.add(showpagenum);
			resultpanel.add(nextpage);
		}
	}
	
	public void showresult(int j){
		BbkOutline bbkoutline = filteredlist.get(j);
		int i = j % 10;
		searchingresultpage.searchingresult.get(i).ID_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BbkDetail bbkdetail = searchcenter.getDetail(bbkoutline.name);
				DetailsofResults detailsofresults = new DetailsofResults();
				
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
				String score = "" + bbkdetail.getScore();
				detailsofresults.Score.setText(score);
				
				detailsofresults.setPreferredSize(new Dimension(665, 1500));
				scrollPane1 = new JScrollPane(detailsofresults);
				scrollbar1 = new JScrollBar();
				scrollbar1.setUnitIncrement(100);
				scrollPane1.setVerticalScrollBar(scrollbar1);
				scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane1.setBounds(0, 0, 683, 670);
				scrollPane1.validate();
				
				Details.removeAll();;
				Details.add(scrollPane1);
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
			searchingresultpage.searchingresult.get(i).Description2.setText(null);
		}
		else{
			searchingresultpage.searchingresult.get(i).Description1.setText(shortdescription.substring(0, 29));
			searchingresultpage.searchingresult.get(i).Description2.setText(shortdescription.substring(29));
		}
		
		String score = "" + bbkoutline.getScore();
		searchingresultpage.searchingresult.get(i).Score.setText(score);
		
		if(blast == 1){
			searchingresultpage.searchingresult.get(i).Evalue.setVisible(false);
		}
		else if(blast == 2){
			searchingresultpage.searchingresult.get(i).Evalue.setVisible(true);
			String s = "" + bbkoutline.blasting.eValue;
			searchingresultpage.searchingresult.get(i).Evalue_Content.setText(s);
		}
	}
}