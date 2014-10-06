package EasyBBK_Swing.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import data_center.*;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;

public class Test {

	private JFrame frame;
	public JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 673, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		lblNewLabel.setBounds(199, 123, 154, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(44, 133, 99, 35);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SearchCenter searchcenter = new SearchCenter();
				SearchResultList searchresultlist = searchcenter.search("BBa_B");
				ArrayList<String> type = new ArrayList<String>();
				int[] enteredyear = new int[2];
				enteredyear[0] = 2003;
				enteredyear[1] = 2013;
				type.add(SearchResultList.Filter.Type.RBS);
				SearchResultList filteredlist = searchresultlist;
				filteredlist.sortByEnterDate(true);
				filteredlist = filteredlist.filterByType(type);
				filteredlist = filteredlist.filterByEnterYear(enteredyear);
				filteredlist = filteredlist.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.RELEASED);
				filteredlist = filteredlist.filterByDNAStatus(SearchResultList.Filter.DNAStatus.AVAILABLE);
				filteredlist = filteredlist.filterByDeletedOrNot(false);
				ArrayList<Integer> starNumList = new ArrayList();
				starNumList.add(4);
				starNumList.add(5);
				filteredlist = filteredlist.filterByStarNum(starNumList);
				BbkOutline bbkoutline = filteredlist.get(1);
				lblNewLabel.setText(bbkoutline.name);
			}
		});
		btnNewButton.setBounds(87, 46, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(344, 61, 198, 179);
		frame.getContentPane().add(scrollPane);
		
		
	}
}
