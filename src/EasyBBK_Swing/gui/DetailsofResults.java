package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class DetailsofResults extends JPanel {
	public JLabel ID_Content;
	public JLabel Type_Content;
	public JLabel EnteredDate_Content;
	public JLabel Author_Content;
	public JLabel Description1;
	public JLabel URL_Content;
	public JLabel ReleasedStatus_Content;
	public JLabel AverageStar_Content;
	public JLabel ResultsInGoogle_Content;
	public JLabel Score;
	public JLabel Description2;
	public JCheckBox Addforcomparison;
	public JLabel related_URL;
	public JLabel SampleStatus_Content;
	public JLabel DNAStatus_Content;
	public JLabel Deletethispart_Content;
	public JLabel ConfirmedTimes_Content;
	public JLabel LengthofDocumentation_Content;
	/**
	 * Create the panel.
	 */
	public DetailsofResults() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					requestFocus();
			}
		});
		setBounds(0, 0, 665, 1500);
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		ID_Content = new JLabel("");
		ID_Content.setFont(new Font("Arial", Font.BOLD, 24));
		ID_Content.setBounds(10, 10, 176, 24);
		add(ID_Content);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Arial", Font.PLAIN, 20));
		Type.setBounds(10, 44, 51, 24);
		add(Type);
		
		Type_Content = new JLabel("");
		Type_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		Type_Content.setBounds(71, 44, 146, 24);
		add(Type_Content);
		
		JLabel EnteredDate = new JLabel("Entered Date:");
		EnteredDate.setFont(new Font("Arial", Font.PLAIN, 20));
		EnteredDate.setBounds(10, 78, 134, 24);
		add(EnteredDate);
		
		EnteredDate_Content = new JLabel("");
		EnteredDate_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		EnteredDate_Content.setBounds(154, 78, 117, 24);
		add(EnteredDate_Content);
		
		JLabel Author = new JLabel("Author:");
		Author.setFont(new Font("Arial", Font.PLAIN, 20));
		Author.setBounds(10, 112, 70, 24);
		add(Author);
		
		Author_Content = new JLabel("");
		Author_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		Author_Content.setBounds(81, 112, 421, 24);
		add(Author_Content);
		
		JLabel ShortDescription = new JLabel("Short Description:");
		ShortDescription.setFont(new Font("Arial", Font.PLAIN, 20));
		ShortDescription.setBounds(10, 146, 163, 24);
		add(ShortDescription);
		
		Description1 = new JLabel("");
		Description1.setFont(new Font("Arial", Font.PLAIN, 20));
		Description1.setBounds(171, 146, 484, 24);
		add(Description1);
		
		JLabel URL = new JLabel("Main Page on Registry:");
		URL.setFont(new Font("Arial", Font.PLAIN, 20));
		URL.setBounds(10, 584, 213, 24);
		add(URL);
		
		URL_Content = new JLabel("");
		URL_Content.addMouseListener(new MouseAdapter() {
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
		URL_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		URL_Content.setBounds(229, 584, 426, 24);
		add(URL_Content);
		
		JLabel ReleasedStatus = new JLabel("Part Status:");
		ReleasedStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		ReleasedStatus.setBounds(10, 686, 117, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ReleasedStatus_Content.setBounds(129, 686, 278, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Rating:");
		AverageStars.setFont(new Font("Arial", Font.PLAIN, 20));
		AverageStars.setBounds(10, 1382, 146, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		AverageStar_Content.setBounds(164, 1382, 107, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Number of Related Results on Google Scholar:");
		ResultsInGoogle.setFont(new Font("Arial", Font.PLAIN, 20));
		ResultsInGoogle.setBounds(10, 1416, 425, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ResultsInGoogle_Content.setBounds(445, 1416, 51, 24);
		add(ResultsInGoogle_Content);
		
		Score = new JLabel("", SwingConstants.CENTER);
		Score.setFont(new Font("Arial", Font.BOLD, 40));
		Score.setBorder(BorderFactory.createLineBorder(Color.black));
		Score.setBounds(514, 30, 112, 100);
		add(Score);
		
		Description2 = new JLabel("");
		Description2.setFont(new Font("Arial", Font.PLAIN, 20));
		Description2.setBounds(10, 180, 645, 24);
		add(Description2);
		
        Addforcomparison = new JCheckBox("Add to compare");
        Addforcomparison.setFont(new Font("Arial", Font.PLAIN, 24));
        Addforcomparison.setBounds(229, 17, 206, 30);
        Addforcomparison.setBackground(new Color(0, 255, 255));
		add(Addforcomparison);
		
		JLabel Sequence = new JLabel("Sequences:");
		Sequence.setFont(new Font("Arial", Font.PLAIN, 20));
		Sequence.setBounds(10, 210, 117, 24);
		add(Sequence);
		
		JLabel Categories = new JLabel("Categories:");
		Categories.setFont(new Font("Arial", Font.PLAIN, 20));
		Categories.setBounds(10, 308, 117, 24);
		add(Categories);
		
		JLabel Twins = new JLabel("Twins:");
		Twins.setFont(new Font("Arial", Font.PLAIN, 20));
		Twins.setBounds(10, 470, 70, 24);
		add(Twins);
		
		JScrollPane sequence_scrollPane = new JScrollPane();
		sequence_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sequence_scrollPane.setBounds(10, 233, 645, 72);
		add(sequence_scrollPane);
		
		JScrollPane categories_scrollPane = new JScrollPane();
		categories_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		categories_scrollPane.setBounds(10, 331, 645, 137);
		add(categories_scrollPane);
		
		JScrollPane twins_scrollPane = new JScrollPane();
		twins_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		twins_scrollPane.setBounds(10, 502, 645, 72);
		add(twins_scrollPane);
		
		JLabel UrlOfGoogle = new JLabel("URL of the most related publication:");
		UrlOfGoogle.setFont(new Font("Arial", Font.PLAIN, 20));
		UrlOfGoogle.setBounds(10, 618, 320, 24);
		add(UrlOfGoogle);
		
		related_URL = new JLabel("");
		related_URL.setFont(new Font("Arial", Font.PLAIN, 20));
		related_URL.setBounds(335, 618, 320, 24);
		add(related_URL);
		
		JLabel Status = new JLabel("Status");
		Status.setFont(new Font("Arial", Font.BOLD, 24));
		Status.setBounds(10, 652, 98, 28);
		add(Status);
		
		JLabel SampleStatus = new JLabel("Sample Status:");
		SampleStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		SampleStatus.setBounds(10, 720, 146, 24);
		add(SampleStatus);
		
		SampleStatus_Content = new JLabel("1");
		SampleStatus_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		SampleStatus_Content.setBounds(154, 720, 278, 24);
		add(SampleStatus_Content);
		
		JLabel DNAStatus = new JLabel("DNA Status:");
		DNAStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		DNAStatus.setBounds(10, 754, 110, 24);
		add(DNAStatus);
		
		DNAStatus_Content = new JLabel("2");
		DNAStatus_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		DNAStatus_Content.setBounds(130, 754, 278, 24);
		add(DNAStatus_Content);
		
		JLabel Deletethispart = new JLabel("Delete this Part:");
		Deletethispart.setFont(new Font("Arial", Font.PLAIN, 20));
		Deletethispart.setBounds(10, 788, 150, 24);
		add(Deletethispart);
		
		Deletethispart_Content = new JLabel("3");
		Deletethispart_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		Deletethispart_Content.setBounds(170, 788, 278, 24);
		add(Deletethispart_Content);
		
		JLabel ConfirmedTimes = new JLabel("Confirmed Times:");
		ConfirmedTimes.setFont(new Font("Arial", Font.PLAIN, 20));
		ConfirmedTimes.setBounds(10, 822, 160, 24);
		add(ConfirmedTimes);
		
		ConfirmedTimes_Content = new JLabel("4");
		ConfirmedTimes_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ConfirmedTimes_Content.setBounds(180, 822, 278, 24);
		add(ConfirmedTimes_Content);
		
		JLabel LengthofDocumentation = new JLabel("Length of Documentation:");
		LengthofDocumentation.setFont(new Font("Arial", Font.PLAIN, 20));
		LengthofDocumentation.setBounds(10, 856, 240, 24);
		add(LengthofDocumentation);
		
		LengthofDocumentation_Content = new JLabel("5");
		LengthofDocumentation_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		LengthofDocumentation_Content.setBounds(250, 856, 278, 24);
		add(LengthofDocumentation_Content);
		
		JLabel Reliability = new JLabel("Reliability");
		Reliability.setFont(new Font("Arial", Font.BOLD, 24));
		Reliability.setBounds(10, 890, 140, 28);
		add(Reliability);
		
		
		
		setVisible(true);
	}
}