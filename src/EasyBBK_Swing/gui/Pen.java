package EasyBBK_Swing.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

/**
 * Draw line in this panel
 */
class Pen extends JLabel
{
	public boolean inUse = false;
	
	public Pen()
	{
		super();
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
}
