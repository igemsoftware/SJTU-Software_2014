package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

public class DetailsofResults extends JPanel {
	public JLabel ID_Content;
	public JLabel Type_Content;
	public JLabel Evalue_Content;
	public JLabel EnteredDate_Content;
	public JLabel Author_Content;
	public JLabel Description1;
	public JLabel URL_Content;
	public JLabel ReleasedStatus_Content;
	public JLabel AverageStar_Content;
	public JLabel ResultsInGoogle_Content;
	public JLabel Score;
	public JLabel Description2;
	/**
	 * Create the panel.
	 */
	public DetailsofResults() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
			}
		});
		setBounds(0, 0, 665, 1500);
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		ID_Content = new JLabel("");
		ID_Content.setFont(new Font("Times New Roman", Font.BOLD, 24));
		ID_Content.setBounds(10, 10, 159, 24);
		add(ID_Content);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Type.setBounds(10, 44, 51, 24);
		add(Type);
		
		Type_Content = new JLabel("");
		Type_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Type_Content.setBounds(59, 44, 148, 24);
		add(Type_Content);
		
		JLabel Evalue = new JLabel("E-value:");
		Evalue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Evalue.setBounds(10, 78, 68, 24);
		add(Evalue);
		
		Evalue_Content = new JLabel("");
		Evalue_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Evalue_Content.setBounds(81, 78, 117, 24);
		add(Evalue_Content);
		
		JLabel EnteredDate = new JLabel("Entered Date:");
		EnteredDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		EnteredDate.setBounds(10, 112, 112, 24);
		add(EnteredDate);
		
		EnteredDate_Content = new JLabel("");
		EnteredDate_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		EnteredDate_Content.setBounds(122, 112, 117, 24);
		add(EnteredDate_Content);
		
		JLabel Author = new JLabel("Author:");
		Author.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Author.setBounds(10, 146, 60, 24);
		add(Author);
		
		Author_Content = new JLabel("");
		Author_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Author_Content.setBounds(74, 146, 581, 24);
		add(Author_Content);
		
		JLabel ShortDescription = new JLabel("Short Description:");
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ShortDescription.setBounds(10, 180, 146, 24);
		add(ShortDescription);
		
		Description1 = new JLabel("");
		Description1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Description1.setBounds(161, 180, 494, 24);
		add(Description1);
		
		JLabel URL = new JLabel("URL:");
		URL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		URL.setBounds(10, 248, 48, 24);
		add(URL);
		
		URL_Content = new JLabel("");
		URL_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					Runtime.getRuntime().exec("explorer " + URL_Content.getText());
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				URL_Content.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				URL_Content.setForeground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				URL_Content.setForeground(Color.black);
			}
		});
		URL_Content.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		URL_Content.setBounds(59, 248, 437, 24);
		add(URL_Content);
		
		JLabel ReleasedStatus = new JLabel("Released Status:");
		ReleasedStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ReleasedStatus.setBounds(10, 282, 134, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ReleasedStatus_Content.setBounds(143, 282, 290, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Stars:");
		AverageStars.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageStars.setBounds(10, 316, 124, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageStar_Content.setBounds(132, 316, 107, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Results in Google Scholar:");
		ResultsInGoogle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ResultsInGoogle.setBounds(10, 350, 216, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ResultsInGoogle_Content.setBounds(229, 350, 51, 24);
		add(ResultsInGoogle_Content);
		
		Score = new JLabel("", SwingConstants.CENTER);
		Score.setFont(new Font("Times New Roman", Font.BOLD, 96));
		Score.setBorder(BorderFactory.createLineBorder(Color.black));
		Score.setBounds(509, 26, 124, 110);
		add(Score);
		
		JLabel test = new JLabel("test");
		test.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		test.setBounds(10, 1000, 100, 24);
		add(test);
		
		Description2 = new JLabel("");
		Description2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Description2.setBounds(10, 214, 645, 24);
		add(Description2);
	}
}
