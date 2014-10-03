package EasyBBK_Swing.gui;

import EasyBBK_Swing.gui.JLabelWithID;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 * Imply movement in board
 */
class DragCompListener implements MouseInputListener
{
	Point point = new Point(0,0);
	boolean near =false;
	
	ArrayList<BackBone> backboneList = new ArrayList<BackBone>();
	ArrayList<JLabelWithID> partList = new ArrayList<JLabelWithID>();
	ArrayList<Integer> nearBackbone = new ArrayList<Integer>();
	ArrayList<Integer> onBackbone = new ArrayList<Integer>();
	BackBone closestBackbone = new BackBone();
	
	JLayeredPane panel;
	TPanel Tpanel;
	
	public DragCompListener()
	{
		super();
	}
	
	public DragCompListener(JLayeredPane panel, TPanel Tpanel)
	{
		super();
		//get panel
		this.panel = panel;
		this.Tpanel = Tpanel;
	}
	
	public void mousePressed(MouseEvent e)
	{
		if ((e.getComponent()).getName() != "TPanel" & (e.getComponent()).getName() != "LinePanel"
				& (e.getComponent()).getName() != "text")
		{
			//get the location
			if ((e.getComponent()).getName()=="backbone")
			{
				point = SwingUtilities.convertPoint((BackBone)e.getSource() , e.getPoint(), 
						((BackBone)e.getSource()).getParent());
			}
			else
			{
				point = SwingUtilities.convertPoint((JLabelWithID)e.getSource() , e.getPoint(), 
						((JLabelWithID)e.getSource()).getParent());
			}

			//create a list of backbones and a list of components
			int length = (panel.getComponents()).length;
			for (int i = 0; i<length ; i++)
			{
				if (((panel.getComponents())[i]).getName() != "TPanel" & ((panel.getComponents())[i]).getName() != "linePanel"
						& ((panel.getComponents())[i]).getName() != "text")
				{
					if (((panel.getComponents())[i]).getName()=="backbone")
					{
						backboneList.add((BackBone)((panel.getComponents())[i]));
					}
					else
					{
						partList.add((JLabelWithID)((panel.getComponents())[i]));
					}
				}
			}
			
			//create a list of components which are on the backbone
			if ((e.getComponent()).getName()=="backbone")
			{
				//search components on the backbone
				int pCount = partList.size();
				onBackbone.clear();
				
				for (int i=0; i<pCount; i++)
				{
					if (	//component is in the range of backbone in x axis
							(((BackBone)e.getSource()).getX()<(partList.get(i)).getX())
							& ((((BackBone)e.getSource()).getX()+
									((BackBone)e.getSource()).getWidth())> (partList.get(i)).getX())
							//component is close to backbone in Y axis
							& (Math.abs(((BackBone)e.getSource()).getY()-(partList.get(i)).getY())==0))		
					{
						onBackbone.add(i);
					}
				}
			}
		}
	}
	
	public void mouseDragged(MouseEvent e)
	{
		//Components movement
		if ((e.getComponent()).getName() != "TPanel" & (e.getComponent()).getName() != "LinePanel"
				& (e.getComponent()).getName() != "text")
		{
			if ((e.getComponent()).getName() != "backbone")
			{
				//solve the problem of overlapping
				panel.setPosition(e.getComponent(),0);
				Tpanel.setImage(null);
				Tpanel.repaint();
				//get new location
				Point newPoint = SwingUtilities.convertPoint((JLabelWithID)e.getSource() , 
						e.getPoint(), ((JLabelWithID)e.getSource()).getParent());
				((JLabelWithID)e.getSource()).setLocation(
						((JLabelWithID)e.getSource()).getX()+(newPoint.x-point.x),
						((JLabelWithID)e.getSource()).getY()+(newPoint.y-point.y));
				point = newPoint;
				
				//search closest backbone
				int bCount = backboneList.size();
				nearBackbone.clear();
				
				for (int i=0; i<bCount; i++)
				{
					if (	//component is in the range of backbone in x axis
							(((JLabelWithID)e.getSource()).getX()>((BackBone)backboneList.get(i)).getX())
							& ((((BackBone)backboneList.get(i)).getWidth()
									+((BackBone)backboneList.get(i)).getX())
									>(100+((JLabelWithID)e.getSource()).getX())) 
					//component is close to backbone in Y axis
					& (Math.abs((((JLabelWithID)e.getSource()).getY()+25)-
							(((BackBone)backboneList.get(i)).getY()+25))<70))							
					{
						nearBackbone.add(i);
					}
				}
				
				//confirm the closest backbone
				int numNear = nearBackbone.size();
				if (numNear>0)
				{
					near = true;
					if (numNear>1)
					{
						int closestIndex = 0;
						int distance = Math.abs((backboneList.get(nearBackbone.get(0)).getY()+25)
								-((e.getComponent()).getY()+25));
						for (int i=0; i<numNear; i++)
						{
							if (distance>Math.abs((backboneList.get(nearBackbone.get(0)).getY()+25)
									-((e.getComponent()).getY()+25)))
							{
								distance=Math.abs((backboneList.get(nearBackbone.get(0)).getY()+25)
										-((e.getComponent()).getY()+25));
								closestIndex=i;
							}					
						}
						closestBackbone = backboneList.get(nearBackbone.get(closestIndex));
					}
					else
					{
						closestBackbone = backboneList.get(nearBackbone.get(0));
					}
					
					Point location = new Point(((JLabelWithID)(e.getSource())).getX(),
							closestBackbone.getY());

					//Paint transparent component
					Component comp= e.getComponent();
					BufferedImage im = new BufferedImage(comp.getWidth(),
							comp.getHeight(),BufferedImage.TYPE_INT_ARGB);
					Graphics g= im.getGraphics();
					comp.paint(g);
					Tpanel.setImage(im);
					Tpanel.setPoint(location);
					Tpanel.repaint();
				}
				else
				{
					near=false;
				}
			}
			
			//Backbone movement
			else
			{
				if (!(((BackBone)e.getSource()).getResize()))
				{
					//get new location
					Point newPoint = SwingUtilities.convertPoint((BackBone)e.getSource() , 
							e.getPoint(), ((BackBone)e.getSource()).getParent());
					((BackBone)e.getSource()).setLocation(
							((BackBone)e.getSource()).getX()+(newPoint.x-point.x),
							((BackBone)e.getSource()).getY()+(newPoint.y-point.y));
					
					//Move together
					int numOn = onBackbone.size();
					if (numOn>0)
					{
						for (int i=0; i<numOn; i++)
						{
							(partList.get(onBackbone.get(i))).setLocation(
									(partList.get(onBackbone.get(i))).getX()+(newPoint.x-point.x),
									(partList.get(onBackbone.get(i))).getY()+(newPoint.y-point.y));
						}
					}
					point = newPoint;
				}
			}
		}	
	}
	
	public void mouseReleased(MouseEvent e)
	{
		Tpanel.setImage(null);
		Tpanel.repaint();
		backboneList.clear();
		partList.clear();
		
		//Auto align
		if ((e.getComponent()).getName() != "TPanel" & (e.getComponent()).getName() != "LinePanel"
				& (e.getComponent()).getName() != "text")
		{
			if ((e.getComponent()).getName() != "backbone")
			{
				if (near)
				{
					Point location = new Point(((JLabelWithID)(e.getSource())).getX(),
							(closestBackbone.getLocation()).y);
					((JLabelWithID)(e.getSource())).setLocation(location);
				}
			}
		}		
	}
      
    public void mouseEntered(MouseEvent e){}
      
    public void mouseExited(MouseEvent e){}
      
    public void mouseClicked(MouseEvent e){}
      
    public void mouseMoved(MouseEvent e){}
}
