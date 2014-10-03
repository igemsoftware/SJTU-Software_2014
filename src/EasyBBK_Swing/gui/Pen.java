package EasyBBK_Swing.gui;

import javax.swing.JLabel;

/**
 * Draw line in this panel
 */

class Pen extends JLabel
{
	public boolean inUse = false;
	public String type = null;
	
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
		System.out.println("MB");	
		inUse = false;
	}
	
	public boolean ifUse()
	{
		return inUse;
	}
	
	public void setType(String type)
	{
		this.type=type;
	}
	
	public String getType()
	{
		return type;
	}
}
