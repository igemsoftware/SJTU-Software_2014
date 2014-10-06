package EasyBBK_Swing.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * Make JLabel contain more information
 */
@SuppressWarnings("serial")
class BackBone extends JLabel implements MouseListener, MouseMotionListener
{
	public int ID = 0;
	Point newPoint =new Point(0,0);
	private Rectangle outer_rect = new Rectangle();
	private Rectangle inner_rect = new Rectangle();
	private Rectangle rect_right = new Rectangle();
	private Rectangle rect_left = new Rectangle();
	private int reType = 0;
	public boolean activate = false;
	public boolean resizeable = false;
	public JLayeredPane panel = new JLayeredPane();
	public TPanel Tpanel = new TPanel();
	
	public BackBone(String s)
	{
		super(s);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public BackBone()
	{
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public BackBone(JLayeredPane panel, TPanel Tpanel)
	{
		super();
		this.panel=panel;
		this.Tpanel=Tpanel;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (activate == true)
		{
			outer_rect = this.getVisibleRect();
			outer_rect.setRect(outer_rect.x+1, outer_rect.y+1, outer_rect.width-2, outer_rect.height-2);
			inner_rect.setRect(outer_rect.x+3, outer_rect.y, outer_rect.width-6, outer_rect.height);
			rect_right.setRect(outer_rect.x+outer_rect.width-3, outer_rect.y+3, 2, outer_rect.height-6);
			rect_left.setRect(outer_rect.x+1, outer_rect.y+3, 2, outer_rect.height-6);
		}
	}
	
	public void activate()
	{
		activate = true;
	}
	
	public boolean getResize()
	{
		return resizeable;
	}
	
	public void mousePressed(MouseEvent e) 
	{
		if (activate == true)
		{
			if ((outer_rect.contains(e.getPoint())) && (!inner_rect.contains(e.getPoint()))) 
				resizeable = true;
			else
				resizeable = false;
			
			if (rect_right.contains(e.getPoint())) 
			{
		    	reType=2;
			}
			else if (rect_left.contains(e.getPoint())) 
			{
		    	reType=1;
			}
		}
		panel.setPosition(e.getComponent(),-1);
	}

	public void mouseMoved(MouseEvent e) 
	{
		if (activate == true)
		{
			if ((outer_rect.contains(e.getPoint())) && (!inner_rect.contains(e.getPoint()))) 
			{
				this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
				panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
		    	Tpanel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			}				
			else
			{
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		    	Tpanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
		panel.setPosition(e.getComponent(),-1);
	}

	public void mouseDragged(MouseEvent e) 
	{
		if (activate == true)
		{
			if (resizeable)
			{
				newPoint = SwingUtilities.convertPoint((BackBone)e.getSource() , 
						e.getPoint(), ((BackBone)e.getSource()).getParent());
				
				switch (reType)
				{
				case 1:
					if (this.getX()+this.getWidth()-newPoint.x>20)
					{
						this.setSize(new Dimension(this.getX()+this.getWidth()-newPoint.x, this.getHeight()));
						this.setLocation(new Point(newPoint.x,this.getY()));
					}
					break;
				case 2:
					if (newPoint.x-this.getX()>20)
					{
						this.setSize(new Dimension(newPoint.x-this.getX(), this.getHeight()));
					}
					break;
				}
				
				//test
				ImageIcon image = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/backbone_move.png"));
				image.setImage(image.getImage().getScaledInstance(this.getWidth(),50,Image.SCALE_DEFAULT));
				this.setIcon(image);
			}
		}
		panel.setPosition(e.getComponent(),-1);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	Tpanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		resizeable = false;
		panel.setPosition(e.getComponent(),-1);
	}

	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
}

