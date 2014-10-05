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
		public String primaryType;

		public Component(int theID, String theType)
		{
			ID = theID;
			primaryType = theType;
		}
		
		/** This function is used to be inherited to convert Component
		 * object into its subclasses using convariant, but seems failed... */
		public abstract Component toSubclass();
		
		// these functions are used to convert to subclasses in an easier way
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
		
		
		// the function below is to be overrided, the version in super class 
		//	represents the condition that the attribute is not in the class so that
		//	the subclass which don't have these attributes don't have to override them
		public Integer getSecondaryType()
		{	return null;	}
		
		/** Secondary type is defined in BbkType.Sketch */
		public void setSecondaryType(Integer type) {}
		
		/** text for Label, and bbkName for BioBrick */
		public String getString()
		{	return null;	}
		
		/** text for Label, and bbkName for BioBrick */
		public void setString(String str) {}
		
		public Point getCenter()
		{	return null;	}
		
		public void setCenter(Point center) {}
		
		public Font getFont()
		{	return null;	}
		
		public void setFont(Font font) {}
		
		public Color getColor()
		{	return null;	}
		
		public void setColor(Color color) {}
		
		/** length in BackBone, thickness in Relation and scale in BioVector */
		public Double getSize()
		{	return null;	}
		
		public void setSize(double size) {}
		
		public ArrayList<Point> getCurve()
		{	return null;	}
		
		public void setCurve(ArrayList<Point> curve) {}
		
		/** bbkChildren in backBone, the IDs */
		public ArrayList<Integer> getChildren()
		{	return null;	}
		
		public void setChildren(ArrayList<Integer> children) {}
		
		public void display()
		{
			System.out.println("\n\n********");
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
			super(theID, Label.class.getSimpleName());
			this.text = text;
			this.center = pos;
			this.font = font;
			this.color = color;
		}
		
		@Override
		public Label toSubclass()
		{	return this;	}
		
		@Override
		public String getString()
		{	return text;	}
		
		@Override
		public void setString(String text)
		{	this.text = text;	}
		
		@Override
		public Point getCenter()
		{	return center;	}
		
		@Override
		public void setCenter(Point pos)
		{	center.setLocation(pos);	}
		
		@Override
		public Font getFont()
		{	return font;	}
		
		@Override
		public void setFont(Font font)
		{	this.font = font;	}
		
		@Override
		public Color getColor()
		{	return color;	}
		
		@Override
		public void setColor(Color color)
		{	this.color = color;	}
		
		public void display()
		{
			super.display();
			System.out.println("Text: " + text);
			System.out.println("Center: " + center.toString());
		}
	}

    public static class BioBrick extends Component
    {
		public BbkOutline bbkOutline = null;
		public int secondaryType;
		public Point center;
		public Color color;
        
		// primary type already known in the class
		public BioBrick(int theID, int secondaryType, 
        		Point center, Color color)
        {
        	super(theID, BioBrick.class.getSimpleName());
        	this.secondaryType = secondaryType;
        	this.bbkOutline = null;
			this.center = center;
			this.color = color;
        }
		
		/** bbkOutline should be set by setString(bbkName) */
        public BioBrick(int theID, String bbkName, int secondaryType, 
        		Point center, Color color)
        {
        	super(theID, BioBrick.class.getSimpleName());
        	this.secondaryType = secondaryType;
        	this.bbkOutline = DatabaseConnector.getOutlineByName(bbkName);
			this.center = center;
			this.color = color;
        }

        @Override
		public BioBrick toSubclass()
		{	return this;	}
        
        @Override
        public Integer getSecondaryType()
		{	return secondaryType;	}
		
        @Override
		public void setSecondaryType(Integer type)
        {	secondaryType = type;	}
        
        @Override
		public String getString()
		{	return bbkOutline != null ? bbkOutline.name : null;	}
		
        /** Set bbkOutline by bbkName in the function, if there's no such 
         * bbkName in the database, bbkOutline will be a null */
		@Override
		public void setString(String bbkName)
		{	bbkOutline = DatabaseConnector.getOutlineByName(bbkName);	}
        
		@Override
		public Point getCenter()
		{	return center;	}
		
		@Override
		public void setCenter(Point pos)
		{	center.setLocation(pos);	}
		
		@Override
		public Color getColor()
		{	return color;	}
		
		@Override
		public void setColor(Color color)
		{	this.color = color;	}
		
        public void display()
        {
			super.display();
			System.out.println("BbkName: " + bbkOutline.name);
			System.out.println("SecondaryType: " + secondaryType);
			System.out.println("Center: " + center.toString());
			System.out.println("Color: " + color);
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
			super(theID, Protein.class.getSimpleName());
			this.secondaryType = secondaryType; 
			this.center = center;
			this.color = color;
		}

		@Override
		public Protein toSubclass()
		{	return this;	}
		
		@Override
        public Integer getSecondaryType()
		{	return secondaryType;	}
		
        @Override
		public void setSecondaryType(Integer type)
        {	secondaryType = type;	}
		
		@Override
		public Point getCenter()
		{	return center;	}
		
		@Override
		public void setCenter(Point pos)
		{	center.setLocation(pos);	}
		
		@Override
		public Color getColor()
		{	return color;	}
		
		@Override
		public void setColor(Color color)
		{	this.color = color;	}
		
		public void display()
		{
			super.display();
			System.out.println("SecondaryType :" + secondaryType);
			System.out.println("Center: " + center.toString());
			System.out.println("Color: " + color);
		}
	
	}

	public static class BackBone extends Component
	{
		public Point center;
		public int length;
		public ArrayList<Integer> bbkChildren;
		
		public BackBone(int theID, Point pos, int length)
		{	
			super(theID, BackBone.class.getSimpleName());
			this.center = pos;
			this.length = length;
			this.bbkChildren = new ArrayList<Integer>();
		}

		public BackBone(int theID, Point pos, int length, ArrayList<Integer> childIDList)
		{
			super(theID, BackBone.class.getSimpleName());
			this.center = pos;
			this.length = length;
			this.bbkChildren = childIDList;
		}

		@Override
		public BackBone toSubclass()
		{	return this;	}
		
		@Override
		public Point getCenter()
		{	return center;	}
		
		@Override
		public void setCenter(Point pos)
		{	center.setLocation(pos);	}
		
		@Override
		public Double getSize()
		{	return (double) length;	}
		
		@Override
		public void setSize(double length)
		{	this.length = (int) length;	}
		
		@Override
		public ArrayList<Integer> getChildren()
		{	return bbkChildren;	}
		
		@Override
		public void setChildren(ArrayList<Integer> children)
		{	this.bbkChildren = children;	}
		
		public void display()
		{
			super.display();
			System.out.println("Center: " + center.toString());
			System.out.println("Length: " + length);
			System.out.println("BbkChildren: ");
			for (Integer bbkID : bbkChildren)
				System.out.println("\tChild with ID: " + bbkID);
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
			super(theID, Relation.class.getSimpleName());
			this.secondaryType = secondaryType;
			this.posList = list;
			this.color = color;
			this.thickness = thickness;
		}

		@Override
		public Relation toSubclass()
		{	return this;	}
		
		@Override
        public Integer getSecondaryType()
		{	return secondaryType;	}
		
        @Override
		public void setSecondaryType(Integer type)
        {	secondaryType = type;	}
		
		@Override
		public ArrayList<Point> getCurve()
		{	return this.posList;	}
		
		@Override
		public void setCurve(ArrayList<Point> curve)
		{	this.posList = curve;	}
		
		@Override
		public Color getColor()
		{	return color;	}
		
		@Override
		public void setColor(Color color)
		{	this.color = color;	}
		
		@Override
		public Double getSize()
		{	return (double) thickness;	}
		
		@Override
		public void setSize(double thickness)
		{	this.thickness = (int) thickness;	}
		
		public void display()
		{
			super.display();
			System.out.println("SecondaryType: " + secondaryType);
			System.out.println("Curve: ");
			for (Point point : posList)
				System.out.println("\t" + point.toString());
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
			super(theID, BioVector.class.getSimpleName());
			this.secondaryType = secondaryType;
			this.center = center;
			this.scale = scale;
		}

		@Override
		public BioVector toSubclass()
		{	return this;	}
		
		@Override
        public Integer getSecondaryType()
		{	return secondaryType;	}
		
        @Override
		public void setSecondaryType(Integer type)
        {	secondaryType = type;	}
		
		@Override
		public Point getCenter()
		{	return center;	}
		
		@Override
		public void setCenter(Point pos)
		{	center.setLocation(pos);	}
		
		@Override
		public Double getSize()
		{	return scale;	}
		
		@Override
		public void setSize(double scale)
		{	this.scale = scale;	}
		
		public void display()
		{
			super.display();
			System.out.println("SecondaryType: " + secondaryType);
			System.out.println("Center: " + center.toString());
			System.out.println("Scale: " + scale);
		}
	}
}
