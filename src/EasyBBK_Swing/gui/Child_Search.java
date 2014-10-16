package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import data_center.*;

public class Child_Search extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JTextField textField;
	public JPanel Result;
	public JLabel Search;
	public MainPage mainpage;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane1;
	private JLabel Blast;
	public JPanel Details;
	public JLabel page6;
	public JLabel page7;
	public JLabel page8;
	public JLabel page9;
	public JLabel page10;
	public int currentpage = 1;
	public SearchingResultPage searchingresultpage;
	public JScrollBar scrollbar;
	public JScrollBar scrollbar1;
	public SearchCenter searchcenter;
	public Information information;
	public int blast;
	public JPanel resultpanel;
	public JLabel previouspage;
	public JLabel nextpage;
	public JLabel total;
	public JLabel totalpagenum;
	public JLabel showpagenum;
	public boolean confirmed_clicked;
	public Child_Search child_search;
	public String keyword;
	public ArrayList<BbkDetail> comparisonlist = new ArrayList<BbkDetail>();
	public ArrayList<InitializeResultPage> threadlist = new ArrayList<InitializeResultPage>();
	/**
	 * Create the panel.
	 */
	public Child_Search(MainPage mainpage1, String searchcontent, Information information1, int blast1, boolean confirmed_clicked1) {
		child_search = this;
		confirmed_clicked = confirmed_clicked1;
		searchcenter = new SearchCenter();
		keyword = searchcontent;
		blast = blast1;
		information = information1;
		mainpage = mainpage1;
		if(mainpage.small == false){
			setBounds(0, 0, 1366, 670);
		}
		else if(mainpage.small == true){
			setBounds(0, 0, 1280, 670);
		}
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		Result = new JPanel();
		if(mainpage.small == false){
			Result.setBounds(0, 0, 683, 670);
		}
		else if(mainpage.small == true){
			Result.setBounds(0, 0, 640, 670);
		}
		Result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					Result.requestFocus();
			}
		});
		Result.setBackground(new Color(255, 255, 255));
		//Result.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Result);
		Result.setLayout(null);
		
		final JLabel Back = new JLabel();
		if(mainpage.small == false){
			Back.setBounds(1, 0, 58, 55);
		}
		else if(mainpage.small == true){
			Back.setBounds(1, 0, 58, 55);
		}
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					SearchResultList previousList = searchcenter.rollBack();
					if(previousList == null){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							child_search_main.SearchText.requestFocus();
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						requestFocus();
						textField.setText(previousList.keyword);
						resultpanel.removeAll();
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, previousList.keyword, true, previousList, mainpage.small);
						initializeresultpage.start();
						resultpanel.updateUI();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
			}
		});
		Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
		Result.add(Back);
		
		final JLabel Forward = new JLabel();
		if(mainpage.small == false){
			Forward.setBounds(59, 0, 54, 55);
		}
		else if(mainpage.small == true){
			Forward.setBounds(59, 0, 54, 55);
		}
		Forward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					SearchResultList forwardList = searchcenter.goForward();
					if(forwardList == null) return;
					else{
						requestFocus();
						textField.setText(forwardList.keyword);
						resultpanel.removeAll();
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, forwardList.keyword, true, forwardList, mainpage.small);
						initializeresultpage.start();
						resultpanel.updateUI();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
			}
		});
		Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
		Result.add(Forward);

		Search = new JLabel();
		if(mainpage.small == false){
			Search.setBounds(490, 0, 104, 53);
		}
		else if(mainpage.small == true){
			Search.setBounds(447, 0, 104, 53);
		}
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(textField.getText().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						if(threadlist.size() != 0){
							for(int i = 0; i < threadlist.size(); i++){
								threadlist.get(i).interrupt();
							}
							for(int i = 0; i < threadlist.size(); i++){
								threadlist.remove(i);
							}
							return;
						}
						requestFocus();
						currentpage = 1;
						blast = 1;
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, textField.getText(), false, mainpage.small);
						initializeresultpage.start();
						threadlist.add(initializeresultpage);
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		Result.add(Search);
		
		textField = new JTextField();
		if(mainpage.small == false){
			textField.setBounds(114, 0, 378, 54);
		}
		else if(mainpage.small == true){
			textField.setBounds(114, 0, 335, 54);
		}
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					if(textField.getText().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							child_search_main.SearchText.requestFocus();
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						if(threadlist.size() != 0){
							for(int i = 0; i < threadlist.size(); i++){
								threadlist.get(i).interrupt();
							}
							for(int i = 0; i < threadlist.size(); i++){
								threadlist.remove(i);
							}
							return;
						}
						Search.requestFocus();
						currentpage = 1;
						blast = 1;
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, textField.getText(), false, mainpage.small);
						initializeresultpage.start();
						threadlist.add(initializeresultpage);
					}
				}
			}
		});
		textField.setFont(new Font("Arial", Font.PLAIN, 40));
		textField.setVisible(true);
		Result.add(textField);
		textField.setColumns(20);
		
		resultpanel = new JPanel();
		if(mainpage.small == false){
			resultpanel.setBounds(51, 85, 587, 583);
		}
		else if(mainpage.small == true){
			resultpanel.setBounds(30, 85, 587, 583);
		}
		resultpanel.setBackground(new Color(255, 255, 255));
		
		Result.add(resultpanel);
		resultpanel.setLayout(null);
		
		
		Blast = new JLabel("");
		if(mainpage.small == false){
			Blast.setBounds(596, 0, 84, 53);
		}
		else if(mainpage.small == true){
			Blast.setBounds(553, 0, 84, 53);
		}
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					if(textField.getText().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						if(threadlist.size() != 0){
							for(int i = 0; i < threadlist.size(); i++){
								threadlist.get(i).interrupt();
							}
							for(int i = 0; i < threadlist.size(); i++){
								threadlist.remove(i);
							}
							return;
						}
						requestFocus();
						currentpage = 1;
						blast = 2;
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, textField.getText(), false, mainpage.small);
						initializeresultpage.start();
						threadlist.add(initializeresultpage);
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Blast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast_enter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
			}
		});
		Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
		Result.add(Blast);
		
		Details = new JPanel();
		if(mainpage.small == false){
			Details.setBounds(684, 0, 683, 670);
		}
		else if(mainpage.small == true){
			Details.setBounds(641, 0, 640, 670);
		}
		Details.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Details.requestFocus();
			}
		});
		
		Details.setBackground(new Color(255, 255, 255));
		//Details.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Details);
		Details.setLayout(null);
		updateUI();
		
		InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, keyword, false, mainpage.small);
		initializeresultpage.start();
		threadlist.add(initializeresultpage);
	}
}