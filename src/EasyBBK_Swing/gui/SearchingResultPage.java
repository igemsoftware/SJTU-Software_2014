package EasyBBK_Swing.gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SearchingResultPage extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ArrayList<SearchingResult> searchingresult;
	public int num = 10;
	/**
	 * Create the panel.
	 */
	public SearchingResultPage(int number) {
		num = number;
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 569, 192*num);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);
		
		searchingresult = new ArrayList<SearchingResult>();
		
		for(int i = 0; i < num; i++){
			SearchingResult single = new SearchingResult();
			searchingresult.add(single);
			add(single);
			single.setBounds(0, 192*i, 569, 192);
		}
	}
	
	public SearchingResultPage() {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 569, 192*num);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);
		
		searchingresult = new ArrayList<SearchingResult>();
		
		for(int i = 0; i < num; i++){
			SearchingResult single = new SearchingResult();
			searchingresult.add(single);
			add(single);
			single.setBounds(0, 192*i, 569, 192);
		}
	}
}
