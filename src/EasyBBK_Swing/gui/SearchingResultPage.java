package EasyBBK_Swing.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SearchingResultPage extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public SearchingResultPage() {
		setBackground(new Color(255, 255, 255));
		setBounds(62, 117, 558, 500);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);
		
		SearchingResult searchingresult1 = new SearchingResult();
		add(searchingresult1);
		searchingresult1.setBounds(0, 0, 558, 250);
		
		SearchingResult searchingresult2 = new SearchingResult();
		add(searchingresult2);
		searchingresult2.setBounds(0, 250, 558, 250);
	}

}
