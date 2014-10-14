package EasyBBK_Swing.gui;

import javax.swing.JLabel;

/**
 * Draw line in this panel
 */

class Pen extends JLabel
{
	private static final long serialVersionUID = 1L;
	
	public boolean inUse = false;
	public Integer type = null;
	
	public Pen()
	{
		super();
		this.setName("pen");
	}
	
	public void inUse()
	{
		this.setEnabled(false);
		inUse = true;
	}
	
	public void noUse()
	{
		this.setEnabled(true);
		inUse = false;
	}
	
	public boolean ifUse()
	{
		return inUse;
	}
	
	// -1=arrow1; 0=arrow2; 1=arrow3; 5=text; 2=eraser;
	public void setType(int type)
	{
		this.type=type;
	}
	
	public int getType()
	{
		return type;
	}
}
