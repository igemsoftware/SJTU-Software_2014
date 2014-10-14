package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import data_center.SketchCenter;
import data_center.SketchOperation;
import data_center.SketchProject;

@SuppressWarnings("serial")
class TextLabel extends JTextPane implements MouseListener, MouseMotionListener
{
	Point newPoint =new Point(0,0);
	//Drag area
	private Rectangle outer_rect = new Rectangle();
	private Rectangle middle_rect = new Rectangle();
	private Rectangle inner_rect = new Rectangle();
	private Rectangle rect_right = new Rectangle();
	private Rectangle rect_left = new Rectangle();
	private Rectangle rect_top = new Rectangle();
	private Rectangle rect_bottom = new Rectangle();
	private Rectangle rect_lefttop = new Rectangle();
	private Rectangle rect_leftbottom = new Rectangle();
	private Rectangle rect_righttop = new Rectangle();
	private Rectangle rect_rightbottom = new Rectangle();
	private Rectangle rect_move = new Rectangle();
	
	private int reType = 0;
	int ID=0;
	public boolean resizeable = false;
	public boolean movable = false;
	public boolean focus = false;
	SimpleAttributeSet attr = new SimpleAttributeSet();
	public JLayeredPane panel = new JLayeredPane();
	public TPanel Tpanel = new TPanel();	
	
	SketchCenter sketchCenter;
	
	public TextLabel(JLayeredPane panel, TPanel Tpanel, SketchCenter sketchCenter)
	{
		super();
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_CENTER);
		StyleConstants.setForeground(attr, Color.black);
		StyleConstants.setBold(attr, false);
		StyleConstants.setItalic(attr, false);
		StyleConstants.setFontSize(attr, 16);
		StyleConstants.setFontFamily(attr, "Arial");
		this.setParagraphAttributes(attr, true);
		this.setName("text");
		this.panel=panel;
		this.Tpanel=Tpanel;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.sketchCenter = sketchCenter;
	}
	
	public boolean inUse = false;
	
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
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setName("text");
		outer_rect = this.getVisibleRect();
		middle_rect.setRect(outer_rect.x+2, outer_rect.y+2, outer_rect.width-4, outer_rect.height-4);
		inner_rect.setRect(outer_rect.x+7, outer_rect.y+7, outer_rect.width-14, outer_rect.height-14);
		rect_right.setRect(outer_rect.x+outer_rect.width-7, outer_rect.y+7, 5, outer_rect.height-14);
		rect_left.setRect(outer_rect.x+2, outer_rect.y+7, 5, outer_rect.height-14);
		rect_top.setRect(outer_rect.x+7, outer_rect.y+2, outer_rect.width-14, 5);
		rect_bottom.setRect(outer_rect.x+7, outer_rect.y+outer_rect.height-7, outer_rect.width-14, 5);
		rect_lefttop.setRect(outer_rect.x+2, outer_rect.y+2, 5, 5);
		rect_leftbottom.setRect(outer_rect.x+2, outer_rect.y+outer_rect.height-7, 5, 5);
		rect_righttop.setRect(outer_rect.x+outer_rect.width-7, outer_rect.y+2, 5, 5);
		rect_rightbottom.setRect(outer_rect.x+outer_rect.width-7, outer_rect.y+outer_rect.height-7, 5, 5);
		rect_move.setRect((int)(outer_rect.x+outer_rect.getWidth()/2-8), outer_rect.y+2, 15, 5);
	}
	
	public boolean getResize()
	{
		return resizeable;
	}
	public void mousePressed(MouseEvent e) 
	{
		e.getComponent().requestFocus();
		if ((middle_rect.contains(e.getPoint())) & (!inner_rect.contains(e.getPoint())) & (!rect_move.contains(e.getPoint())))
		{
			resizeable = true;			
		}
		else
		{
			resizeable = false;			
		}
		
		if (rect_right.contains(e.getPoint())) 
		{
	    	reType=4;
		}
		else if (rect_left.contains(e.getPoint())) 
		{
	    	reType=8;
		}
		else if (rect_top.contains(e.getPoint()) & (!rect_move.contains(e.getPoint()))) 
		{
	    	reType=2;
		}
		else if (rect_bottom.contains(e.getPoint())) 
		{
	    	reType=6;
		}
		else if (rect_lefttop.contains(e.getPoint()))
		{
	    	reType=1;
		}
		else if (rect_leftbottom.contains(e.getPoint()))
		{
	    	reType=7;
		}
		else if (rect_righttop.contains(e.getPoint()))
		{
	    	reType=3;
		}
		else if (rect_rightbottom.contains(e.getPoint()))
		{
	    	reType=5;
		}
		else
		{
	    	reType=0;
		}
		
		if (rect_move.contains(e.getPoint()))
		{
			movable=true;
		}
		else
		{
			movable=false;
		}

		panel.setPosition(e.getComponent(),-1);
	}

	public void mouseMoved(MouseEvent e) 
	{
		if (outer_rect.contains(e.getPoint()))
		{
			TitledBorder border = new TitledBorder("");
			this.setBorder(border);
		}
		
		if (rect_right.contains(e.getPoint())) 
		{
			this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
	    	reType=4;
		}
		else if (rect_left.contains(e.getPoint())) 
		{
			this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
	    	reType=8;
		}
		else if (rect_top.contains(e.getPoint()) & (!rect_move.contains(e.getPoint()))) 
		{
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
	    	reType=2;
		}
		else if (rect_bottom.contains(e.getPoint())) 
		{
			this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
	    	reType=6;
		}
		else if (rect_lefttop.contains(e.getPoint()))
		{
			this.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
	    	reType=1;
		}
		else if (rect_leftbottom.contains(e.getPoint()))
		{
			this.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
	    	reType=7;
		}
		else if (rect_righttop.contains(e.getPoint()))
		{
			this.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
	    	reType=3;
		}
		else if (rect_rightbottom.contains(e.getPoint()))
		{
			this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
			panel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
	    	reType=5;
		}
		else if (rect_move.contains(e.getPoint()))
		{
			this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			panel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		else
		{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    	reType=0;
		}
		panel.setPosition(e.getComponent(),-1);
	}

	public void mouseDragged(MouseEvent e) 
	{
		if (resizeable)
		{
			newPoint = SwingUtilities.convertPoint((TextLabel)e.getSource() , 
					e.getPoint(), ((TextLabel)e.getSource()).getParent());
			switch (reType)
			{
			case 1:	
				if (this.getWidth()+this.getX()-newPoint.x>35 & this.getHeight()+this.getY()-newPoint.y>35)
				{
					this.setSize(new Dimension(this.getWidth()+this.getX()-newPoint.x, this.getHeight()+this.getY()-newPoint.y));
					this.setLocation(newPoint);
				}				
				break;
			case 2:
				if (this.getY()+this.getHeight()-newPoint.y>35)
				{
					this.setSize(new Dimension(this.getWidth(), this.getY()+this.getHeight()-newPoint.y));
					this.setLocation(new Point(this.getX(),newPoint.y));
				}
				break;
			case 3:
				if (newPoint.x-this.getX()>35 & this.getHeight()+this.getY()-newPoint.y>35)
				{
					this.setSize(new Dimension(newPoint.x-this.getX(), this.getHeight()+this.getY()-newPoint.y));
					this.setLocation(new Point(this.getX(),newPoint.y));
				}
				break;
			case 4:
				if (newPoint.x-this.getX()>35)
				{
					this.setSize(new Dimension(newPoint.x-this.getX(), this.getHeight()));
				}
				break;
			case 5:
				if (newPoint.x-this.getX()>35 & newPoint.y-this.getY()>35)
				{
					this.setSize(new Dimension(newPoint.x-this.getX(), newPoint.y-this.getY()));
				}
				break;
			case 6:
				if (newPoint.y-this.getY()>35)
				{
					this.setSize(new Dimension(this.getWidth(), newPoint.y-this.getY()));
				}
				break;
			case 7:
				if (this.getX()+this.getWidth()-newPoint.x>35 & newPoint.y-this.getY()>35)
				{
					this.setSize(new Dimension(this.getX()+this.getWidth()-newPoint.x, newPoint.y-this.getY()));
					this.setLocation(new Point(newPoint.x,this.getY()));
				}
				break;
			case 8:
				if (this.getX()+this.getWidth()-newPoint.x>35)
				{
					this.setSize(new Dimension(this.getX()+this.getWidth()-newPoint.x, this.getHeight()));
					this.setLocation(new Point(newPoint.x,this.getY()));
				}
				break;
			}			
		}
		else
		panel.setPosition(e.getComponent(),0);
	}
	
	public void mouseReleased(MouseEvent e)
	{
		TextLabel labelResized = (TextLabel) e.getComponent();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	Tpanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	if (resizeable)
    	{
    		Rectangle folBounds = new Rectangle(labelResized.getBounds());
    		sketchCenter.currentProject.modifyComponent
    			(labelResized.ID, SketchOperation.TYPE_BOUNDS, folBounds);
    	}
		resizeable = false;
		reType=0;
	}

	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) 
	{
		if (!focus)
		{
			this.setBorder(null);
		}
	}	
}