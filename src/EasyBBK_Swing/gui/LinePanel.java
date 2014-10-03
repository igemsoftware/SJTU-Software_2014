package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Draw line in this panel
 */
class LinePanel extends JPanel
{
	public ArrayList<ArrayList<Point>> lineList= new ArrayList<ArrayList<Point>>();
	private Pen p;
	
	boolean startNew = true;;
	int numLine = 0;
	
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
    	super.repaint();
    	GraphicsCurve graCurve= new GraphicsCurve((Graphics2D)g);
    	graCurve.myGraphics.setPaint(Color.red);
    	graCurve.myGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
    	int n = lineList.size();
    	for (int i=0; i<n; i++)
    	{
    		graCurve.DrawCurves(lineList.get(i));
    	}
    } 
    
    public void addPoint(Point point)
    {
    	if (startNew)
    	{
    		lineList.add(new ArrayList<Point>());
    	}
    	startNew=false;
    	(lineList.get(numLine)).add(point);
    }
    
    public void addLine()
    {
    	numLine = numLine+1;
    	startNew = true;
    }
    
    public void setColor()
    {
    	
    }

    //Draw Line
    class GraphicsCurve 
    {
    	public Graphics2D myGraphics; 

    	public GraphicsCurve() {} 
    	
    	public GraphicsCurve(Graphics2D graphics) 
    	{        
    		this.myGraphics=graphics; 
    	} 
      
    	public void DrawCurves(ArrayList<Point> List) 
    	{ 
    		int[] x, y; 
    		double[] a, b, c; 
    		double[] px, py, qx, qy, tt; 
    		double[] dx, dy; 
    		int px1,py1,px2,py2;
    		int n=List.size();
    		x=new int[n];
    		y=new int[n];
    		for (int i=0;i<n;i++)
    		{
    			x[i]=(List.get(i)).x;
    			y[i]=(List.get(i)).y;
    		}
    		px1=x[0];
    		py1=y[0];	 
    		a=new double[n];
    		b=new double[n]; 
    		c=new double[n]; 
    		px=new double[n]; 
    		py=new double[n]; 
    		qx=new double[n]; 
    		qy=new double[n]; 
    		tt=new double[n]; 
    		dx=new double[n]; 
    		dy=new double[n]; 
    		int i, t, es; 
    		double bx3, bx4, by3, by4, cx, cy; 
    		by3 = 0; 
    		es = 3; 
    		px[0] = 1; 
    		py[0] = 1; 
    		px[n-1] = 1; 
    		py[n-1] = 1; 
    		if (n>1) 
    		{ 
    			for (i = 1;i<n;i++) 
    			{
    				tt[i] = Math.sqrt((x[i] - x[i - 1]) * (x[i] - x[i - 1]) + (y[i] - y[i - 1]) * (y[i] - y[i - 1])); 
    			}
    			switch(n)       
    			{
    				case 2: 
    					break; 
    				case 3: 
    					for (i = 1;i<n - 1;i++) 
    					{
    						a[i] = 2 * (tt[i] + tt[i + 1]); 
    						b[i] = tt[i + 1]; 
    						c[i] = tt[i]; 
    						dx[i] = 3 * (tt[i] * (x[i + 1] - x[i]) / tt[i + 1] + tt[i + 1] * (x[i] - x[i - 1]) / tt[i]); 
    						dy[i] = 3 * (tt[i] * (y[i + 1] - y[i]) / tt[i + 1] + tt[i + 1] * (y[i] - y[i - 1]) / tt[i]); 
    					} 
    					dx[1] = dx[1] - tt[2] * px[0]; 
    					dx[n - 2] = dx[n - 2] - tt[n - 2] * px[n-1]; 
    					dy[1] = dy[1] - tt[2] * py[0]; 
    					dy[n - 2] = dy[n - 2] - tt[n - 2] * py[n-1]; 
    					px[1] = dx[1] / a[1]; 
    					py[1] = dy[1] / a[1]; 
    					break; 
    				default: 
    					for (i = 1;i<n - 1;i++) 
    					{
    						a[i] = 2 * (tt[i] + tt[i + 1]); 
    						b[i] = tt[i + 1]; 
    						c[i] = tt[i]; 
    						dx[i] = 3 * (tt[i] * (x[i + 1] - x[i]) / tt[i + 1] + tt[i + 1] * (x[i] - x[i - 1]) / tt[i]); 
    						dy[i] = 3 * (tt[i] * (y[i + 1] - y[i]) / tt[i + 1] + tt[i + 1] * (y[i] - y[i - 1]) / tt[i]); 
    					} 
    					dx[1] = dx[1] - tt[2] * px[0]; 
    					dx[n - 2] = dx[n - 2] - tt[n - 2] * px[n-1]; 
    					dy[1] = dy[1] - tt[2] * py[0]; 
    					dy[n - 2] = dy[n - 2] - tt[n - 2] * py[n-1]; 
    					c[1] = c[1]/ a[1]; 
    					for (i = 2 ;i< n - 1;i++) 
    					{ 
    						a[i] = a[i] - b[i] * c[i - 1]; 
    						c[i] = c[i] / a[i]; 
    					} 
    					qx[1] = dx[1] / a[1]; 
    					qy[1] = dy[1] / a[1]; 
    					for (i = 2 ;i< n - 1;i++) 
    					{ 
    						qx[i] = (dx[i] - b[i] * qx[i - 1]) / a[i]; 
    						qy[i] = (dy[i] - b[i] * qy[i - 1]) / a[i]; 
    					} 
    					px[n - 2] = qx[n - 2]; 
    					py[n - 2] = qy[n - 2]; 
    					for (i = n - 3;i>=1;i--) 
    					{ 
    						px[i] = qx[i] - c[i] * px[i + 1]; 
    						py[i] = qy[i] - c[i] * py[i + 1]; 
    					} 
    					break; 
    			} 
    			for (i = 0 ;i< n - 1;i++) 
    			{ 
    				bx3 = (3 * (x[i + 1] - x[i]) / tt[i + 1] - 2 * px[i] - px[i + 1]) / tt[i + 1]; 
    				bx4 = ((2 *(x[i] - x[i + 1]) / tt[i + 1] + px[i] + px[i + 1]) / tt[i + 1]) / tt[i + 1]; 
    				by3 = (3* (y[i + 1] - y[i]) / tt[i + 1] - 2 * py[i] - py[i + 1]) / tt[i + 1]; 
    				by4 = ((2 *(y[i] - y[i + 1]) / tt[i + 1] + py[i] + py[i + 1]) / tt[i + 1]) / tt[i + 1]; 
    				t = 0; 
    				while (t < tt[i + 1]) 
    				{ 
    					t = t + es; 
    					cx = x[i] + (px[i] + (bx3 + bx4 * t) * t) * t; 
    					cy = y[i] + (py[i] + (by3 + by4 * t) * t) * t; 
    					px2 = (int)cx; 
    					py2 = (int)cy; 
    					myGraphics.drawLine(px1,py1,px2,py2); 
    					px1 = px2; 
    					py1 = py2; 
    				} 
    			} 
    		}
    		
    		//show point
    		else
    		{
    			myGraphics.drawOval(x[0],y[0],1,1);
    		}
    	} 
    } 

}
