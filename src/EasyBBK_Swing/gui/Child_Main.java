package EasyBBK_Swing.gui;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Child_Main extends JPanel {

	public JLabel Logo;
	public JLabel Sentence;
	public JLabel GetStarted;
	public JLabel SearchDrawingBox;
	public JLabel DesignDrawingBox;
	public JLabel UploadDrawingBox;
	public MainPage mainpage;
	/**
	 * Create the panel.
	 */
	public Child_Main(MainPage mainpage1) {
		mainpage = mainpage1;
		setLayout(null);
		setBounds(0, 0, 1366, 670);
		setVisible(true);
		setBackground(new Color(255, 255, 255));
		
		Logo = new JLabel("");
		Logo.setBounds(508, 18, 358, 191);
		Logo.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Logo.png")));
		Logo.setVisible(true);
		add(Logo);
		
		Sentence = new JLabel("");
		Sentence.setBounds(370, 225, 650, 35);
		Sentence.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Sentence.png")));
		Sentence.setVisible(true);
		add(Sentence);
		
		GetStarted = new JLabel("");
		GetStarted.setBounds(98, 315, 239, 38);
		GetStarted.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/GetStarted.png")));
		GetStarted.setVisible(true);
		add(GetStarted);
		
		SearchDrawingBox = new JLabel("");
		SearchDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				SearchDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching_back1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
				mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search_click1.png")));
				mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
				mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
				Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
				mainpage.Mainpanel.removeAll();
				mainpage.Mainpanel.add(child_search_main);
				mainpage.Mainpanel.updateUI();
			}
		});
		SearchDrawingBox.setBounds(192, 397, 290, 220);
		SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
		SearchDrawingBox.setVisible(true);
		add(SearchDrawingBox);
		
		DesignDrawingBox = new JLabel("");
		DesignDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DesignDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting_back1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
				mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
				mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design_click1.png")));
				mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
				Child_Design child_design = new Child_Design(mainpage);
				mainpage.Mainpanel.removeAll();
				mainpage.Mainpanel.add(child_design);
				mainpage.Mainpanel.updateUI();
			}
		});
		DesignDrawingBox.setBounds(539, 397, 290, 220);
		DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
		DesignDrawingBox.setVisible(true);
		add(DesignDrawingBox);
		
		UploadDrawingBox = new JLabel("");
		UploadDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				UploadDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload_back1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
				mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
				mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
				mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));
				Child_Upload child_upload = new Child_Upload(mainpage);
				mainpage.Mainpanel.removeAll();
				mainpage.Mainpanel.add(child_upload);
				mainpage.Mainpanel.updateUI();
			}
		});
		UploadDrawingBox.setBounds(887, 397, 290, 220);
		UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
		UploadDrawingBox.setVisible(true);
		add(UploadDrawingBox);
	}
}
