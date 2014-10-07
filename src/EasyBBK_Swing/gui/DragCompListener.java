package EasyBBK_Swing.gui;

import EasyBBK_Swing.gui.JLabelWithID;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import data_center.SketchCenter;
import data_center.SketchComponent;
import data_center.SketchProject;

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
	BackBone closestBackbone = null;
	
	private BackBone previousBackbone=null;
	
	private BackBone previousBackbone=null;
	
	JLayeredPane panel;
	TPanel Tpanel;
	
	SketchCenter sketchCenter;
	
	public DragCompListener()
	{
		super();
	}
	
	public DragCompListener(JLayeredPane panel, TPanel Tpanel,SketchCenter sketchCenter)
	{
		super();
		//get panel
		this.panel = panel;
		this.Tpanel = Tpanel;
		this.sketchCenter = sketchCenter;
	}
	
	public void mousePressed(MouseEvent e)
	{
		if ((e.getComponent()).getName() != "TPanel" & (e.getComponent()).getName() != "LinePanel"
				& (e.getComponent()).getName() != "text")
		{			
			closestBackbone=null;
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
			
			if ((e.getComponent()).getName() != "recepter" & (e.getComponent()).getName() != "factor"
<<<<<<< HEAD
					& (e.getComponent()).getName() != "protein" & (e.getComponent()).getName() != "plasmid")
=======
					& (e.getComponent()).getName() != "protein" & (e.getComponent()).getName() != "plasmid"
					& (e.getComponent()).getName() != "virus" & (e.getComponent()).getName() != "ecoil")
>>>>>>> origin/master
			{
				//create a list of backbones and a list of components
				backboneList.clear();
				partList.clear();
				int length = (panel.getComponents()).length;
				for (int i = 0; i<length ; i++)
				{
					if (((panel.getComponents())[i]).getName() != "TPanel" & ((panel.getComponents())[i]).getName() != "linePanel"
							& ((panel.getComponents())[i]).getName() != "text" & 
							(((panel.getComponents())[i]).getName() != "recepter" & ((panel.getComponents())[i]).getName() != "factor"
<<<<<<< HEAD
							& ((panel.getComponents())[i]).getName() != "protein" & ((panel.getComponents())[i]).getName() != "plasmid"))
=======
							& ((panel.getComponents())[i]).getName() != "protein" & ((panel.getComponents())[i]).getName() != "plasmid"
							& (e.getComponent()).getName() != "virus" & (e.getComponent()).getName() != "ecoil"))
>>>>>>> origin/master
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
										((BackBone)e.getSource()).getWidth())> ((partList.get(i)).getX()
												+(partList.get(i)).getWidth()))
								//component is close to backbone in Y axis
								& (Math.abs(((BackBone)e.getSource()).getY()-(partList.get(i)).getY())==0))		
						{
							onBackbone.add(i);
						}
					}
				}
				else
				{
					int bCount = backboneList.size();
					System.out.println(bCount);
					previousBackbone=null;
					
					for (int i2=0; i2<bCount; i2++)
					{
						if (	//component is in the range of backbone in x axis
								((backboneList.get(i2)).getX()<(e.getComponent()).getX())
								& ((backboneList.get(i2)).getX()+
										((backboneList.get(i2)).getWidth())> ((e.getComponent()).getX()
										+(e.getComponent()).getWidth()))
								//component is close to backbone in Y axis
								& (Math.abs((backboneList.get(i2)).getY()-(e.getComponent()).getY())==0))		
						{
							previousBackbone=backboneList.get(i2);
							System.out.println("ok");
							System.out.println(i2);
						}
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
				if (((e.getComponent()).getName() != "recepter" & (e.getComponent()).getName() != "factor"
						& (e.getComponent()).getName() != "protein" & (e.getComponent()).getName() != "plasmid"
						& (e.getComponent()).getName() != "virus" & (e.getComponent()).getName() != "ecoil"))
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
										>(83+((JLabelWithID)e.getSource()).getX())) 
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
						closestBackbone=null;
					}
				}
				else
				{
					panel.setPosition(e.getComponent(),0);
					Point newPoint = SwingUtilities.convertPoint((JLabelWithID)e.getSource() , 
							e.getPoint(), ((JLabelWithID)e.getSource()).getParent());
					((JLabelWithID)e.getSource()).setLocation(
							((JLabelWithID)e.getSource()).getX()+(newPoint.x-point.x),
							((JLabelWithID)e.getSource()).getY()+(newPoint.y-point.y));
					point = newPoint;
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
		
		JLabelWithID compMoved = (JLabelWithID)e.getComponent();
		
		//Auto align
		if ((e.getComponent()).getName() != "TPanel" & (e.getComponent()).getName() != "LinePanel"
				& (e.getComponent()).getName() != "text" )
		{
<<<<<<< HEAD
			if (((e.getComponent()).getName() != "recepter" & 
					(e.getComponent()).getName() != "factor" & (e.getComponent()).getName() != "protein" 
					& (e.getComponent()).getName() != "plasmid"))
=======
			if (((e.getComponent()).getName() != "recepter" & (e.getComponent()).getName() != "factor" 
					& (e.getComponent()).getName() != "protein" & (e.getComponent()).getName() != "plasmid"
					& (e.getComponent()).getName() != "virus" & (e.getComponent()).getName() != "ecoil"))
>>>>>>> origin/master
			{
				if ((e.getComponent()).getName() != "backbone")
				{
					if (near)
					{
						Point location = new Point(((JLabelWithID)(e.getSource())).getX(),
								(closestBackbone.getLocation()).y);
						((JLabelWithID)(e.getSource())).setLocation(location);
						
						//search components on the backbone
						int pCount = partList.size();
						onBackbone.clear();
						
						for (int i=0; i<pCount; i++)
						{
							if (	//component is in the range of backbone in x axis
									(closestBackbone.getX()<(partList.get(i)).getX())
									& ((closestBackbone.getX()+
											closestBackbone.getWidth())> (partList.get(i)).getX())
									//component is close to backbone in Y axis
									& (Math.abs(closestBackbone.getY()-(partList.get(i)).getY())==0))		
							{
								onBackbone.add(i);
							}
						}
						
						//confirm the serial number on backbone
						int compNum_OnBackbone = onBackbone.size();
						int order=0;
						int distance=e.getComponent().getX()- closestBackbone.getX();
						for (int i2=0; i2<compNum_OnBackbone; i2++)
						{
							if ((partList.get(onBackbone.get(i2)).getX()-closestBackbone.getX())<distance)
							{
								order=order+1;
							}					
						}
						
						sketchCenter.currentProject.onAbsorb
							(closestBackbone.ID, compMoved.ID, order);
					}
					else
					{	// the previousBackbone represents the backbone once absorbed, if it is not a 
						// 	null, it means a desorb event
						if (previousBackbone != null)
							sketchCenter.currentProject.onDesorb
								(previousBackbone.ID, compMoved.ID);
					}
				}
			}
			// register the move action
			Rectangle folBounds = new Rectangle(compMoved.getBounds());
			sketchCenter.currentProject.modifyComponent
				(compMoved.ID, SketchProject.Operation.TYPE_BOUNDS, folBounds);
			
		}		
	}
      
    public void mouseEntered(MouseEvent e){}
      
    public void mouseExited(MouseEvent e){}
      
    public void mouseClicked(MouseEvent e){}
      
    public void mouseMoved(MouseEvent e){}
}
