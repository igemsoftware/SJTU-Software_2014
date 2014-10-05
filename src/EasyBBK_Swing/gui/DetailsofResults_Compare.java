package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DetailsofResults_Compare extends JPanel {
	public JLabel ID_Content;
	public JLabel Type_Content;
	public JLabel Description1;
	public JLabel ReleasedStatus_Content;
	public JLabel AverageStar_Content;
	public JLabel ResultsInGoogle_Content;
	public JLabel Score;
	public JLabel Description2;
	public JLabel SampleStatus_Content;
	public JLabel DNAStatus_Content;
	public JLabel DeleteThisPart_Content;
	public JLabel ConfirmedTimes_Content;
	public JLabel LengthOfDocumentation_Content;
	public JLabel PartResults_Content;
	public JLabel PartRating_Content;
	public JLabel GroupFavorite_Content;
	public JLabel UsedTimes_Content;
	public JLabel TotComments_Content;
	public JLabel URL_Content;
	/**
	 * Create the panel.
	 */
	public DetailsofResults_Compare() {
		setBounds(0, 0, 437, 820);
		setPreferredSize(new Dimension(437, 820));
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
		
		JLabel ShortDescription = new JLabel("Short Description:");
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ShortDescription.setBounds(10, 78, 146, 24);
		add(ShortDescription);
		
		Description1 = new JLabel("");
		Description1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Description1.setBounds(161, 78, 132, 24);
		add(Description1);
		
		JLabel ReleasedStatus = new JLabel("Part Status:");
		ReleasedStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ReleasedStatus.setBounds(10, 229, 102, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ReleasedStatus_Content.setBounds(112, 229, 315, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Rating:");
		AverageStars.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageStars.setBounds(10, 671, 137, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageStar_Content.setBounds(161, 671, 112, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Google Items:");
		ResultsInGoogle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ResultsInGoogle.setBounds(10, 791, 124, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ResultsInGoogle_Content.setBounds(144, 791, 63, 24);
		add(ResultsInGoogle_Content);
		
		Score = new JLabel("", SwingConstants.CENTER);
		Score.setFont(new Font("Times New Roman", Font.BOLD, 96));
		Score.setBorder(BorderFactory.createLineBorder(Color.black));
		Score.setBounds(326, 10, 101, 98);
		add(Score);
		
		Description2 = new JLabel("");
		Description2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Description2.setBounds(10, 111, 283, 24);
		add(Description2);
		
		JLabel SampleStatus = new JLabel("Sample Status:");
		SampleStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SampleStatus.setBounds(10, 263, 124, 24);
		add(SampleStatus);
		
		SampleStatus_Content = new JLabel("");
		SampleStatus_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SampleStatus_Content.setBounds(132, 263, 295, 24);
		add(SampleStatus_Content);
		
		JLabel score = new JLabel("Score", SwingConstants.CENTER);
		score.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		score.setBounds(338, 111, 75, 24);
		add(score);
		
		JLabel Status = new JLabel("Status");
		Status.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Status.setBounds(10, 195, 75, 24);
		add(Status);
		
		JLabel DNAStatus = new JLabel("DNA Status:");
		DNAStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DNAStatus.setBounds(10, 297, 102, 24);
		add(DNAStatus);
		
		DNAStatus_Content = new JLabel("");
		DNAStatus_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DNAStatus_Content.setBounds(120, 297, 295, 24);
		add(DNAStatus_Content);
		
		JLabel DeleteThisPart = new JLabel("Delete this Part:");
		DeleteThisPart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DeleteThisPart.setBounds(10, 331, 137, 24);
		add(DeleteThisPart);
		
		DeleteThisPart_Content = new JLabel("");
		DeleteThisPart_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DeleteThisPart_Content.setBounds(152, 331, 275, 24);
		add(DeleteThisPart_Content);
		
		JLabel ConfirmedTimes = new JLabel("Confirmed Times:");
		ConfirmedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ConfirmedTimes.setBounds(10, 365, 148, 24);
		add(ConfirmedTimes);
		
		ConfirmedTimes_Content = new JLabel("");
		ConfirmedTimes_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ConfirmedTimes_Content.setBounds(168, 365, 259, 24);
		add(ConfirmedTimes_Content);
		
		JLabel LengthOfDocumentation = new JLabel("Length of Documentation:");
		LengthOfDocumentation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		LengthOfDocumentation.setBounds(10, 399, 209, 24);
		add(LengthOfDocumentation);
		
		LengthOfDocumentation_Content = new JLabel("");
		LengthOfDocumentation_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		LengthOfDocumentation_Content.setBounds(220, 399, 207, 24);
		add(LengthOfDocumentation_Content);
		
		JLabel Reliability = new JLabel("Reliability");
		Reliability.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Reliability.setBounds(10, 449, 118, 24);
		add(Reliability);
		
		JLabel PartResults = new JLabel("Part Results:");
		PartResults.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartResults.setBounds(10, 483, 112, 24);
		add(PartResults);
		
		PartResults_Content = new JLabel("");
		PartResults_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartResults_Content.setBounds(122, 483, 305, 24);
		add(PartResults_Content);
		
		JLabel PartRating = new JLabel("Star Rating:");
		PartRating.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartRating.setBounds(10, 517, 102, 24);
		add(PartRating);
		
		PartRating_Content = new JLabel("");
		PartRating_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartRating_Content.setBounds(112, 517, 305, 24);
		add(PartRating_Content);
		
		JLabel GroupFavorite = new JLabel("Group Favorite:");
		GroupFavorite.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupFavorite.setBounds(10, 551, 137, 24);
		add(GroupFavorite);
		
		GroupFavorite_Content = new JLabel("");
		GroupFavorite_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupFavorite_Content.setBounds(149, 551, 278, 24);
		add(GroupFavorite_Content);
		
		JLabel Feedbacks = new JLabel("Feedbacks");
		Feedbacks.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Feedbacks.setBounds(10, 603, 118, 24);
		add(Feedbacks);
		
		JLabel UsedTimes = new JLabel("Used Times:");
		UsedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UsedTimes.setBounds(10, 637, 112, 24);
		add(UsedTimes);
		
		UsedTimes_Content = new JLabel("");
		UsedTimes_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UsedTimes_Content.setBounds(122, 637, 112, 24);
		add(UsedTimes_Content);
		
		JLabel TotComments = new JLabel("Number of Comments:");
		TotComments.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TotComments.setBounds(10, 705, 184, 24);
		add(TotComments);
		
		TotComments_Content = new JLabel("");
		TotComments_Content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		TotComments_Content.setBounds(204, 705, 112, 24);
		add(TotComments_Content);
		
		JLabel Publication = new JLabel("Publication");
		Publication.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Publication.setBounds(10, 757, 146, 24);
		add(Publication);
		
		JLabel URL = new JLabel("URL:");
		URL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		URL.setBounds(10, 145, 48, 24);
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
		URL_Content.setBounds(59, 145, 368, 24);
		add(URL_Content);
	}
}
