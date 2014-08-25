package data_center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

public class SketchComponent
{
	public static class Component
	{
		// attributes
		public int ID;
		public int primaryType;

		public Component(int theID, int theType)
		{
			ID = theID;
			primaryType = theType;
		}

		public void display()
		{
			System.out.println("ID: " + ID);
			System.out.println("Type: " + primaryType);
		}

	}

	public static class Label extends Component
	{
		// attributes
		public String text;
		public Point center;
		public int size;
		public Font font;
		public Color color;
		
		// primaryType already known in the class
		public Label(int theID, String text, Point pos, 
				int size, Font font, Color color)
		{
			super(theID, BbkType.Sketch.LABEL);
			this.text = text;
			this.center = pos;
			this.size = size;
			this.font = font;
			this.color = color;
		}

		public void display()
		{
			super.display();
			System.out.println("Text: " + text);
			System.out.println(center.toString());
		}
	}

    public static class BioBrick extends Component
    {
		// attributes
		public int secondaryType;
		public Point center;
		public Color color;
        public BbkOutline info = null;

		// primary type already known in the class
        public BioBrick(int theID, int secondaryType, Point center, Color color)
        {
        	super(theID, BbkType.Sketch.BIO_BRICK);
        	this.secondaryType = secondaryType;
			this.center = center;
			this.color = color;
        }

        public void display()
        {
			super.display();
			System.out.println("SecondaryType: " + secondaryType);
			System.out.println(center.toString());
			System.out.println("Color: " + color);
			if (info != null)
                info.display();
        }


    }

	public static class Protein extends Component
	{
		// attributes
		public int secondaryType;
		public Point center;
		public Color color;

		// primaryType already known in the class
		public Protein(int theID, int secondaryType, Point center, Color color)
			
		{
			super(theID, BbkType.Sketch.PROTEIN);
			this.secondaryType = secondaryType; 
			this.center = center;
			this.color = color;
		}

		public void display()
		{
			super.display();
			System.out.println("SecondaryType :" + secondaryType);
			System.out.println(center.toString());
			System.out.println("Color: " + color);
		}
	
	}

	public class BackBone extends Component
	{
		// attributes
		public Point leftPoint;
		public Point rightPoint;

		public BackBone(int theID, Point left, Point right)
		{
			super(theID, BbkType.Sketch.BACKBONE);
			this.leftPoint = left;
			this.rightPoint = right;
		}

		public void display()
		{
			super.display();
			System.out.println(leftPoint.toString());
			System.out.println(rightPoint.toString());
		}
	}

	public class Relation extends Component
	{
		public final static int PROMOTE = 1;
		public final static int SUPPRESS = 2;
		public final static int OTHER = 0;

		// attributes
		public int secondaryType;
		public ArrayList<Point> posList;
		public Color color;
		public int thickness;

		public Relation(int theID, int secondaryType, 
				ArrayList<Point> list, Color color, int thickness)
			
		{
			super(theID, BbkType.Sketch.RELATION);
			this.secondaryType = secondaryType;
			this.posList = list;
			this.color = color;
			this.thickness = thickness;
		}

		public void display()
		{
			super.display();
			System.out.println("SecondaryType: " + secondaryType);
			for (Point point : posList)
				System.out.println(point.toString());
			System.out.println("Color: " + color);
			System.out.println("Thickness: " + thickness);
		}
	}

	public class Vector extends Component
	{
		// attributes
		public int secondaryType;
		public Point center;
		public double scale;
		
		public Vector(int theID, int secondaryType, Point center, int scale)
		{
			super(theID, BbkType.Sketch.VECTOR);
			this.secondaryType = secondaryType;
			this.center = center;
			this.scale = scale;
		}

		public void display()
		{
			super.display();
			System.out.println("SecondaryType: " + secondaryType);
			System.out.println(center.toString());
			System.out.println("Scale: " + scale);
		}
	}
}
