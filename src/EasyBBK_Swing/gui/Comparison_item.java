package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Comparison_item extends JPanel {
	public JLabel PartName;
	public JLabel Type;
	public JLabel ShortDescription;
	public JLabel PartStatus;
	public JLabel SampleStatus;
	public JLabel Url;
	public JLabel DNAStatus;
	public JLabel DeleteThisPart;
	public JLabel ConfirmedTimes;
	public JLabel LengthofDocumentation;
	public JLabel PartResults;
	public JLabel GroupFavorite;
	public JLabel UsedTimes;
	public JLabel AverageRating;
	public JLabel NumberofComments;
	public JLabel GoogleItems;
	/**
	 * Create the panel.
	 */
	public Comparison_item() {
		setBounds(0, 0, 300, 690);
		setPreferredSize(new Dimension(300, 690));
		setBackground(new Color(255, 255, 255));
		setOpaque(false);
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		PartName = new JLabel("", SwingConstants.CENTER);
		PartName.setOpaque(false);
		//PartName.setBorder(BorderFactory.createLineBorder(Color.black));
		PartName.setFont(new Font("Times New Roman", Font.BOLD, 24));
		PartName.setBounds(0, 0, 300, 40);
		add(PartName);
		
		Type = new JLabel("", SwingConstants.CENTER);
		Type.setOpaque(false);
		//Type.setBorder(BorderFactory.createLineBorder(Color.black));
		Type.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Type.setBounds(0, 40, 300, 40);
		add(Type);
		
		ShortDescription = new JLabel("", SwingConstants.CENTER);
		ShortDescription.setOpaque(false);
		//ShortDescription.setBorder(BorderFactory.createLineBorder(Color.black));
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ShortDescription.setBounds(0, 110, 300, 60);
		add(ShortDescription);
		
		JLabel Status = new JLabel("", SwingConstants.CENTER);
		Status.setOpaque(false);
		//Status.setBorder(BorderFactory.createLineBorder(Color.black));
		Status.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Status.setBounds(0, 170, 300, 40);
		add(Status);
		
		PartStatus = new JLabel("", SwingConstants.CENTER);
		PartStatus.setOpaque(false);
		//PartStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		PartStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		PartStatus.setBounds(0, 210, 300, 30);
		add(PartStatus);
		
		SampleStatus = new JLabel("", SwingConstants.CENTER);
		SampleStatus.setOpaque(false);
		//SampleStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		SampleStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SampleStatus.setBounds(0, 240, 300, 30);
		add(SampleStatus);
		
		Url = new JLabel("", SwingConstants.CENTER);
		Url.setOpaque(false);
		Url.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					try{
						Runtime.getRuntime().exec("explorer " + Url.getText());
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Url.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Url.setForeground(Color.blue);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Url.setForeground(Color.black);
			}
		});
		Url.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//Url.setBorder(BorderFactory.createLineBorder(Color.black));
		Url.setBounds(0, 80, 300, 30);
		add(Url);
		
		DNAStatus = new JLabel("", SwingConstants.CENTER);
		DNAStatus.setOpaque(false);
		DNAStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//DNAStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		DNAStatus.setBounds(0, 270, 300, 30);
		add(DNAStatus);
		
		DeleteThisPart = new JLabel("", SwingConstants.CENTER);
		DeleteThisPart.setOpaque(false);
		DeleteThisPart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//DeleteThisPart.setBorder(BorderFactory.createLineBorder(Color.black));
		DeleteThisPart.setBounds(0, 300, 300, 30);
		add(DeleteThisPart);
		
		ConfirmedTimes = new JLabel("", SwingConstants.CENTER);
		ConfirmedTimes.setOpaque(false);
		ConfirmedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//ConfirmedTimes.setBorder(BorderFactory.createLineBorder(Color.black));
		ConfirmedTimes.setBounds(0, 330, 300, 30);
		add(ConfirmedTimes);
		
		LengthofDocumentation = new JLabel("", SwingConstants.CENTER);
		LengthofDocumentation.setOpaque(false);
		LengthofDocumentation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//LengthofDocumentation.setBorder(BorderFactory.createLineBorder(Color.black));
		LengthofDocumentation.setBounds(0, 360, 300, 30);
		add(LengthofDocumentation);
		
		JLabel Reliability = new JLabel("", SwingConstants.CENTER);
		Reliability.setOpaque(false);
		Reliability.setFont(new Font("Times New Roman", Font.BOLD, 24));
		//Reliability.setBorder(BorderFactory.createLineBorder(Color.black));
		Reliability.setBounds(0, 390, 300, 40);
		add(Reliability);
		
		PartResults = new JLabel("", SwingConstants.CENTER);
		PartResults.setOpaque(false);
		PartResults.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//PartResults.setBorder(BorderFactory.createLineBorder(Color.black));
		PartResults.setBounds(0, 430, 300, 30);
		add(PartResults);
		
		GroupFavorite = new JLabel("", SwingConstants.CENTER);
		GroupFavorite.setOpaque(false);
		GroupFavorite.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//GroupFavorite.setBorder(BorderFactory.createLineBorder(Color.black));
		GroupFavorite.setBounds(0, 460, 300, 30);
		add(GroupFavorite);
		
		JLabel Feedbacks = new JLabel("", SwingConstants.CENTER);
		Feedbacks.setOpaque(false);
		Feedbacks.setFont(new Font("Times New Roman", Font.BOLD, 24));
		//Feedbacks.setBorder(BorderFactory.createLineBorder(Color.black));
		Feedbacks.setBounds(0, 490, 300, 40);
		add(Feedbacks);
		
		UsedTimes = new JLabel("", SwingConstants.CENTER);
		UsedTimes.setOpaque(false);
		UsedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//UsedTimes.setBorder(BorderFactory.createLineBorder(Color.black));
		UsedTimes.setBounds(0, 530, 300, 30);
		add(UsedTimes);
		
		AverageRating = new JLabel("", SwingConstants.CENTER);
		AverageRating.setOpaque(false);
		AverageRating.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//AverageRating.setBorder(BorderFactory.createLineBorder(Color.black));
		AverageRating.setBounds(0, 560, 300, 30);
		add(AverageRating);
		
		NumberofComments = new JLabel("", SwingConstants.CENTER);
		NumberofComments.setOpaque(false);
		NumberofComments.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//NumberofComments.setBorder(BorderFactory.createLineBorder(Color.black));
		NumberofComments.setBounds(0, 590, 300, 30);
		add(NumberofComments);
		
		JLabel Publication = new JLabel("", SwingConstants.CENTER);
		Publication.setOpaque(false);
		Publication.setFont(new Font("Times New Roman", Font.BOLD, 24));
		//Publication.setBorder(BorderFactory.createLineBorder(Color.black));
		Publication.setBounds(0, 620, 300, 40);
		add(Publication);
		
		GoogleItems = new JLabel("", SwingConstants.CENTER);
		GoogleItems.setOpaque(false);
		GoogleItems.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//GoogleItems.setBorder(BorderFactory.createLineBorder(Color.black));
		GoogleItems.setBounds(0, 660, 300, 30);
		add(GoogleItems);
		
		
	}
}