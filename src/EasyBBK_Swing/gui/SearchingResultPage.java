package EasyBBK_Swing.gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SearchingResultPage extends JPanel {
	public ArrayList<SearchingResult> searchingresult;
	public int num = 10;
	/**
	 * Create the panel.
	 */
	public SearchingResultPage(int number) {
		num = number;
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 558, 250*num);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);
		
		searchingresult = new ArrayList<SearchingResult>();
		
		for(int i = 0; i < num; i++){
			SearchingResult single = new SearchingResult();
			searchingresult.add(single);
			add(single);
			single.setBounds(0, 250*i, 558, 250);
		}
	}
	
	public SearchingResultPage() {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 558, 250*num);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);
		
		searchingresult = new ArrayList<SearchingResult>();
		
		for(int i = 0; i < num; i++){
			SearchingResult single = new SearchingResult();
			searchingresult.add(single);
			add(single);
			single.setBounds(0, 250*i, 558, 250);
		}
	}
}
