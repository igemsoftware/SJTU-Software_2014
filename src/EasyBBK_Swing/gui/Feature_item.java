package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Feature_item extends JPanel {
	public JComboBox Choice;
	public String content1;
	public JTextField Label;
	public JTextField Start;
	public JTextField End;
	public JComboBox Direction;
	public String content2;
	/**
	 * Create the panel.
	 */
	public Feature_item() {
		setBounds(0, 0, 486, 30);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		String feature[] = {"misc", "promoter", "rbs", "cds", "start", "stop", "scar", "dna", "tag", 
				"stem_loop", "mutation", "s_mutation", "primer_binding", "operator", "binding", "protein", 
				"conserved", "polya", "barcode", "BioBrick"};
		Choice = new JComboBox(feature);
		content1 = "misc";
		Choice.setFont(new Font("Arial", Font.PLAIN, 20));
		Choice.setSelectedIndex(0);
		Choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					content1 = (String)Choice.getSelectedItem();
				}
			}
		});
		Choice.setBounds(0, 0, 150, 30);
		add(Choice);
		
		Label = new JTextField();
		Label.setBounds(153, 0, 150, 30);
		Label.setFont(new Font("Arial", Font.PLAIN, 20));
		add(Label);
		Label.setColumns(10);
		
		Start = new JTextField();
		Start.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }
		});
		Start.setBounds(306, 0, 50, 30);
		Start.setFont(new Font("Arial", Font.PLAIN, 20));
		add(Start);
		Start.setColumns(10);
		
		End = new JTextField();
		End.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {  
                int keyChar = e.getKeyChar();                 
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                      
                }else{  
                    e.consume(); //关键，屏蔽掉非法输入  
                }  
            }
		});
		End.setBounds(359, 0, 50, 30);
		End.setFont(new Font("Arial", Font.PLAIN, 20));
		add(End);
		End.setColumns(10);
		
		String direction[] = {"Fwd", "Rev"};
		Direction = new JComboBox(direction);
		content2 = "Fwd";
		Direction.setFont(new Font("Arial", Font.PLAIN, 20));
		Direction.setSelectedIndex(0);
		Direction.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					content2 = (String)Choice.getSelectedItem();
				}
			}
		});
		Direction.setBounds(412, 0, 74, 30);
		add(Direction);
		
		setVisible(true);
	}
}