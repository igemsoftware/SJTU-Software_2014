package data_center;

public class SketchOperation
{
	/** Add component, not bbkName or sth like that */
	public final static int ADD = 1;
	/** Remove component, not bbkName or sth like that */
	public final static int REMOVE = 2;
	public final static int MODIFY = 3;

	// no TYPE_SECONDART_TYPE and TYPE_CURVE cause they can't be modified
	public final static int TYPE_COMPONENT = 0;
	public final static int TYPE_STRING = 1;
	public final static int TYPE_BOUNDS = 2;
	public final static int TYPE_FONT = 3;
	public final static int TYPE_COLOR = 4;
	public final static int TYPE_THICKNESS = 5;
	public final static int TYPE_NULL = -1;
	
	// attributes
	public int ID;
	public int operationType;
	public int attributeType;
	public Object previous = null;
	public Object following = null;
	
	public SketchOperation(int ID, int opeType, int attType, Object pre, Object fol)
	{
		this.ID = ID;
		this.operationType = opeType;
		this.attributeType = attType;
		this.previous = pre;
		this.following = fol;
	}

	public SketchOperation generateReversedOp()
	{
		switch (operationType)
		{	case ADD:
				return new SketchOperation(ID, REMOVE, attributeType, following, previous);
			case REMOVE:
				return new SketchOperation(ID, ADD, attributeType, following, previous);
			case MODIFY:
				return new SketchOperation(ID, MODIFY, attributeType, following, previous);
			default:
				return null;	
		}
	}
	
	public void display()
	{	
		String operation = null, attribute = null;
		switch (operationType)
		{	case ADD:
				operation = "add";	break;
			case REMOVE:
				operation = "remove";	break;
			case MODIFY:
				operation = "modify";	break;
		}
		
		switch (attributeType)
		{	case TYPE_COMPONENT:
				attribute = "component";	break;
			case TYPE_BOUNDS:
				attribute = "bounds";	break;
			case TYPE_COLOR:
				attribute = "color";	break;
			case TYPE_FONT:
				attribute = "font";	break;
			case TYPE_STRING:
				attribute = "string";	break;
			case TYPE_THICKNESS:
				attribute = "thickness";	break;
			case TYPE_NULL:
				attribute = "null";	break;
		}
		
		System.out.println("Operation component ID: " + ID);
		System.out.println("Operation type: " + operation);
		System.out.println("Attribute type: " + attribute);
		System.out.println();
	}
}

