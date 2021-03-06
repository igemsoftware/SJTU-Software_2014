package EasyBBK_Swing.gui;

import javax.swing.JLabel;

/**
 * User can choose to draw lines in three types or remove component when given buttons are clicked.
 * @author LC
 *
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
	
	// -1=line_inhibit; 0=line_enhance; 1=line_other; 2=eraser;
	public void setType(int type)
	{
		this.type=type;
	}
	
	public int getType()
	{
		return type;
	}
}
