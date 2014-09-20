package data_center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

public class SketchComponent
{
	public abstract static class Component
	{
		public int ID;
		public int primaryType;

		public Component(int theID, int theType)
		{
			ID = theID;
			primaryType = theType;
		}
		
		/** 
		 * This function is used to be inherited to convert Component
		 * object into its subclasses, but failed...
		 */
		public abstract Component toSubclass();
		
		public Label toLabel()
		{	return (Label) this;	}
		
		public BioBrick toBioBrick()
		{	return (BioBrick) this;	}
		
		public Protein toProtein()
		{	return (Protein) this;	}
		
		public BackBone toBackBone()
		{	return (BackBone) this;	}
		
		public Relation toRelation()
		{	return (Relation) this;	}
		
		public BioVector toBioVictor()
		{	return (BioVector) this;	}
		
		public void display()
		{
			System.out.println("ID: " + ID);
			System.out.println("Type: " + primaryType);
		}

	}

	public static class Label extends Component
	{
		public String text;
		public Point center;
		public Font font;
		public Color color;
		
		// primaryType already known in the class
		public Label(int theID, String text, Point pos, 
				Font font, Color color)
		{
			super(theID, BbkType.Sketch.LABEL);
			this.text = text;
			this.center = pos;
			this.font = font;
			this.color = color;
		}
		
		@Override
		public Label toSubclass()
		{	
			return this;
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
		public int secondaryType;
		public Point center;
		public Color color;
        public String bbkName = null;

		// primary type already known in the class
        public BioBrick(int theID, int secondaryType, Point center, Color color)
        {
        	super(theID, BbkType.Sketch.BIO_BRICK);
        	this.secondaryType = secondaryType;
			this.center = center;
			this.color = color;
        }

        @Override
		public BioBrick toSubclass()
		{	
			return this;
		}
        
        public void display()
        {
			super.display();
			System.out.println("SecondaryType: " + secondaryType);
			System.out.println(center.toString());
			System.out.println("Color: " + color);
			if (bbkName != null)
				System.out.println("BbkName: " + bbkName);
        }


    }

	public static class Protein extends Component
	{
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

		@Override
		public Protein toSubclass()
		{	
			return this;
		}
		
		public void display()
		{
			super.display();
			System.out.println("SecondaryType :" + secondaryType);
			System.out.println(center.toString());
			System.out.println("Color: " + color);
		}
	
	}

	public static class BackBone extends Component
	{
		public Point leftPoint;
		public Point rightPoint;

		public BackBone(int theID, Point left, Point right)
		{
			super(theID, BbkType.Sketch.BACKBONE);
			this.leftPoint = left;
			this.rightPoint = right;
		}

		@Override
		public BackBone toSubclass()
		{	
			return this;
		}
		
		public void display()
		{
			super.display();
			System.out.println(leftPoint.toString());
			System.out.println(rightPoint.toString());
		}
	}

	public static class Relation extends Component
	{
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

		@Override
		public Relation toSubclass()
		{	
			return this;
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

	public static class BioVector extends Component
	{
		public int secondaryType;
		public Point center;
		public double scale;
		
		public BioVector(int theID, int secondaryType, Point center, double scale)
		{
			super(theID, BbkType.Sketch.VECTOR);
			this.secondaryType = secondaryType;
			this.center = center;
			this.scale = scale;
		}

		@Override
		public BioVector toSubclass()
		{	
			return this;
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
