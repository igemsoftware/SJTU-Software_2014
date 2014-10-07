package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Category_item extends JPanel {
	public JComboBox Choice;
	public String content;
	/**
	 * Create the panel.
	 */
	public Category_item() {
		setBounds(0, 0, 486, 30);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		String category[] = {"//biosafety", "//biosafety/kill_switch", "//biosafety/semantic_containment", 
				"//biosafety/xnase", "//cds", "//cds/biosynthesis", "//cds/chromatinremodeling", 
				"//cds/enzyme", "//cds/enzyme/chromatinremodeling", "//cds/enzyme/dnapolymerase", 
				"//cds/enzyme/dnase", "//cds/enzyme/endonuclease", "//cds/enzyme/endonuclease/restriction", 
				"//cds/enzyme/exonuclease", "//cds/enzyme/ligase", "//cds/enzyme/lysis", 
				"//cds/enzyme/methylation", "//cds/enzyme/phosphorylation", "//cds/enzyme/protease", 
				"//cds/enzyme/rnap", "//cds/enzyme/rnapolymerase", "//cds/ligand", "//cds/lysis", 
				"//cds/membrane", "//cds/membrane/channel", "//cds/membrane/extracellular", 
				"//cds/membrane/lysis", "//cds/membrane/pump", "//cds/membrane/receptor", 
				"//cds/membrane/transporter", "//cds/receptor", "//cds/receptor/antibody", 
				"//cds/reporter", "//cds/reporter/cfp", "//cds/reporter/chromoprotein", 
				"//cds/reporter/gfp", "//cds/reporter/rfp", "//cds/reporter/yfp", 
				"//cds/selectionmarker", "//cds/selectionmarker/antibioticresistance", 
				"//cds/transcriptionalregulator", "//cds/transcriptionalregulator/activator", 
				"//cds/transcriptionalregulator/repressor", "//chassis", "//chassis/aferrooxidans", 
				"//chassis/bacteriophage", "//chassis/bacteriophage/t3", "//chassis/bacteriophage/t4", 
				"//chassis/bacteriophage/t7", "//chassis/eukaryote", "//chassis/eukaryote/human", 
				"//chassis/eukaryote/pichia", "//chassis/eukaryote/yeast", "//chassis/miscellaneous", 
				"//chassis/multihost", "//chassis/organelle", "//chassis/organelle/chloroplast", 
				"//chassis/organelle/mitochondrion", "//chassis/prokaryote", 
				"//chassis/prokaryote/bcepacia", "//chassis/prokaryote/bsubtilis", 
				"//chassis/prokaryote/cyanobacterium", "//chassis/prokaryote/ecoli", 
				"//chassis/prokaryote/lactobacillus", "//chassis/prokaryote/mflorum", 
				"//chassis/prokaryote/pananatis", "//chassis/prokaryote/pmirabilis", 
				"//chassis/prokaryote/pputida", "//chassis/prokaryote/reuphora", 
				"//chassis/prokaryote/salmonella", "//chassis/prokaryote/sespanaensis", 
				"//chassis/prokaryote/subtilis", "//chassis/prokaryote/synechocystis", 
				"//chassis/prokaryote/vharveyi", "//classic/composite/uncategorized", 
				"//classic/device/uncategorized", "//classic/generator/plasmids", 
				"//classic/generator/prc", "//classic/generator/prct", "//classic/generator/rc", 
				"//classic/generator/rct", "//classic/generator/uncategorized", 
				"//classic/intermediate/uncategorized", "//classic/inverter/uncategorized",
				"//classic/measurement/o_h", "//classic/measurement/uncategorized", 
				"//classic/other/uncategorized", "//classic/plasmid/measurement", 
				"//classic/plasmid/uncategorized", "//classic/project/uncategorized", 
				"//classic/rbs/uncategorized", "//classic/regulatory/other", 
				"//classic/regulatory/uncategorized", "//classic/reporter", 
				"//classic/reporter/constitutive", "//classic/reporter/multiple", 
				"//classic/reporter/pret", "//classic/reporter/ret", "//classic/rna/uncategorized", 
				"//classic/signalling/receiver", "//classic/signalling/sender", 
				"//classic/temporary/uncategorized", "//collections", "//collections/biofab", 
				"//direction", "//direction/bidirectional", "//direction/forward", "//direction/reverse", 
				"//dna", "//dna/aptamer", "//dna/bioscaffold", "//dna/chromosomalintegration", 
				"//dna/cloningsite", "//dna/conjugation", "//dna/nucleotide", "//dna/origami", 
				"//dna/origin_of_replication", "//dna/primerbindingsite", "//dna/restrictionsite", 
				"//dna/scar", "//dna/spacer", "//dna/transposome/tn5", "//dna/transposon", 
				"//function/biofuels", "//function/biosynthesis", "//function/biosynthesis/ahl", 
				"//function/biosynthesis/butanol", "//function/biosynthesis/heme", 
				"//function/biosynthesis/isoprenoid", "//function/biosynthesis/odorant", 
				"//function/biosynthesis/phycocyanobilin", "//function/biosynthesis/plastic", 
				"//function/biosynthesis/pyocyanin", "//function/celldeath", "//function/cellsignalling", 
				"//function/coliroid", "//function/conjugation", "//function/crispr", 
				"//function/crispr/cas9", "//function/degradation", "//function/degradation/ahl", 
				"//function/degradation/bisphenol", "//function/degradation/cellulose", "//function/dna", 
				"//function/fret", "//function/immunology", "//function/mismatchrepair",
				"//function/motility", "//function/odor", "//function/recombination", 
				"//function/recombination/cre", "//function/recombination/fim", 
				"//function/recombination/flp", "//function/recombination/hin", 
				"//function/recombination/lambda", "//function/recombination/p22", 
				"//function/recombination/xer", "//function/regulation/transcriptional", 
				"//function/reporter", "//function/reporter/color", "//function/reporter/fluorescence", 
				"//function/reporter/light", "//function/reporter/pigment", "//function/sensor", 
				"//function/sensor/lead", "//function/sensor/light", "//function/structures", 
				"//function/tumorkillingbacteria", "//legal/ip-free", "//plasmid", 
				"//plasmid/chromosomalintegration", "//plasmid/component/cloningsite", 
				"//plasmid/component/insulation", "//plasmid/component/origin", "//plasmid/component/other", 
				"//plasmid/component/primerbindingsite", "//plasmid/construction", "//plasmid/expression", 
				"//plasmid/expression/t7", "//plasmid/measurement", "//plasmid/sp6", "//plasmidbackbone", 
				"//plasmidbackbone/archive", "//plasmidbackbone/assembly", 
				"//plasmidbackbone/component/defaultinsert", 
				"//plasmidbackbone/component/selectionmarker/antibioticresistance", 
				"//plasmidbackbone/copynumber", "//plasmidbackbone/copynumber/high", 
				"//plasmidbackbone/copynumber/inducible", "//plasmidbackbone/copynumber/low", "//plasmidbackbone/copynumber/medium", "//plasmidbackbone/expression", "//plasmidbackbone/expression/constitutive", "//plasmidbackbone/expression/inducible", "//plasmidbackbone/libraryscreening", "//plasmidbackbone/libraryscreening/codingsequence", "//plasmidbackbone/libraryscreening/promoter", "//plasmidbackbone/libraryscreening/rbscodingsequence", "//plasmidbackbone/operation", "//plasmidbackbone/proteinfusion", "//plasmidbackbone/synthesis", "//plasmidbackbone/version/10", "//plasmidbackbone/version/3", "//plasmidbackbone/version/4", "//plasmidbackbone/version/5", "//primer/m13", "//primer/part", "//primer/part/amplification", "//primer/part/sequencing", "//primer/plasmid/amplification", "//primer/reporter/cfp", "//primer/reporter/gfp", "//primer/reporter/yfp", "//primer/sp6", "//primer/t3", "//primer/t7", "//promoter", "//promoter/anderson", "//promoter/iron", "//promoter/logic/ustc", "//promoter/stresskit", "//proteindomain", "//proteindomain/activation", "//proteindomain/affinity", "//proteindomain/binding", "//proteindomain/cleavage", "//proteindomain/degradation", "//proteindomain/dnabinding", "//proteindomain/head", "//proteindomain/internal", "//proteindomain/internal/special", "//proteindomain/linker", "//proteindomain/localization", "//proteindomain/repression", "//proteindomain/tail", "//proteindomain/transmembrane", "//rbs/prokaryote", "//rbs/prokaryote/constitutive", "//rbs/prokaryote/constitutive/anderson", "//rbs/prokaryote/constitutive/community", "//rbs/prokaryote/constitutive/constitutive", "//rbs/prokaryote/constitutive/miscellaneous", "//rbs/prokaryote/constitutive/rackham", "//rbs/prokaryote/regulated/issacs", "//rbs/prokaryotic/constitutive/miscellaneous", "//regulation", "//regulation/constitutive", "//regulation/multiple", "//regulation/negative", "//regulation/positive", "//regulation/unknown", "//ribosome", "//ribosome/eukaryote", "//ribosome/eukaryote/yeast", "//ribosome/prokaryote", "//ribosome/prokaryote/bcepacia", "//ribosome/prokaryote/bsubtilis", "//ribosome/prokaryote/custom", "//ribosome/prokaryote/ecoli", "//ribosome/prokaryote/pananatis", "//ribosome/prokaryote/pputida", "//ribosome/prokaryote/salmonella", "//ribosome/prokaryote/sespanaensis", "//rnap", "//rnap/bacteriophage/sp6", "//rnap/bacteriophage/t3", "//rnap/bacteriophage/t7", "//rnap/eukaryote", "//rnap/eukaryote/pichia", "//rnap/eukaryote/yeast", "//rnap/miscellaneous", "//rnap/prokaryote", "//rnap/prokaryote/aferrooxidans", "//rnap/prokaryote/ecoli", "//rnap/prokaryote/ecoli/sigma24", "//rnap/prokaryote/ecoli/sigma25", "//rnap/prokaryote/ecoli/sigma32", "//rnap/prokaryote/ecoli/sigma54", "//rnap/prokaryote/ecoli/sigma70", "//rnap/prokaryote/ecoli/sigmas", "//rnap/prokaryote/pmirabilis", "//rnap/prokaryote/pputida", "//rnap/prokaryote/reuphora", "//rnap/prokaryote/salmonella", "//rnap/prokaryote/subtilis/sigmaa", "//rnap/prokaryote/subtilis/sigmab", "//rnap/prokaryote/synechocystis", "//rnap/prokaryote/vharveyi/sigma54", "//t3", "//t3/t2", "//t3/t4", "//terminator", "//terminator/double", "//terminator/single", "//test", "//test/test2", "//test1", "//viral_vectors", "//viral_vectors/aav", "//viral_vectors/aav/capsid_coding", "//viral_vectors/aav/miscellaneous", "//viral_vectors/aav/vector_plasmid"};
		Choice = new JComboBox(category);
		content = "//biosafety";
		Choice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Choice.setSelectedIndex(0);
		Choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					content = (String)Choice.getSelectedItem();
				}
			}
		});
		Choice.setBounds(0, 0, 486, 30);
		add(Choice);
		
		setVisible(true);
	}
}
