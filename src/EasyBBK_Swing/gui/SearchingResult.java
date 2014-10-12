package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	public JLabel Score;
	public JLabel Evalue;
	public JLabel UsedTimes_Content;
	
	/**
	 * Create the panel.
	 */
	public SearchingResult(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					requestFocus();
			}
		});
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 569, 281);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
		setLayout(null);

		ID_Content = new JLabel("");
		ID_Content.setForeground(Color.blue);
		ID_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ID_Content.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				ID_Content.setForeground(Color.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ID_Content.setForeground(Color.blue);
			}
		});
		ID_Content.setFont(new Font("Arial", Font.BOLD, 24));
		ID_Content.setBounds(10, 10, 169, 24);
		add(ID_Content);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Arial", Font.PLAIN, 20));
		Type.setBounds(202, 10, 51, 24);
		add(Type);
		
		Type_Content = new JLabel("");
		Type_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		Type_Content.setBounds(263, 10, 117, 24);
		add(Type_Content);
		
		Evalue = new JLabel("E-value:");
		Evalue.setFont(new Font("Arial", Font.PLAIN, 20));
		Evalue.setBounds(409, 10, 82, 24);
		add(Evalue);
		
		Evalue_Content = new JLabel("");
		Evalue_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		Evalue_Content.setBounds(495, 10, 56, 24);
		add(Evalue_Content);
		
		JLabel EnteredDate = new JLabel("Entered Date:");
		EnteredDate.setFont(new Font("Arial", Font.PLAIN, 20));
		EnteredDate.setBounds(10, 44, 134, 24);
		add(EnteredDate);
		
		EnteredDate_Content = new JLabel("");
		EnteredDate_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		EnteredDate_Content.setBounds(143, 44, 134, 24);
		add(EnteredDate_Content);
		
		JLabel Author = new JLabel("Author:");
		Author.setFont(new Font("Arial", Font.PLAIN, 20));
		Author.setBounds(10, 144, 68, 24);
		add(Author);
		
		Author_Content = new JLabel("");
		Author_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		Author_Content.setBounds(81, 144, 378, 24);
		add(Author_Content);
		
		Score = new JLabel("", JLabel.CENTER);
		Score.setFont(new Font("Arial", Font.BOLD, 30));
		Score.setBounds(469, 78, 82, 73);
		Score.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Score);
		
		JLabel ShortDescription = new JLabel("Short Description:");
		ShortDescription.setFont(new Font("Arial", Font.PLAIN, 20));
		ShortDescription.setBounds(10, 78, 159, 24);
		add(ShortDescription);
		
		Description1 = new JLabel("");
		Description1.setFont(new Font("Arial", Font.PLAIN, 20));
		Description1.setBounds(173, 78, 286, 24);
		add(Description1);
		
		Description2 = new JLabel("");
		Description2.setFont(new Font("Arial", Font.PLAIN, 20));
		Description2.setBounds(10, 113, 450, 24);
		add(Description2);
		
		JLabel Url = new JLabel("URL:");
		Url.setFont(new Font("Arial", Font.PLAIN, 20));
		Url.setBounds(10, 178, 48, 24);
		add(Url);
		
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
		URL_Content.setBounds(60, 178, 399, 24);
		add(URL_Content);
		
		JLabel ReleasedStatus = new JLabel("Part Status:");
		ReleasedStatus.setFont(new Font("Arial", Font.PLAIN, 20));
		ReleasedStatus.setBounds(10, 212, 111, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ReleasedStatus_Content.setBounds(125, 212, 189, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Rating:");
		AverageStars.setFont(new Font("Arial", Font.PLAIN, 20));
		AverageStars.setBounds(321, 212, 145, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		AverageStar_Content.setBounds(469, 212, 79, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Number of Related Results on Google Scholar:");
		ResultsInGoogle.setFont(new Font("Arial", Font.PLAIN, 20));
		ResultsInGoogle.setBounds(10, 247, 422, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		ResultsInGoogle_Content.setBounds(442, 246, 51, 24);
		add(ResultsInGoogle_Content);
		
		JLabel UsedTimes = new JLabel("Used Times:");
		UsedTimes.setFont(new Font("Arial", Font.PLAIN, 20));
		UsedTimes.setBounds(315, 44, 117, 24);
		add(UsedTimes);
		
		UsedTimes_Content = new JLabel("");
		UsedTimes_Content.setFont(new Font("Arial", Font.PLAIN, 20));
		UsedTimes_Content.setBounds(440, 44, 68, 24);
		add(UsedTimes_Content);
	}
}
