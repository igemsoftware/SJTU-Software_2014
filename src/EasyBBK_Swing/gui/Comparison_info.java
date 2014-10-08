package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Comparison_info extends JPanel {

	/**
	 * Create the panel.
	 */
	public Comparison_info() {
		setBounds(0, 0, 220, 690);
		setPreferredSize(new Dimension(220, 690));
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		JLabel PartName = new JLabel("Part Name", SwingConstants.CENTER);
		PartName.setBorder(BorderFactory.createLineBorder(Color.black));
		PartName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		PartName.setBounds(0, 0, 220, 40);
		add(PartName);
		
		JLabel Type = new JLabel("Type", SwingConstants.CENTER);
		Type.setBorder(BorderFactory.createLineBorder(Color.black));
		Type.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Type.setBounds(0, 40, 220, 40);
		add(Type);
		
		JLabel ShortDescription = new JLabel("Short Description", SwingConstants.CENTER);
		ShortDescription.setBorder(BorderFactory.createLineBorder(Color.black));
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ShortDescription.setBounds(0, 110, 220, 60);
		add(ShortDescription);
		
		JLabel Status = new JLabel("Status", SwingConstants.CENTER);
		Status.setBorder(BorderFactory.createLineBorder(Color.black));
		Status.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Status.setBounds(0, 170, 220, 40);
		add(Status);
		
		JLabel PartStatus = new JLabel("Part Status", SwingConstants.CENTER);
		PartStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		PartStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartStatus.setBounds(0, 210, 220, 30);
		add(PartStatus);
		
		JLabel SampleStatus = new JLabel("Sample Status", SwingConstants.CENTER);
		SampleStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		SampleStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SampleStatus.setBounds(0, 240, 220, 30);
		add(SampleStatus);
		
		JLabel Url = new JLabel("URL", SwingConstants.CENTER);
		Url.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Url.setBorder(BorderFactory.createLineBorder(Color.black));
		Url.setBounds(0, 80, 220, 30);
		add(Url);
		
		JLabel DNAStatus = new JLabel("DNA Status", SwingConstants.CENTER);
		DNAStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DNAStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		DNAStatus.setBounds(0, 270, 220, 30);
		add(DNAStatus);
		
		JLabel lblDeleteThisPart = new JLabel("Delete this Part", SwingConstants.CENTER);
		lblDeleteThisPart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDeleteThisPart.setBorder(BorderFactory.createLineBorder(Color.black));
		lblDeleteThisPart.setBounds(0, 300, 220, 30);
		add(lblDeleteThisPart);
		
		JLabel ConfirmedTimes = new JLabel("Confirmed Times", SwingConstants.CENTER);
		ConfirmedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ConfirmedTimes.setBorder(BorderFactory.createLineBorder(Color.black));
		ConfirmedTimes.setBounds(0, 330, 220, 30);
		add(ConfirmedTimes);
		
		JLabel LengthofDocumentation = new JLabel("Length of Documentation", SwingConstants.CENTER);
		LengthofDocumentation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		LengthofDocumentation.setBorder(BorderFactory.createLineBorder(Color.black));
		LengthofDocumentation.setBounds(0, 360, 220, 30);
		add(LengthofDocumentation);
		
		JLabel Reliability = new JLabel("Reliability", SwingConstants.CENTER);
		Reliability.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Reliability.setBorder(BorderFactory.createLineBorder(Color.black));
		Reliability.setBounds(0, 390, 220, 40);
		add(Reliability);
		
		JLabel PartResults = new JLabel("Part Results", SwingConstants.CENTER);
		PartResults.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartResults.setBorder(BorderFactory.createLineBorder(Color.black));
		PartResults.setBounds(0, 430, 220, 30);
		add(PartResults);
		
		JLabel GroupFavorite = new JLabel("Group Favorite", SwingConstants.CENTER);
		GroupFavorite.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupFavorite.setBorder(BorderFactory.createLineBorder(Color.black));
		GroupFavorite.setBounds(0, 460, 220, 30);
		add(GroupFavorite);
		
		JLabel Feedbacks = new JLabel("Feedbacks", SwingConstants.CENTER);
		Feedbacks.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Feedbacks.setBorder(BorderFactory.createLineBorder(Color.black));
		Feedbacks.setBounds(0, 490, 220, 40);
		add(Feedbacks);
		
		JLabel UsedTimes = new JLabel("Used Times", SwingConstants.CENTER);
		UsedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UsedTimes.setBorder(BorderFactory.createLineBorder(Color.black));
		UsedTimes.setBounds(0, 530, 220, 30);
		add(UsedTimes);
		
		JLabel AverageRating = new JLabel("Average Rating", SwingConstants.CENTER);
		AverageRating.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageRating.setBorder(BorderFactory.createLineBorder(Color.black));
		AverageRating.setBounds(0, 560, 220, 30);
		add(AverageRating);
		
		JLabel NumberofComments = new JLabel("Number of Comments", SwingConstants.CENTER);
		NumberofComments.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		NumberofComments.setBorder(BorderFactory.createLineBorder(Color.black));
		NumberofComments.setBounds(0, 590, 220, 30);
		add(NumberofComments);
		
		JLabel Publication = new JLabel("Publication", SwingConstants.CENTER);
		Publication.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Publication.setBorder(BorderFactory.createLineBorder(Color.black));
		Publication.setBounds(0, 620, 220, 40);
		add(Publication);
		
		JLabel GoogleItems = new JLabel("Google Items", SwingConstants.CENTER);
		GoogleItems.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GoogleItems.setBorder(BorderFactory.createLineBorder(Color.black));
		GoogleItems.setBounds(0, 660, 220, 30);
		add(GoogleItems);
		
		
	}
}
