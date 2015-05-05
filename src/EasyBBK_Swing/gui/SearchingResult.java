package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchingResult extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public JLabel ID_Content;
	public JLabel Type_Content;
	public JLabel Evalue_Content;
	public JLabel EnteredDate_Content;
	public JLabel Author_Content;
	public JLabel Description;
	public JLabel URL_Content;
	public JLabel ReleasedStatus_Content;
	public JLabel AverageStar_Content;
	public JLabel ResultsInGoogle_Content;
	public JLabel Evalue;
	public JLabel UsedTimes_Content;
	public JLabel Score;
	/**
	 * Create the panel.
	 */
	public SearchingResult(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					requestFocus();
			}
		});
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 569, 192);
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
		ID_Content.setFont(new Font("Times New Roman", Font.BOLD, 25));
		ID_Content.setBounds(10, 10, 180, 36);
		add(ID_Content);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Type.setBounds(195, 16, 50, 24);
		add(Type);
		
		Type_Content = new JLabel("");
		Type_Content.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Type_Content.setBounds(245, 16, 117, 24);
		add(Type_Content);
		
		Evalue = new JLabel("E-value:");
		Evalue.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Evalue.setBounds(365, 16, 68, 24);
		add(Evalue);
		
		Evalue_Content = new JLabel("");
		Evalue_Content.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Evalue_Content.setBounds(435, 16, 56, 24);
		add(Evalue_Content);
		
		JLabel EnteredDate = new JLabel("Entered Date:");
		EnteredDate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		EnteredDate.setBounds(10, 54, 101, 24);
		add(EnteredDate);
		
		EnteredDate_Content = new JLabel("");
		EnteredDate_Content.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		EnteredDate_Content.setBounds(110, 54, 134, 24);
		add(EnteredDate_Content);
		
		JLabel Author = new JLabel("Author:");
		Author.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Author.setBounds(10, 92, 56, 24);
		add(Author);
		
		Author_Content = new JLabel("");
		Author_Content.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Author_Content.setBounds(65, 92, 494, 24);
		add(Author_Content);
		
		JLabel ShortDescription = new JLabel("Short Description:");
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		ShortDescription.setBounds(10, 73, 127, 24);
		add(ShortDescription);
		
		Description = new JLabel("");
		Description.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Description.setBounds(141, 73, 418, 24);
		add(Description);
		
		JLabel Url = new JLabel("Main Page of This Part:");
		Url.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		Url.setBounds(10, 111, 161, 24);
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
		URL_Content.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		URL_Content.setBounds(175, 111, 384, 24);
		add(URL_Content);
		
		JLabel ReleasedStatus = new JLabel("Part Status:");
		ReleasedStatus.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		ReleasedStatus.setBounds(10, 130, 79, 24);
		add(ReleasedStatus);
		
		ReleasedStatus_Content = new JLabel("");
		ReleasedStatus_Content.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		ReleasedStatus_Content.setBounds(90, 130, 169, 24);
		add(ReleasedStatus_Content);
		
		JLabel AverageStars = new JLabel("Average Rating:");
		AverageStars.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		AverageStars.setBounds(321, 130, 117, 24);
		add(AverageStars);
		
		AverageStar_Content = new JLabel("");
		AverageStar_Content.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		AverageStar_Content.setBounds(435, 130, 79, 24);
		add(AverageStar_Content);
		
		JLabel ResultsInGoogle = new JLabel("Number of Related Results on NCBI:");
		ResultsInGoogle.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		ResultsInGoogle.setBounds(10, 149, 322, 24);
		add(ResultsInGoogle);
		
		ResultsInGoogle_Content = new JLabel("");
		ResultsInGoogle_Content.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ResultsInGoogle_Content.setBounds(340, 149, 68, 24);
		add(ResultsInGoogle_Content);
		
		JLabel UsedTimes = new JLabel("Used Times:");
		UsedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		UsedTimes.setBounds(318, 54, 92, 24);
		add(UsedTimes);
		
		UsedTimes_Content = new JLabel("");
		UsedTimes_Content.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		UsedTimes_Content.setBounds(410, 54, 68, 24);
		add(UsedTimes_Content);
		
		Score = new JLabel("", SwingConstants.CENTER);
		Score.setBorder(BorderFactory.createLineBorder(Color.black));
		Score.setFont(new Font("Times New Roman", Font.BOLD, 18));
		Score.setBounds(509, 10, 50, 40);
		add(Score);
	}
}
