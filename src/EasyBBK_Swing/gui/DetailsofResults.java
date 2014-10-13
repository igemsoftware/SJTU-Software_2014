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
	private JLabel Categories;
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
		URL.setBounds(10, 418, 213, 24);
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
		URL_Content.setBounds(229, 418, 426, 24);
		add(URL_Content);
		
		JLabel ReleasedStatus = new JLabel("Part Status:");
		ReleasedStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		ReleasedStatus.setBounds(10, 452, 117, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ReleasedStatus_Content.setBounds(129, 452, 278, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Rating:");
		AverageStars.setFont(new Font("Arial", Font.PLAIN, 20));
		AverageStars.setBounds(10, 486, 146, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		AverageStar_Content.setBounds(164, 486, 107, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Number of Related Results on Google Scholar:");
		ResultsInGoogle.setFont(new Font("Arial", Font.PLAIN, 20));
		ResultsInGoogle.setBounds(10, 520, 425, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ResultsInGoogle_Content.setBounds(445, 520, 51, 24);
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
		
		JLabel Sequence = new JLabel("Sequence:");
		Sequence.setFont(new Font("Arial", Font.PLAIN, 20));
		Sequence.setBounds(10, 210, 100, 24);
		add(Sequence);
		
		Categories = new JLabel("Categories:");
		Categories.setFont(new Font("Arial", Font.PLAIN, 20));
		Categories.setBounds(10, 308, 117, 24);
		add(Categories);
		
		setVisible(true);
	}
}