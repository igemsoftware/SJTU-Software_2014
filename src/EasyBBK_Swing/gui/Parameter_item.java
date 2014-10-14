package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parameter_item extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JComboBox<?> Choice;
	public String content;
	public JTextField textField;
	/**
	 * Create the panel.
	 */
	public Parameter_item() {
		setBounds(0, 0, 486, 30);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		String parameter[] = {"--Add Another Parameter--","abs", "activation_coeficient", "biology", 
				"chassis", "color", "control", "copies", "device_type", 
				"direction", "dissociation_constant", "ec_num", "efficiency", 
				"emission", "emit", "excitation", "excite", "family", 
				"forward_efficiency", "function", "genotype", "hill_coeficient", 
				"input_s", "insert", "i_h", "i_l", "kegg", "latency", "len_end", 
				"len_fr", "len_start", "ligands", "lum", "mcs", "n/a", 
				"negative_regulators", "origin", "output", "output_s", "o_h", 
				"o_l", "pct_gc", "positive_regulators", "protein", "proteins", 
				"protein_in", "protein_out", "rbs", "receiver", "resistance", 
				"reversed_version", "reverse_efficiency", "sender", 
				"signalling_molecule", "strain", "swisspro", "switch_point", 
				"t-oh", "t-ol", "tag", "target", "type", "t_hl", "t_lh", "t_m", "uniprot"};
		Choice = new JComboBox<Object>(parameter);
		content = "abs";
		Choice.setFont(new Font("Arial", Font.PLAIN, 20));
		Choice.setSelectedIndex(0);
		Choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					content = (String)Choice.getSelectedItem();
				}
			}
		});
		Choice.setBounds(0, 0, 220, 30);
		add(Choice);
		
		textField = new JTextField();
		textField.setBounds(223, 0, 263, 30);
		textField.setFont(new Font("Arial", Font.PLAIN, 20));
		add(textField);
		textField.setColumns(10);
		
		setVisible(true);
	}
}
