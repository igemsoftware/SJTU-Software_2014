package EasyBBK_Swing.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.JLayeredPane;


/**
 * Draw line in this panel
 */
class LinePanel extends JLayeredPane
{
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Point> lineList;
	public Pen p =new Pen();
	
	public final static int LINE_WIHT_STOP_END = -1;
	public final static int LINE_WITH_EMPTY_ARROW = 0;
	public final static int LINE_WITH_FULL_ARROW = 1;
	
	public int lineType = 0;
	public int left = 10000;
	public int right = 0;
	public int top = 10000;
	public int bottom = 0;
	public int width;
	public int height;
	public Point topLeft = new Point();
	public Point bottomRight = new Point();
	public float stroke=3.0f;
	public Color color = Color.black;
	
	/**
	 * LinPanel constructor
	 * @param p Pen type
	 */
	public LinePanel(Pen p) 
	{
		lineList= new ArrayList<Point>();
		super.repaint();
		this.p=p;
		setOpaque(false);
		this.setName("linePanel");
		if (p!=null)
		{
			this.lineType = p.getType();
		}
        repaint();
    }  
	
	/**
	 * setPen
	 * @param p
	 */
	public void setPen(Pen p)
	{
		this.p = p;
	}
	
	public void setPenValue(int value)
	{
		p=new Pen();
		p.setType(value);
	}
	
	/**
	 * paintComponent
	 */
    public void paintComponent(Graphics g)
    {
    	super.repaint();
    	if (p != null)
    	{
    		lineType = p.getType();    		
    		GraphicsCurve graCurve= new GraphicsCurve((Graphics2D)g);
    		graCurve.myGraphics.setPaint(color);
    		graCurve.myGraphics.setStroke(new BasicStroke(stroke));
    		graCurve.myGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
    		if (lineList.size()==0) return;
    		if (lineType == -1)
    		{
    			graCurve.DrawBezierWithLine(lineList);
    		}
    		else if (lineType == 0)
    		{
    			graCurve.DrawBezierWithArrow(lineList, false);
    		}
    		else
    		{
    			graCurve.DrawBezierWithArrow(lineList,true);
    		}
    		left = graCurve.getLeft();
    		right = graCurve.getRight();
    		top = graCurve.getTop();
    		bottom = graCurve.getBottom();
    		topLeft = graCurve.getTopLeft();
    		bottomRight = graCurve.getBottomRight();
    		width = graCurve.getWidth();
    		height = graCurve.getHeight();
    	}   	
    } 
    
    /**
     * Get lineList
     * @param lineList
     */
    public void drawGivenLine(ArrayList<Point> lineList)
    {
    	this.lineList=lineList;
    }
    
    /**
     * Add Point to lineList
     * @param point
     */
    public void addPoint(Point point)
    {
    	lineList.add(point);
    }
    
    /**
     * Get border
     * @return border
     */
    public int[] getLineBorder()
    {
    	return (new int[]{left,right,top,bottom});
    }
    
    /**
     * Get lineList
     * @return lineList
     */
    public ArrayList<Point> getPointArrayList()
    {
    	return lineList;
    }
    
    /**
     * endline function
     */
    public void endLine()
    {
    	p=null;
    	lineList.clear();
    }
    
    /**
     * set line color
     * @param color
     */
    public void setColor(Color color)
    {
    	this.color = color;
    }
    
    /**
     * set line stroke
     * @param stroke
     */
    public void setStoke(float stroke)
    {
    	this.stroke = stroke;
    }    
    
    /**
     * Draw Line
     */
    class GraphicsCurve 
    {
    	private int left = 10000;
    	private int right = 0;
    	private int top = 10000;
    	private int bottom = 0;
    	public Graphics2D myGraphics;
    	public QuadCurve2D myCurve;
    	public GeneralPath polyline;

    	public GraphicsCurve() {} 
    	
    	public GraphicsCurve(Graphics2D graphics) 
    	{        
    		this.myGraphics=graphics; 
    		(this.myGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
    	} 
    	   	
    	public Point getTopLeft()
    	{
    		return new Point(left,top);
    	}
    	
    	public Point getBottomRight()
    	{
    		return new Point(right,bottom);
    	}
    	
    	public int getWidth()
    	{
    		return right - left;
    	}
    	
    	public int getHeight()
    	{
    		return bottom - top;
    	}
    	
    	public int getLeft()
    	{
    		return left;
    	}
    	
    	public int getRight()
    	{
    		return right;
    	}
    	
    	public int getTop()
    	{
    		return top;
    	}
    	
    	public int getBottom()
    	{
    		return bottom;
    	}
    	
    	/**
    	 * Calculate parameters of Bezier curve. 
    	 * @param i
    	 * @param n
    	 * @param t
    	 * @return parameter
    	 */
    	private double calcBezierParam(int i,int n,double t)
    	{
    		double tt = Math.pow(t, i) * Math.pow(1-t, n-i);
    		if (i==0 || i==n) return tt;
    		double nCi = 1.0;
    		for (int j=1;j<=i;j++)
    		{
    			nCi = nCi * (n-j+1);
    			nCi = nCi / j;
    		}    		
    		return nCi * tt;
    	}
    	
    	/**
    	 * Draw orthogonal end line from (x1,y1) to (x2,y2)
    	 * @param x1 
    	 * @param y1
    	 * @param x2
    	 * @param y2
    	 * @param length
    	 */
    	private void DrawEndLine(int x1, int y1, int x2, int y2, int length)
    	{
			double x3,y3,x4,y4;
			double a = Math.sqrt(length/(Math.pow(x1-x2, 2)/Math.pow(y1-y2, 2)+1));
			x3 = a + x2;
			x4 = x2 - a;
			y3 = -(x1-x2)/(y1-y2)*(x3-x2)+y2;
			y4 = -(x1-x2)/(y1-y2)*(x4-x2)+y2;
			myGraphics.drawLine((int)x3, (int)y3, (int)x4, (int)y4);
			if ((int)x3 < left) left = (int)x3;
			if ((int)x4 < left) left = (int)x4;
			if ((int)x3 > right) right = (int)x3;
			if ((int)x4 > right) right = (int)x4;
			if ((int)y3 < top) top = (int)y3;
			if ((int)y4 < top) top = (int)y4;
			if ((int)y3 > bottom) bottom = (int)y3;
			if ((int)y4 > bottom) bottom = (int)y4; 
    	}
    	
    	/**
    	 * Draw end arrow according to last two point ((sx,sy) and (ex,ey)) of the line.
    	 * @param sx
    	 * @param sy
    	 * @param ex
    	 * @param ey
    	 * @param fill true for solid.
    	 */
    	private void DrawEndArrow(int sx, int sy, int ex, int ey, boolean fill)  
        {  
      
            double H = 10 ; //height of the arrow
            double L = 7.07; 
            int x3 = 0;  
            int y3 = 0;  
            int x4 = 0;  
            int y4 = 0;  
            double awrad = Math.atan(L / H); //angle of arrow
            double arraow_len = Math.sqrt(L * L + H * H); //length of arrow
            double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);  
            double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);  
            double x_3 = ex - arrXY_1[0];
            double y_3 = ey - arrXY_1[1];  
            double x_4 = ex - arrXY_2[0];
            double y_4 = ey - arrXY_2[1];  
      
            Double X3 = new Double(x_3);  
            x3 = X3.intValue();  
            Double Y3 = new Double(y_3);  
            y3 = Y3.intValue();  
            Double X4 = new Double(x_4);  
            x4 = X4.intValue();  
            Double Y4 = new Double(y_4);  
            y4 = Y4.intValue();  
            //Draw line
            GeneralPath triangle = new GeneralPath();  
            triangle.moveTo(x3, y3);  
            triangle.lineTo(ex, ey);  
            triangle.lineTo(x4, y4);
            
            //true for solid arrow
            if (fill)
            { 
            	triangle.closePath();
            	myGraphics.draw(triangle);
            	myGraphics.fill(triangle);  
            }
            else
            {             
            	myGraphics.draw(triangle);
            }
            if (x3 < left) left = x3;
			if (x4 < left) left = x4;
			if (x3 > right) right = x3;
			if (x4 > right) right = x4;
			if (y3 < top) top = y3;
			if (y4 < top) top = y4;
			if (y3 > bottom) bottom = y3;
			if (y4 > bottom) bottom = y4; 
        }  
      
        //Calculate the angle
        private double[] rotateVec(int px, int py, double ang,  
                boolean isChLen, double newLen) {  
      
            double mathstr[] = new double[2];  
            double vx = px * Math.cos(ang) - py * Math.sin(ang);  
            double vy = px * Math.sin(ang) + py * Math.cos(ang);  
            if (isChLen) {  
                double d = Math.sqrt(vx * vx + vy * vy);  
                vx = vx / d * newLen;  
                vy = vy / d * newLen;  
                mathstr[0] = vx;  
                mathstr[1] = vy;  
            }  
            return mathstr;  
        }  
        
        /**
         * Draw Bezier curve end with arrow.
         * @param List
         * @param fill true for solid arrow
         */
        public void DrawBezierWithArrow(ArrayList<Point> List, boolean fill)
    	{
    		double t;
    		int[] x, y, xB,yB;
    		int n=List.size();
    		
    		x=new int[n];
    		y=new int[n];
    		
    		xB = new int[1000];
    		yB = new int[1000];
    		for (int i=0;i<n;i++)
    		{
    			x[i]=(List.get(i)).x;
    			y[i]=(List.get(i)).y;
    		}
    		if (n<=1)
    		{
    			myGraphics.drawOval(x[0],y[0],(int)stroke,(int)stroke);
    			left = x[0] - 1;
    			right = x[0] + 1;
    			top = y[0] - 1;
    			bottom = y[0] + 1;
    		}
    		else if (n==2)
    		{
    			myGraphics.drawLine(x[0], y[0], x[1], y[1]);
    			this.DrawEndArrow((int)x[0], (int)y[0], (int)x[1], (int)y[1], fill);
    			left = Math.min(x[0], x[1]);
    			right = Math.max(x[0], x[1]);
    			top = Math.min(y[0], y[1]);
    			bottom = Math.max(y[0], y[1]);
    		}
    		else
    		{
    			int idx=0;
    			for (t=0;t<=1;t+=0.01,idx++)
    			{
    				double xtmp=0,ytmp=0;
    				for (int i=0;i<n;i++)
    				{
    					double BezierParam = calcBezierParam(i,n-1,t);
    					xtmp += BezierParam*x[i];
    					ytmp += BezierParam*y[i];
    				}
    				xB[idx] = (int) xtmp;
    				yB[idx] = (int) ytmp;
    				if (xB[idx] < left) left = xB[idx];
    				if (xB[idx] > right) right = xB[idx];
    				if (yB[idx] < top) top = yB[idx];
    				if (yB[idx] > bottom) bottom = yB[idx]; 
    			}
    			xB[idx-1] = (int) x[n-1];
				yB[idx-1] = (int) y[n-1];
				if (xB[idx-1] < left) left = xB[idx-1];
				if (xB[idx-1] > right) right = xB[idx-1];
				if (yB[idx-1] < top) top = yB[idx-1];
				if (yB[idx-1] > bottom) bottom = yB[idx-1]; 
    			for (int i=1;i<idx;i++)
    			{
    				myGraphics.drawLine(xB[i-1], yB[i-1], xB[i], yB[i]);
    			}
    			this.DrawEndArrow((int)xB[idx-2], (int)yB[idx-2], (int)xB[idx-1], (int)yB[idx-1],fill);
    		}
    		
    	}
        
        /**
         * Draw Bezier curve end with line.
         * @param List
         */
        public void DrawBezierWithLine(ArrayList<Point> List)
    	{
    		double t;
    		int[] x, y, xB,yB;
    		int n=List.size();
    		
    		x=new int[n];
    		y=new int[n];
    		
    		xB = new int[1000];
    		yB = new int[1000];
    		for (int i=0;i<n;i++)
    		{
    			x[i]=(List.get(i)).x;
    			y[i]=(List.get(i)).y;
    		}
    		if (n<=1)
    		{
    			myGraphics.drawOval(x[0],y[0],(int)stroke,(int)stroke);
    			left = x[0] - 1;
    			right = x[0] + 1;
    			top = y[0] - 1;
    			bottom = y[0] + 1;
    		}
    		else if (n==2)
    		{
    			myGraphics.drawLine(x[0], y[0], x[1], y[1]);
    			this.DrawEndLine((int)x[0], (int)y[0], (int)x[1], (int)y[1],100);
    			left = Math.min(x[0], x[1]);
    			right = Math.max(x[0], x[1]);
    			top = Math.min(y[0], y[1]);
    			bottom = Math.max(y[0], y[1]);
    		}
    		else
    		{
    			int idx=0;
    			for (t=0;t<=1;t+=0.01,idx++)
    			{
    				double xtmp=0,ytmp=0;
    				for (int i=0;i<n;i++)
    				{
    					double BezierParam = calcBezierParam(i,n-1,t);
    					xtmp += BezierParam*x[i];
    					ytmp += BezierParam*y[i];
    				}
    				xB[idx] = (int) xtmp;
    				yB[idx] = (int) ytmp;
    				if (xB[idx] < left) left = xB[idx];
    				if (xB[idx] > right) right = xB[idx];
    				if (yB[idx] < top) top = yB[idx];
    				if (yB[idx] > bottom) bottom = yB[idx];
    			}
    			xB[idx-1] = (int) x[n-1];
				yB[idx-1] = (int) y[n-1];
				if (xB[idx-1] < left) left = xB[idx-1];
				if (xB[idx-1] > right) right = xB[idx-1];
				if (yB[idx-1] < top) top = yB[idx-1];
				if (yB[idx-1] > bottom) bottom = yB[idx-1]; 
    			for (int i=1;i<idx;i++)
    			{
    				myGraphics.drawLine(xB[i-1], yB[i-1], xB[i], yB[i]);
    			}
    			this.DrawEndLine((int)xB[idx-2], (int)yB[idx-2], (int)xB[idx-1], (int)yB[idx-1], 100);
    		}
    		
    	}
    }
}
