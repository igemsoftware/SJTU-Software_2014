package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import data_center.*;

public class InitializeResultPage extends Thread{
	public Child_Search child_search;
	public String keyword;
	public SearchResultList searchresultlist;
	public SearchResultList filteredlist;
	public boolean whethersearched = false;
	public ArrayList<MouseListener> showdetaillist = new ArrayList<MouseListener>();
	public ArrayList<MouseListener> openurllist = new ArrayList<MouseListener>();
	public boolean small;
	
	public InitializeResultPage(Child_Search child_search1, String keyword1, boolean whethersearched1, boolean small1){
		child_search = child_search1;
		keyword = keyword1;
		whethersearched = whethersearched1;
		small = small1;
	}
	
	public InitializeResultPage(Child_Search child_search1, String keyword1, boolean whethersearched1, SearchResultList listtodeal, boolean small1){
		child_search = child_search1;
		keyword = keyword1;
		whethersearched = whethersearched1;
		searchresultlist = listtodeal;
		small = small1;
	}
	
	public void run(){
		JLabel searching = new JLabel("Searching...");
		searching.setFont(new Font("Arial", Font.BOLD, 30));
		searching.setBounds(20, 0, 300, 50);
		child_search.resultpanel.removeAll();
		child_search.resultpanel.add(searching);
		child_search.resultpanel.updateUI();
		
		if(whethersearched == false){
			if(child_search.blast == 1){
				searchresultlist = child_search.searchcenter.search(keyword);
			}
			else if(child_search.blast == 2){
				searchresultlist = child_search.searchcenter.blast(keyword, BlastingSearcher.MODE_INPUT_SEQUENCE);
			}
			else if(child_search.blast == 3){
				searchresultlist = child_search.searchcenter.blast(keyword, BlastingSearcher.MODE_INPUT_FILE_PATH);
			}
		}
		
		filteredlist = searchresultlist;
		int number = filteredlist.size();
		
		//System.out.println(1);
		
		if(number == 0){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Arial", Font.BOLD, 30));
			noresults.setBounds(0, 0, 400, 50);
			child_search.resultpanel.removeAll();
			child_search.resultpanel.add(noresults);
			child_search.resultpanel.updateUI();
			return;
		}
		
		if(child_search.confirmed_clicked == false){
			if(child_search.blast == 2){
				filteredlist.sortByBlastResult(true);
			}
			if(child_search.blast == 1){
				filteredlist.sortByTotalScore(true);
			}
		}
		
		if(child_search.confirmed_clicked == true){
			if(child_search.information.sortby.equals("")){
				filteredlist.sortByTotalScore(true);
			}
			if(child_search.information.sortby.equals("Entered Date")){
				filteredlist.sortByEnterDate(true);
			}
			if(child_search.information.sortby.equals("Google Qoute Number")){
				filteredlist.sortByGoogleQuoteNum(true);
			}
			if(child_search.information.sortby.equals("Average Stars")){
				filteredlist.sortByAverageStars(true);
			}
			if(child_search.information.sortby.equals("Confirmed Times")){
				filteredlist.sortByConfrimedTimes(true);
			}
			if(child_search.information.sortby.equals("Total Score")){
				filteredlist.sortByTotalScore(true);
			}
		
		
			if(child_search.information.type.size() != 0){
				filteredlist = filteredlist.filterByType(child_search.information.type);
			}
		    
			filteredlist = filteredlist.filterByEnterYear(child_search.information.enteredyear);
			
			if(child_search.information.releasestatus.released == true){
				filteredlist = filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.RELEASED);
			}
			if(child_search.information.releasestatus.deleted == true){
				filteredlist = filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.DELETED);
			}
			if(child_search.information.releasestatus.notreleased == true){
				filteredlist = filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.NOT_RELEASED);
			}
		
			if(child_search.information.dnastatus.available == true){
				filteredlist = filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.AVAILABLE);
			}
			if(child_search.information.dnastatus.planning == true){
				filteredlist = filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.PLANNING);
			}
			if(child_search.information.dnastatus.informational == true){
				filteredlist = filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.INFORMATIONAL);
			}
		
			if(child_search.information.whetherornot == false){
				filteredlist = filteredlist.filterByDeletedOrNot(false);
			}
			if(child_search.information.whetherornot == true){
				filteredlist = filteredlist.filterByDeletedOrNot(true);
			}
			
			ArrayList<Integer> starNumList = new ArrayList<Integer>();
		
			if(child_search.information.averagestars.high == true){
				starNumList.add(4);
				starNumList.add(5);
			}
			if(child_search.information.averagestars.middle == true){
				starNumList.add(2);
				starNumList.add(3);
			}
			if(child_search.information.averagestars.low == true){
				starNumList.add(0);
				starNumList.add(1);
			}
			
			if(starNumList.size() != 0){
				filteredlist = filteredlist.filterByStarNum(starNumList);
			}
			
			if(child_search.information.preferences.status + child_search.information.preferences.quality + child_search.information.preferences.feedbacks + child_search.information.preferences.publication == 100){
				filteredlist.sortByTotalScore(true, child_search.information.preferences.status, child_search.information.preferences.quality, child_search.information.preferences.feedbacks, child_search.information.preferences.publication);
			}
		}
		
		final int numberofresults = filteredlist.size();
		
		//System.out.println(2);
		
		if(numberofresults == 0){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Arial", Font.BOLD, 30));
			noresults.setBounds(0, 0, 400, 50);
			child_search.resultpanel.removeAll();
			child_search.resultpanel.add(noresults);
			child_search.resultpanel.updateUI();
			return;
		}
		
		if(numberofresults <= 10 && numberofresults > 0){
			child_search.resultpanel.removeAll();
			child_search.searchingresultpage = new SearchingResultPage(numberofresults);
			child_search.searchingresultpage.setPreferredSize(new Dimension(569,192*numberofresults));
			for(int i = 0; i < numberofresults; i++){
				showresult(i);
			}
			
			child_search.scrollPane = new JScrollPane(child_search.searchingresultpage);
			child_search.scrollbar = new JScrollBar();
			child_search.scrollbar.setUnitIncrement(150);
			child_search.scrollPane.setVerticalScrollBar(child_search.scrollbar);
			child_search.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			child_search.scrollPane.setBounds(0, 0, 587, 520);
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
			child_search.showpagenum.setFont(new Font("Arial", Font.PLAIN, 16));
			child_search.showpagenum.setBounds(281, 530, 25, 25);
			child_search.resultpanel.add(child_search.showpagenum);
			child_search.resultpanel.updateUI();
		}
		
		if(numberofresults > 10){
			child_search.resultpanel.removeAll();
			final int num = (int) numberofresults / 10;
			final int leftnum = numberofresults % 10;
			child_search.currentpage = 1;
			
			child_search.searchingresultpage = new SearchingResultPage();
			child_search.searchingresultpage.setPreferredSize(new Dimension(569,1920));
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
			child_search.previouspage.setFont(new Font("Arial", Font.PLAIN, 16));
			child_search.previouspage.setBounds(95, 530, 120, 25);
			child_search.previouspage.setVisible(false);
			
			child_search.previouspage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1){
						String s;
						child_search.scrollbar.setValue(child_search.scrollbar.getMinimum());
						if(child_search.currentpage > 2){
							child_search.searchingresultpage.setPreferredSize(new Dimension(569,1920));
							for(int i = 10 * (child_search.currentpage - 2); i < 10 * (child_search.currentpage - 1); i++){
								if(showdetaillist.get(i%10) != null){
									child_search.searchingresultpage.searchingresult.get(i%10).ID_Content.removeMouseListener(showdetaillist.get(i%10));
									showdetaillist.remove(i%10);
								}
								if(openurllist.get(i%10) != null){
									child_search.searchingresultpage.searchingresult.get(i%10).URL_Content.removeMouseListener(openurllist.get(i%10));
									openurllist.remove(i%10);
								}
								showresult(i);
							}
							child_search.nextpage.setVisible(true);
							child_search.currentpage--;
							s = "" + child_search.currentpage;
							child_search.showpagenum.setText(s);
							return;
						}
						else if(child_search.currentpage == 2){
							child_search.searchingresultpage.setPreferredSize(new Dimension(569,1920));
							for(int i = 0; i < 10; i++){
								child_search.searchingresultpage.searchingresult.get(i%10).ID_Content.removeMouseListener(showdetaillist.get(i%10));
								showdetaillist.remove(i%10);
								child_search.searchingresultpage.searchingresult.get(i%10).URL_Content.removeMouseListener(openurllist.get(i%10));
								openurllist.remove(i%10);
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
				}
			});
			
			child_search.nextpage = new JLabel("next page>", SwingConstants.CENTER);
			child_search.nextpage.setForeground(Color.blue);
			child_search.nextpage.addMouseListener(new MouseAdapter() {
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
			child_search.nextpage.setFont(new Font("Arial", Font.PLAIN, 16));
			child_search.nextpage.setBounds(373, 530, 85, 25);
			
			child_search.nextpage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1){
						child_search.scrollbar.setValue(child_search.scrollbar.getMinimum());
						String s;
						if(child_search.currentpage < num){
							child_search.searchingresultpage.setPreferredSize(new Dimension(569,1920));
							for(int i = 10 * child_search.currentpage; i < 10 * (child_search.currentpage + 1); i++){
								child_search.searchingresultpage.searchingresult.get(i%10).ID_Content.removeMouseListener(showdetaillist.get(i%10));
								showdetaillist.remove(i%10);
								child_search.searchingresultpage.searchingresult.get(i%10).URL_Content.removeMouseListener(openurllist.get(i%10));
								openurllist.remove(i%10);
								showresult(i);
							}
							child_search.previouspage.setVisible(true);
							child_search.currentpage++;
							s = "" + child_search.currentpage;
							child_search.showpagenum.setText(s);
							return;
						}
						else if(child_search.currentpage == num){
							child_search.searchingresultpage.setPreferredSize(new Dimension(569,192 * leftnum));
							for(int i = 10 * child_search.currentpage; i < numberofresults; i++){
								child_search.searchingresultpage.searchingresult.get(i%10).ID_Content.removeMouseListener(showdetaillist.get(i%10));
								showdetaillist.remove(i%10);
								child_search.searchingresultpage.searchingresult.get(i%10).URL_Content.removeMouseListener(openurllist.get(i%10));
								openurllist.remove(i%10);
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
				}
			});
			
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
			child_search.showpagenum.setFont(new Font("Arial", Font.PLAIN, 16));
			child_search.showpagenum.setBounds(260, 530, 80, 25);
			
			child_search.totalpagenum = new JLabel("");
			child_search.totalpagenum.setForeground(Color.blue);
			String s = null;
			if(numberofresults%10 > 0){
				s = Integer.toString(numberofresults/10 + 1);
			}
			else if(numberofresults%10 == 0){
				s = Integer.toString(numberofresults/10);
			}
			child_search.totalpagenum.setText(s);
			child_search.totalpagenum.setFont(new Font("Arial", Font.PLAIN, 16));
			child_search.totalpagenum.setBounds(530, 530, 80, 25);
			
			child_search.total = new JLabel("total:", SwingConstants.CENTER);
			child_search.total.setForeground(Color.blue);
			child_search.total.setFont(new Font("Arial", Font.PLAIN, 16));
			child_search.total.setBounds(490, 530, 40, 25);
			
			child_search.scrollPane = new JScrollPane(child_search.searchingresultpage);
			child_search.scrollbar = new JScrollBar();
			child_search.scrollbar.setUnitIncrement(150);
			child_search.scrollPane.setVerticalScrollBar(child_search.scrollbar);
			child_search.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			child_search.scrollPane.setBounds(0, 0, 587, 520);
			child_search.scrollPane.validate();
			child_search.resultpanel.add(child_search.scrollPane);
			
			child_search.resultpanel.add(child_search.previouspage);
			child_search.resultpanel.add(child_search.showpagenum);
			child_search.resultpanel.add(child_search.nextpage);
			child_search.resultpanel.add(child_search.totalpagenum);
			child_search.resultpanel.add(child_search.total);
			
			child_search.resultpanel.updateUI();
		}
	}
	
	public void showresult(int j){
		final BbkOutline bbkoutline = filteredlist.get(j);
		int i = j % 10;
		
		MouseListener showdetailpage = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					child_search.Details.removeAll();
					child_search.Details.updateUI();
					InitializeDetail initializedetail = new InitializeDetail(child_search, bbkoutline, small);
					initializedetail.start();
				}
			}
		};
		
		
		showdetaillist.add(i, showdetailpage);;
		
		child_search.searchingresultpage.searchingresult.get(i).ID_Content.addMouseListener(showdetailpage);
		child_search.searchingresultpage.searchingresult.get(i).ID_Content.setText(bbkoutline.name);
		child_search.searchingresultpage.searchingresult.get(i).Type_Content.setText(bbkoutline.type);
		child_search.searchingresultpage.searchingresult.get(i).Author_Content.setText(bbkoutline.author);
		child_search.searchingresultpage.searchingresult.get(i).EnteredDate_Content.setText(bbkoutline.enterDate);
		child_search.searchingresultpage.searchingresult.get(i).UsedTimes_Content.setText(bbkoutline.rating.used_times);
		child_search.searchingresultpage.searchingresult.get(i).URL_Content.setText("<html><u>" + bbkoutline.url + "</u></html>");
		
		MouseListener openurl = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					try{
						//URL url=new URL(child_search.searchingresultpage.searchingresult.get(i).URL_Content.getText().trim());
						//getAppletContext().showDocument(url);
						//Runtime.getRuntime().exec("cmd.exe /c start iexplore " + child_search.searchingresultpage.searchingresult.get(i).URL_Content.getText());
						//URI uri=new java.net.URI(child_search.searchingresultpage.searchingresult.get(i).URL_Content.getText().trim());
						//Desktop.getDesktop().browse(uri);
						Runtime.getRuntime().exec("explorer " + bbkoutline.url);
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		};
		
		openurllist.add(i, openurl);
		
		child_search.searchingresultpage.searchingresult.get(i).URL_Content.addMouseListener(openurl);
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
		child_search.searchingresultpage.searchingresult.get(i).Description.setText(shortdescription);
		
		String score = "" + bbkoutline.getScore();
		char c = '.';
		if(score.charAt(1) == c){
			score = score.substring(0, 4);
		}
		else if(score.charAt(2) == c){
			score = score.substring(0, 5);
		}
		
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
