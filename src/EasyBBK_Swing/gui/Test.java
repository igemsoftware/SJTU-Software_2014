package EasyBBK_Swing.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import data_center.*;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class Test {

	private JFrame frame;

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
		
		JLabel lblNewLabel = new JLabel("New label");
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
				SearchResultList searchresultlist = searchcenter.search("BBa_B0000");
				SearchResultList searchresultlist1 = (SearchResultList)searchresultlist.clone();
				BbkOutline bbkoutline = searchresultlist.get(0);
				BbkOutline bbkoutline1 = searchresultlist1.get(0);
				lblNewLabel_1.setText(bbkoutline.name);
				//BbkDetail bbkdetail = searchcenter.getDetail(bbkoutline.name);
				searchresultlist = searchcenter.blast(null, BlastingSearcher.MODE_INPUT_SEQUENCE);
				lblNewLabel.setText(bbkoutline1.name);
			}
		});
		btnNewButton.setBounds(87, 46, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(344, 61, 198, 179);
		frame.getContentPane().add(scrollPane);
		
		
	}
}
