package EasyBBK_Swing.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

public class DrawLineListener implements MouseInputListener
{
	Pen p = new Pen();
	LinePanel linePanel= new LinePanel(p);
	
	DrawLineListener(Pen p,LinePanel linePanel)
	{
		super();
		this.p = p;
		this.linePanel=linePanel;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if (p!=null & p.ifUse() & p.getType().equals("promote"))
		{
			if (e.getButton()==MouseEvent.BUTTON3)
			{
				linePanel.addPoint(e.getPoint());
				linePanel.repaint();
				linePanel.addLine();
			}
			else if (e.getClickCount()==1 & e.getButton()==MouseEvent.BUTTON1)
			{
				linePanel.addPoint(e.getPoint());
				linePanel.repaint();				
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
		
	public void mouseReleased(MouseEvent e){}
		
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}
