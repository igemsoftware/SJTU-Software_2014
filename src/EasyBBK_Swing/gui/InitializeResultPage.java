package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import data_center.*;

public class InitializeResultPage extends Thread{
	public Child_Search child_search;
	public String keyword;
	
	public InitializeResultPage(Child_Search child_search1, String keyword1){
		child_search = child_search1;
		keyword = keyword1;
	}
	
	public void run(){
		JLabel searching = new JLabel("Searching...");
		searching.setFont(new Font("Times New Roman", Font.BOLD, 30));
		searching.setBounds(20, 0, 300, 50);
		child_search.resultpanel.removeAll();
		child_search.resultpanel.add(searching);
		child_search.resultpanel.updateUI();
		
		if(child_search.blast == 1){
			child_search.searchresultlist = child_search.searchcenter.search(keyword);
		}
		else if(child_search.blast == 2){
			child_search.searchresultlist = child_search.searchcenter.blast(keyword, BlastingSearcher.MODE_INPUT_SEQUENCE);
		}
		else if(child_search.blast == 3){
			child_search.searchresultlist = child_search.searchcenter.blast(keyword, BlastingSearcher.MODE_INPUT_FILE_PATH);
		}
		
		child_search.filteredlist = child_search.searchresultlist;
		int number = child_search.filteredlist.size();
		
		if(number == 0){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Times New Roman", Font.BOLD, 30));
			noresults.setBounds(0, 0, 300, 50);
			child_search.resultpanel.removeAll();
			child_search.resultpanel.add(noresults);
			child_search.resultpanel.updateUI();
			return;
		}
		
		if(child_search.blast == 2){
			child_search.filteredlist.sortByBlastResult(true);
		}
		else if(child_search.blast == 1){
			if(child_search.confirmed_clicked == false){
				child_search.filteredlist.sortByTotalScore(true);
			}
			else if(child_search.confirmed_clicked == true){
				if(child_search.information.sortby == "" || child_search.information.sortby.equals("")){
					child_search.filteredlist.sortByTotalScore(true);
				}
				else if(child_search.information.sortby.equals("Entered Date")){
					child_search.filteredlist.sortByEnterDate(true);
				}
				else if(child_search.information.sortby.equals("Google Qoute Number")){
					child_search.filteredlist.sortByGoogleQuoteNum(true);
				}
				else if(child_search.information.sortby.equals("Average Stars")){
					child_search.filteredlist.sortByAverageStars(true);
				}
				else if(child_search.information.sortby.equals("Confirmed Times")){
					child_search.filteredlist.sortByConfrimedTimes(true);
				}
				else if(child_search.information.sortby.equals("Total Score")){
					child_search.filteredlist.sortByTotalScore(true);
				}
				
				child_search.filteredlist = child_search.filteredlist.filterByType(child_search.information.type);
				child_search.filteredlist = child_search.filteredlist.filterByEnterYear(child_search.information.enteredyear);
				
				if(child_search.information.releasestatus.released == true){
					child_search.filteredlist = child_search.filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.RELEASED);
				}
				if(child_search.information.releasestatus.deleted == true){
					child_search.filteredlist = child_search.filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.DELETED);
				}
				if(child_search.information.releasestatus.notreleased == true){
					child_search.filteredlist = child_search.filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.NOT_RELEASED);
				}
				
				if(child_search.information.dnastatus.available == true){
					child_search.filteredlist = child_search.filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.AVAILABLE);
				}
				if(child_search.information.dnastatus.planning == true){
					child_search.filteredlist = child_search.filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.PLANNING);
				}
				if(child_search.information.dnastatus.informational == true){
					child_search.filteredlist = child_search.filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.INFORMATIONAL);
				}
				
				if(child_search.information.whetherornot == false){
					child_search.filteredlist = child_search.filteredlist.filterByDeletedOrNot(false);
				}
				if(child_search.information.whetherornot == true){
					child_search.filteredlist = child_search.filteredlist.filterByDeletedOrNot(true);
				}
				
				if(child_search.information.averagestars.high == true){
					ArrayList<Integer> starNumList = new ArrayList<Integer>();
					starNumList.add(4);
					starNumList.add(5);
					child_search.filteredlist = child_search.filteredlist.filterByStarNum(starNumList);
				}
				if(child_search.information.averagestars.middle == true){
					ArrayList<Integer> starNumList = new ArrayList<Integer>();
					starNumList.add(2);
					starNumList.add(3);
					child_search.filteredlist = child_search.filteredlist.filterByStarNum(starNumList);
				}
				if(child_search.information.averagestars.low == true){
					ArrayList<Integer> starNumList = new ArrayList<Integer>();
					starNumList.add(0);
					starNumList.add(1);
					child_search.filteredlist = child_search.filteredlist.filterByStarNum(starNumList);
				}
				
				child_search.filteredlist.sortByTotalScore(true, child_search.information.preferences.status, child_search.information.preferences.quality, child_search.information.preferences.feedbacks, child_search.information.preferences.publication);
			}
		}
		
		int numberofresults = child_search.filteredlist.size();
		
		if(numberofresults == 0){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Times New Roman", Font.BOLD, 30));
			noresults.setBounds(0, 0, 300, 50);
			child_search.resultpanel.removeAll();
			child_search.resultpanel.add(noresults);
			child_search.resultpanel.updateUI();
			return;
		}
		
		if(numberofresults <= 10 && numberofresults > 0){
			child_search.resultpanel.removeAll();
			child_search.searchingresultpage = new SearchingResultPage(numberofresults);
			child_search.searchingresultpage.setPreferredSize(new Dimension(558,250*numberofresults));
			for(int i = 0; i < numberofresults; i++){
				showresult(i);
			}
			
			child_search.scrollPane = new JScrollPane(child_search.searchingresultpage);
			child_search.scrollbar = new JScrollBar();
			child_search.scrollbar.setUnitIncrement(150);
			child_search.scrollPane.setVerticalScrollBar(child_search.scrollbar);
			child_search.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			child_search.scrollPane.setBounds(0, 0, 576, 520);
			child_search.scrollPane.validate();
			child_search.resultpanel.add(child_search.scrollPane);
			
			child_search.showpagenum = new JLabel("1", SwingConstants.CENTER);
			child_search.showpagenum.setForeground(Color.blue);
			child_search.showpagenum.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					child_search.showpagenum.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					child_search.showpagenum.setBorder(BorderFactory.createLineBorder(Color.blue));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					child_search.showpagenum.setBorder(null);
				}
			});
			child_search.showpagenum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			child_search.showpagenum.setBounds(281, 530, 25, 25);
			child_search.resultpanel.add(child_search.showpagenum);
			child_search.resultpanel.updateUI();
		}
		
		if(numberofresults > 10){
			child_search.resultpanel.removeAll();
			int num = (int) numberofresults / 10;
			int leftnum = numberofresults % 10;
			child_search.currentpage = 1;
			
			child_search.searchingresultpage = new SearchingResultPage();
			child_search.searchingresultpage.setPreferredSize(new Dimension(558,2500));
			for(int i = 0; i < 10; i++){
				showresult(i);
			}
			
			child_search.previouspage = new JLabel("<previous page", SwingConstants.CENTER);
			child_search.previouspage.setForeground(Color.blue);
			child_search.previouspage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					child_search.previouspage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					child_search.previouspage.setBorder(BorderFactory.createLineBorder(Color.blue));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					child_search.previouspage.setBorder(null);
				}
			});
			child_search.previouspage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			child_search.previouspage.setBounds(119, 530, 95, 25);
			child_search.previouspage.setVisible(false);
			
			child_search.previouspage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String s;
					child_search.scrollbar.setValue(child_search.scrollbar.getMinimum());
					if(child_search.currentpage > 2){
						child_search.searchingresultpage.setPreferredSize(new Dimension(558,2500));
						for(int i = 10 * (child_search.currentpage - 2); i < 10 * (child_search.currentpage - 1); i++){
							showresult(i);
						}
						child_search.nextpage.setVisible(true);
						child_search.currentpage--;
						s = "" + child_search.currentpage;
						child_search.showpagenum.setText(s);
						return;
					}
					else if(child_search.currentpage == 2){
						child_search.searchingresultpage.setPreferredSize(new Dimension(558,2500));
						for(int i = 0; i < 10; i++){
							showresult(i);
						}
						child_search.nextpage.setVisible(true);
						child_search.previouspage.setVisible(false);
						child_search.currentpage--;
						s = "" + child_search.currentpage;
						child_search.showpagenum.setText(s);
						return;
					}
				}
			});
			
			child_search.nextpage = new JLabel("next page>", SwingConstants.CENTER);
			child_search.nextpage.setForeground(Color.blue);
			child_search.nextpage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					child_search.nextpage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					child_search.nextpage.setBorder(BorderFactory.createLineBorder(Color.blue));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					child_search.nextpage.setBorder(null);
				}
			});
			child_search.nextpage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			child_search.nextpage.setBounds(373, 530, 75, 25);
			
			child_search.nextpage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					child_search.scrollbar.setValue(child_search.scrollbar.getMinimum());
					String s;
					if(child_search.currentpage < num){
						child_search.searchingresultpage.setPreferredSize(new Dimension(558,2500));
						for(int i = 10 * child_search.currentpage; i < 10 * (child_search.currentpage + 1); i++){
							showresult(i);
						}
						child_search.previouspage.setVisible(true);
						child_search.currentpage++;
						s = "" + child_search.currentpage;
						child_search.showpagenum.setText(s);
						return;
					}
					else if(child_search.currentpage == num){
						child_search.searchingresultpage.setPreferredSize(new Dimension(558,250 * leftnum));
						for(int i = 10 * child_search.currentpage; i < numberofresults; i++){
							showresult(i);
						}
						child_search.previouspage.setVisible(true);
						child_search.nextpage.setVisible(false);
						child_search.currentpage++;
						s = "" + child_search.currentpage;
						child_search.showpagenum.setText(s);
						return;
					}
				}
			});
			
			child_search.showpagenum = new JLabel("1", SwingConstants.CENTER);
			child_search.showpagenum.setForeground(Color.blue);
			child_search.showpagenum.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					child_search.showpagenum.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					child_search.showpagenum.setBorder(BorderFactory.createLineBorder(Color.blue));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					child_search.showpagenum.setBorder(null);
				}
			});
			child_search.showpagenum.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			child_search.showpagenum.setBounds(281, 530, 25, 25);
			
			child_search.scrollPane = new JScrollPane(child_search.searchingresultpage);
			child_search.scrollbar = new JScrollBar();
			child_search.scrollbar.setUnitIncrement(150);
			child_search.scrollPane.setVerticalScrollBar(child_search.scrollbar);
			child_search.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			child_search.scrollPane.setBounds(0, 0, 576, 520);
			child_search.scrollPane.validate();
			child_search.resultpanel.add(child_search.scrollPane);
			
			child_search.resultpanel.add(child_search.previouspage);
			child_search.resultpanel.add(child_search.showpagenum);
			child_search.resultpanel.add(child_search.nextpage);
			
			child_search.resultpanel.updateUI();
		}
	}
	
	public void showresult(int j){
		BbkOutline bbkoutline = child_search.filteredlist.get(j);
		int i = j % 10;
		child_search.searchingresultpage.searchingresult.get(i).ID_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InitializeDetail initializedetail = new InitializeDetail(child_search, bbkoutline);
				initializedetail.start();
			}
		});
		child_search.searchingresultpage.searchingresult.get(i).ID_Content.setText(bbkoutline.name);
		child_search.searchingresultpage.searchingresult.get(i).Type_Content.setText(bbkoutline.type);
		child_search.searchingresultpage.searchingresult.get(i).Author_Content.setText(bbkoutline.author);
		child_search.searchingresultpage.searchingresult.get(i).EnteredDate_Content.setText(bbkoutline.enterDate);
		child_search.searchingresultpage.searchingresult.get(i).URL_Content.setText(bbkoutline.url);
		child_search.searchingresultpage.searchingresult.get(i).ReleasedStatus_Content.setText(bbkoutline.releaseStatus);
		if(bbkoutline.rating.average_stars.equals("No Stars")){
			child_search.searchingresultpage.searchingresult.get(i).AverageStar_Content.setText(bbkoutline.rating.average_stars);
		}
		else if(bbkoutline.rating.average_stars.length() == 1){
			child_search.searchingresultpage.searchingresult.get(i).AverageStar_Content.setText(bbkoutline.rating.average_stars);
		}
		else if(bbkoutline.rating.average_stars.length() >= 3){
			child_search.searchingresultpage.searchingresult.get(i).AverageStar_Content.setText(bbkoutline.rating.average_stars.substring(0,3));
		}
		child_search.searchingresultpage.searchingresult.get(i).ResultsInGoogle_Content.setText(bbkoutline.rating.google_items);
		
		String shortdescription = bbkoutline.shortDesc;
		if(shortdescription.length()<=29){
			child_search.searchingresultpage.searchingresult.get(i).Description1.setText(shortdescription);
			child_search.searchingresultpage.searchingresult.get(i).Description2.setText(null);
		}
		else{
			child_search.searchingresultpage.searchingresult.get(i).Description1.setText(shortdescription.substring(0, 29));
			child_search.searchingresultpage.searchingresult.get(i).Description2.setText(shortdescription.substring(29));
		}
		
		String score = "" + bbkoutline.getScore();
		child_search.searchingresultpage.searchingresult.get(i).Score.setText(score);
		
		if(child_search.blast == 1){
			child_search.searchingresultpage.searchingresult.get(i).Evalue.setVisible(false);
		}
		else if(child_search.blast == 2){
			child_search.searchingresultpage.searchingresult.get(i).Evalue.setVisible(true);
			String s = "" + bbkoutline.blasting.eValue;
			child_search.searchingresultpage.searchingresult.get(i).Evalue_Content.setText(s);
		}
	}
}
