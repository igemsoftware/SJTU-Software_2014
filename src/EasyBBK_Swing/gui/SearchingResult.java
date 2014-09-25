package EasyBBK_Swing.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class SearchingResult extends JPanel{
	public JLabel ID_Content;
	public JLabel Type_Content;
	public JLabel Evalue_Content;
	public JLabel EnteredDate_Content;
	public JLabel Author_Content;
	public JLabel Description1;
	public JLabel Description2;
	public JLabel URL_Content;
	public JLabel ReleasedStatus_Content;
	public JLabel AverageStar_Content;
	public JLabel ResultsInGoogle_Content;
	/**
	 * Create the panel.
	 */
	public SearchingResult(){
		setBackground(new Color(255, 255, 255));
		setBounds(62, 117, 558, 250);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);
		
		JLabel ID = new JLabel("BBa_");
		ID.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ID.setBounds(10, 10, 60, 25);
		add(ID);

		ID_Content = new JLabel("");
		ID_Content.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ID_Content.setBounds(70, 10, 99, 24);
		add(ID_Content);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Type.setBounds(184, 10, 51, 24);
		add(Type);
		
		Type_Content = new JLabel("");
		Type_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Type_Content.setBounds(236, 10, 117, 24);
		add(Type_Content);
		
		JLabel Evalue = new JLabel("E-value:");
		Evalue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Evalue.setBounds(360, 10, 68, 24);
		add(Evalue);
		
		Evalue_Content = new JLabel("");
		Evalue_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Evalue_Content.setBounds(434, 10, 117, 24);
		add(Evalue_Content);
		
		JLabel EnteredDate = new JLabel("Entered Date:");
		EnteredDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		EnteredDate.setBounds(10, 44, 109, 24);
		add(EnteredDate);
		
		EnteredDate_Content = new JLabel("");
		EnteredDate_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		EnteredDate_Content.setBounds(122, 44, 117, 24);
		add(EnteredDate_Content);
		
		JLabel Author = new JLabel("Author:");
		Author.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Author.setBounds(246, 44, 60, 24);
		add(Author);
		
		Author_Content = new JLabel("");
		Author_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Author_Content.setBounds(311, 44, 123, 24);
		add(Author_Content);
		
		JLabel Score = new JLabel("98", JLabel.CENTER);
		Score.setFont(new Font("Times New Roman", Font.BOLD, 90));
		Score.setBounds(444, 44, 107, 93);
		Score.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Score);
		
		JLabel ShortDescription = new JLabel("Short Description:");
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ShortDescription.setBounds(10, 78, 146, 24);
		add(ShortDescription);
		
		Description1 = new JLabel("");
		Description1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Description1.setBounds(160, 78, 274, 24);
		add(Description1);
		
		Description2 = new JLabel("");
		Description2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Description2.setBounds(10, 113, 424, 24);
		add(Description2);
		
		JLabel Url = new JLabel("URL:");
		Url.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Url.setBounds(10, 147, 48, 24);
		add(Url);
		
		URL_Content = new JLabel("");
		URL_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		URL_Content.setBounds(60, 147, 491, 24);
		add(URL_Content);
		
		JLabel ReleasedStatus = new JLabel("Released Status:");
		ReleasedStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ReleasedStatus.setBounds(10, 181, 134, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ReleasedStatus_Content.setBounds(146, 181, 109, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Stars:");
		AverageStars.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageStars.setBounds(271, 181, 124, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageStar_Content.setBounds(400, 181, 109, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Results in Google Scholar(totally confirmed times):");
		ResultsInGoogle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ResultsInGoogle.setBounds(10, 216, 415, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ResultsInGoogle_Content.setBounds(434, 215, 117, 24);
		add(ResultsInGoogle_Content);
	}
}
