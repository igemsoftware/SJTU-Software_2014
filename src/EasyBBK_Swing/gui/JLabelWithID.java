package EasyBBK_Swing.gui;

import javax.swing.JLabel;

/**
 * Make JLabel contain more information
 */
class JLabelWithID extends JLabel
{	
	private static final long serialVersionUID = 1L;
	
	public int ID = 0;
	public String bioName="";
	
	public JLabelWithID(String s)
	{
		super(s);
	}
	
	public JLabelWithID()
	{
		super();
	}
	
}
