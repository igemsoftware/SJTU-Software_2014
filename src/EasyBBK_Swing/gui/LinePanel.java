package EasyBBK_Swing.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * Draw line in this panel
 */
class LinePanel extends JLayeredPane
{
	public ArrayList<Point> lineList;
	private Pen p;
	
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
	public float stroke=5.0f;
	public Color color = Color.blue;
	
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
  
    public void paintComponent(Graphics g)
    {
    	super.repaint();
		GraphicsCurve graCurve= new GraphicsCurve((Graphics2D)g);
		graCurve.myGraphics.setPaint(color);
		graCurve.myGraphics.setStroke(new BasicStroke(stroke));
		graCurve.myGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		//graCurve.DrawCurves(lineList.get(i));
		//graCurve.DrawCurvesByCurve(lineList.get(i));
		//graCurve.DrawPolyLine(lineList);
		//graCurve.DrawBezier(lineList.get(i));
		//System.out.println(" print"+lineList.size());
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
//		//SwingUtilities.convertPoint(this.getComponentAt(topLeft), 0, 0, this.getParent());
//		//Rectangle rect = new Rectangle(left,right,width,height);
//		//this.scrollRectToVisible(rect);
//		this.setSize(width,height);
//		//this.setBounds(left, top, width, height);
//		BufferedImage bufferimage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
//		bufferimage.
//		//this.setLocation(left, top);
//		//this.repaint();
//		System.out.println(this.getSize()+" "+this.getLocation());
//		System.out.println(left+" "+right+" "+top+" "+bottom);
    } 
    
    //获得点的list
    public void drawGivenLine(ArrayList<Point> lineList)
    {
    	this.lineList=lineList;
    	System.out.println(this.lineList.toString());
    }
    
    public void addPoint(Point point)
    {
    	System.out.println(lineList.size());
    	lineList.add(point);
    	System.out.println(lineList.size()+" "+point);
    }
    
    public int[] getLineBorder()
    {
    	return (new int[]{left,right,top,bottom});
    }
    
    public ArrayList<Point> getPointArrayList()
    {
    	return lineList;
    }
    
    public void endLine()
    {
    	System.out.println(left+" "+right+" "+top+" "+bottom);
    	lineList.clear();
    }
    
    public void setColor(Color color)
    {
    	this.color = color;
    }
    
    public void setStoke(float stroke)
    {
    	this.stroke = stroke;
    }    
    
    //Draw Line
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
    	
    	//贝塞尔曲线参数计算
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
    	
    	//
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
    	
    	private void DrawEndArrow(int sx, int sy, int ex, int ey, boolean fill)  
        {  
      
            double H = 10 ; // 箭头高度  
            double L = 7.07; // 底边的一半  
            int x3 = 0;  
            int y3 = 0;  
            int x4 = 0;  
            int y4 = 0;  
            double awrad = Math.atan(L / H); // 箭头角度  
            double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度  
            double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);  
            double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);  
            double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点  
            double y_3 = ey - arrXY_1[1];  
            double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点  
            double y_4 = ey - arrXY_2[1];  
      
            Double X3 = new Double(x_3);  
            x3 = X3.intValue();  
            Double Y3 = new Double(y_3);  
            y3 = Y3.intValue();  
            Double X4 = new Double(x_4);  
            x4 = X4.intValue();  
            Double Y4 = new Double(y_4);  
            y4 = Y4.intValue();  
            // 画线    
            GeneralPath triangle = new GeneralPath();  
            triangle.moveTo(x3, y3);  
            triangle.lineTo(ex, ey);  
            triangle.lineTo(x4, y4);
            
            //实心箭头  
            if (fill)
            { 
            	triangle.closePath();
            	myGraphics.draw(triangle);
            	myGraphics.fill(triangle);  
            }
            //非实心箭头  
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
      
        // 计算  
        private double[] rotateVec(int px, int py, double ang,  
                boolean isChLen, double newLen) {  
      
            double mathstr[] = new double[2];  
            // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度  
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
    			for (t=0;t<=1;t+=0.002,idx++)
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
    			//绘制箭头
    			this.DrawEndArrow((int)xB[idx-2], (int)yB[idx-2], (int)xB[idx-1], (int)yB[idx-1],fill);
    		}
    		
    	}
        
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
    			myGraphics.drawOval(x[0],y[0],(int)stroke,(int)stroke);
    		else if (n==2)
    		{
    			myGraphics.drawLine(x[0], y[0], x[1], y[1]);
    			this.DrawEndLine((int)x[0], (int)y[0], (int)x[1], (int)y[1],100);
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
    			//绘制垂直横线
    			this.DrawEndLine((int)xB[idx-2], (int)yB[idx-2], (int)xB[idx-1], (int)yB[idx-1], 100);
    		}
    		
    	}
    }
}
