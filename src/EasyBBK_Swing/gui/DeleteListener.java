package EasyBBK_Swing.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class DeleteListener implements KeyListener
{
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
		{
			((e.getComponent()).getParent()).remove(e.getComponent());
			((e.getComponent()).getParent()).repaint();
		}		
	}
	
	public void keyTyped(KeyEvent e){} 
	 
	public void keyReleased(KeyEvent e){} 

}
