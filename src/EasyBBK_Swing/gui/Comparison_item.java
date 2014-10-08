package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Comparison_item extends JPanel {

	/**
	 * Create the panel.
	 */
	public Comparison_item() {
		setBounds(0, 0, 300, 690);
		setPreferredSize(new Dimension(300, 690));
		setBackground(new Color(255, 255, 255));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		JLabel PartName = new JLabel("", SwingConstants.CENTER);
		PartName.setBorder(BorderFactory.createLineBorder(Color.black));
		PartName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		PartName.setBounds(0, 0, 300, 40);
		add(PartName);
		
		JLabel Type = new JLabel("", SwingConstants.CENTER);
		Type.setBorder(BorderFactory.createLineBorder(Color.black));
		Type.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Type.setBounds(0, 40, 300, 40);
		add(Type);
		
		JLabel ShortDescription = new JLabel("", SwingConstants.CENTER);
		ShortDescription.setBorder(BorderFactory.createLineBorder(Color.black));
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ShortDescription.setBounds(0, 110, 300, 60);
		add(ShortDescription);
		
		JLabel Status = new JLabel("", SwingConstants.CENTER);
		Status.setBorder(BorderFactory.createLineBorder(Color.black));
		Status.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Status.setBounds(0, 170, 300, 40);
		add(Status);
		
		JLabel PartStatus = new JLabel("", SwingConstants.CENTER);
		PartStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		PartStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartStatus.setBounds(0, 210, 300, 30);
		add(PartStatus);
		
		JLabel SampleStatus = new JLabel("", SwingConstants.CENTER);
		SampleStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		SampleStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SampleStatus.setBounds(0, 240, 300, 30);
		add(SampleStatus);
		
		JLabel Url = new JLabel("", SwingConstants.CENTER);
		Url.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Url.setBorder(BorderFactory.createLineBorder(Color.black));
		Url.setBounds(0, 80, 300, 30);
		add(Url);
		
		JLabel DNAStatus = new JLabel("", SwingConstants.CENTER);
		DNAStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DNAStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		DNAStatus.setBounds(0, 270, 300, 30);
		add(DNAStatus);
		
		JLabel lblDeleteThisPart = new JLabel("", SwingConstants.CENTER);
		lblDeleteThisPart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDeleteThisPart.setBorder(BorderFactory.createLineBorder(Color.black));
		lblDeleteThisPart.setBounds(0, 300, 300, 30);
		add(lblDeleteThisPart);
		
		JLabel ConfirmedTimes = new JLabel("", SwingConstants.CENTER);
		ConfirmedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ConfirmedTimes.setBorder(BorderFactory.createLineBorder(Color.black));
		ConfirmedTimes.setBounds(0, 330, 300, 30);
		add(ConfirmedTimes);
		
		JLabel LengthofDocumentation = new JLabel("", SwingConstants.CENTER);
		LengthofDocumentation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		LengthofDocumentation.setBorder(BorderFactory.createLineBorder(Color.black));
		LengthofDocumentation.setBounds(0, 360, 300, 30);
		add(LengthofDocumentation);
		
		JLabel Reliability = new JLabel("", SwingConstants.CENTER);
		Reliability.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Reliability.setBorder(BorderFactory.createLineBorder(Color.black));
		Reliability.setBounds(0, 390, 300, 40);
		add(Reliability);
		
		JLabel PartResults = new JLabel("", SwingConstants.CENTER);
		PartResults.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartResults.setBorder(BorderFactory.createLineBorder(Color.black));
		PartResults.setBounds(0, 430, 300, 30);
		add(PartResults);
		
		JLabel GroupFavorite = new JLabel("", SwingConstants.CENTER);
		GroupFavorite.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GroupFavorite.setBorder(BorderFactory.createLineBorder(Color.black));
		GroupFavorite.setBounds(0, 460, 300, 30);
		add(GroupFavorite);
		
		JLabel Feedbacks = new JLabel("", SwingConstants.CENTER);
		Feedbacks.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Feedbacks.setBorder(BorderFactory.createLineBorder(Color.black));
		Feedbacks.setBounds(0, 490, 300, 40);
		add(Feedbacks);
		
		JLabel UsedTimes = new JLabel("", SwingConstants.CENTER);
		UsedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UsedTimes.setBorder(BorderFactory.createLineBorder(Color.black));
		UsedTimes.setBounds(0, 530, 300, 30);
		add(UsedTimes);
		
		JLabel AverageRating = new JLabel("", SwingConstants.CENTER);
		AverageRating.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AverageRating.setBorder(BorderFactory.createLineBorder(Color.black));
		AverageRating.setBounds(0, 560, 300, 30);
		add(AverageRating);
		
		JLabel NumberofComments = new JLabel("", SwingConstants.CENTER);
		NumberofComments.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		NumberofComments.setBorder(BorderFactory.createLineBorder(Color.black));
		NumberofComments.setBounds(0, 590, 300, 30);
		add(NumberofComments);
		
		JLabel Publication = new JLabel("", SwingConstants.CENTER);
		Publication.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Publication.setBorder(BorderFactory.createLineBorder(Color.black));
		Publication.setBounds(0, 620, 300, 40);
		add(Publication);
		
		JLabel GoogleItems = new JLabel("Google Items", SwingConstants.CENTER);
		GoogleItems.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		GoogleItems.setBorder(BorderFactory.createLineBorder(Color.black));
		GoogleItems.setBounds(0, 660, 209, 30);
		add(GoogleItems);
		
		
	}
}