package EasyBBK_Swing.gui;

import java.awt.Cursor;

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
	public JLabel CompareDrawingBox;
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
				SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching_back.png")));
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
				
				if(mainpage.child_search_main_current == null){
					Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_search_main);
					child_search_main.SearchText.requestFocus();
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 1;
				}
				else if(mainpage.child_search_main_current != null && mainpage.child_search_current == null){
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(mainpage.child_search_main_current);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 1;
				}
				else if(mainpage.child_search_current != null){
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(mainpage.child_search_current);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 11;
				}
			}
		});
		SearchDrawingBox.setBounds(40, 396, 290, 220);
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

				if(mainpage.child_design_current == null){
					Child_Design child_design = new Child_Design(mainpage);
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_design);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 2;
				}
				else if(mainpage.child_design_current != null){
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(mainpage.child_design_current);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 2;
				}
			}
		});
		DesignDrawingBox.setBounds(371, 396, 290, 220);
		DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
		DesignDrawingBox.setVisible(true);
		add(DesignDrawingBox);
		
		UploadDrawingBox = new JLabel("");
		UploadDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				UploadDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload_back.png")));
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

				if(mainpage.child_upload_current == null){
					Child_Upload child_upload = new Child_Upload(mainpage);
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_upload);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 3;
				}
				else if(mainpage.child_upload_current != null){
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(mainpage.child_upload_current);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 3;
				}
			}
		});
		UploadDrawingBox.setBounds(705, 396, 290, 220);
		UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
		UploadDrawingBox.setVisible(true);
		add(UploadDrawingBox);
		
		CompareDrawingBox = new JLabel("");
		CompareDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
				mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
				mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
				mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));

				if(mainpage.child_compare_current == null){
					Child_Compare child_compare = new Child_Compare(mainpage);
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_compare);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 4;
				}
				else if(mainpage.child_compare_current != null){
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(mainpage.child_compare_current);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 4;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CompareDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare_back.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare.png")));
			}
		});
		CompareDrawingBox.setBounds(1039, 396, 290, 220);
		CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare.png")));
		CompareDrawingBox.setVisible(true);
		add(CompareDrawingBox);
	}
}
