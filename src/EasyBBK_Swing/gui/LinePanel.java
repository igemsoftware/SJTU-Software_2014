package EasyBBK_Swing.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

class LinePanel extends JPanel
{
	private Point point = new Point(0,0);
	public ArrayList<ArrayList<Point>> pointList= new ArrayList<ArrayList<Point>>();
	private Pen p;
	
	public LinePanel(Pen p) 
	{
		super.repaint();
		this.p=p;
		setOpaque(false);
		this.setName("linePanel");
        repaint();  
    }  
  
    public void paintComponent(Graphics g)
    {
    	if (p.ifUse())
    	{
    		g.drawLine(0, 0, 100, 100);
    	}
    	else
    	{
    		g.drawLine(0, 0, 100, 50);
    	}
    } 
    
    private void addPoint(Point p)
    {
    	
    }

}
