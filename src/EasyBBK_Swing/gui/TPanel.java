package EasyBBK_Swing.gui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JLayeredPane;

/**
 * Create a transparent panel to show the visible result of the distribution of components,
 * when biobricks move close to the backbone.
 * @author LC
 *
 */
class TPanel extends JLayeredPane
{
	private static final long serialVersionUID = 1L;
	
	private AlphaComposite composite;
	private BufferedImage dragged = null;
	private Point point = new Point(0,0);
	
	public TPanel() 
	{
		super.repaint();
		setOpaque(false);
		composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		this.setName("TPanel");
        repaint();  
    }  
  
    public void paintComponent(Graphics g)
    {  
    	if (dragged == null)
    	{
    		return;
    	}
    	else
    	{
    		Graphics2D graph = (Graphics2D)g;
            graph.setComposite(composite);           
    		graph.drawImage(dragged,point.x,point.y,null);
    	}
    } 
    
    public void setImage( BufferedImage dragged)
	{
		this.dragged = dragged;
	}
	
	public void setPoint(Point location)
	{
		point = location;
	}
	
}
