package data_center;

import java.util.ArrayList;

import data_center.SketchComponent.BackBone;
import data_center.SketchComponent.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/** This class provides project operations include add, remove, modify components, 
 * ctrlZ, ctrlY function and save & load.  */
public class SketchProject
{
	public class Operation
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
		
		public Operation(int ID, int opeType, int attType, Object pre, Object fol)
		{
			this.ID = ID;
			this.operationType = opeType;
			this.attributeType = attType;
			this.previous = pre;
			this.following = fol;
		}

		public Operation generateReversedOp()
		{
			switch (operationType)
			{	case ADD:
					return new Operation(ID, REMOVE, attributeType, following, previous);
				case REMOVE:
					return new Operation(ID, ADD, attributeType, following, previous);
				case MODIFY:
					return new Operation(ID, MODIFY, attributeType, following, previous);
				default:
					return null;	
			}
		}
		
		@SuppressWarnings("unused")
		private int checkAttributeType(int opeType, Object pre, Object fol)
		{	
			if (pre == null && fol == null)
				return TYPE_NULL;
			if (pre != null && fol != null && pre.getClass() != fol.getClass())
				return TYPE_NULL;
			if (opeType == ADD || opeType == REMOVE)
				return TYPE_COMPONENT;
			
			Object obj = (pre != null ? pre : fol);
			switch (obj.getClass().getSimpleName())
			{	case "String":
					return TYPE_STRING;
				case "Rectangle":
					return TYPE_BOUNDS;
				case "Integer":
				case "Double":
					return TYPE_THICKNESS;
				case "Font":
					return TYPE_FONT;
				case "Color":
					return TYPE_COLOR;
				default:
					return TYPE_NULL;
			}
		}
	}

	
	// attributes
	public String name;
	public String filePath;
	
	public ArrayList<Component> componentList = new ArrayList<Component>();
	public ArrayList<BackBone> backBoneList = new ArrayList<BackBone>();
	public HistoryList<Operation> operationHistory = new HistoryList<Operation>();

	public boolean modified = false;
	

    public SketchProject(String name)
    {
		this.name = name;
    }
    

	public Component findComponentByID(int ID)
	{
		for (Component comp : componentList)
			if (comp.ID == ID)
				return comp;
		// not found
		return null;
	}
	
	public BackBone findBackBoneInBackBoneListByID(int ID)
	{	
		for (BackBone backBone : backBoneList)
			if (backBone.ID == ID)
				return backBone;
		// not found
		return null;
	}

	/** Registration function, receive the user operation and remember it in the 
	 * backstage.  */
	public void addComponent(Component component)
	{
		if (component != null)
		{	Operation operation = new Operation
				(component.ID, Operation.ADD, Operation.TYPE_COMPONENT, null, component);
			operationHistory.putInItem(operation);
			exeOperation(operation);
			modified = true;
		}
	}

	/** Registration function, receive the user operation and remember it in the 
	 * backstage.  */
	public void delComponent(Component component)
	{
		if (component != null)
		{	Operation operation = new Operation
				(component.ID, Operation.REMOVE, Operation.TYPE_COMPONENT, component, null);
			operationHistory.putInItem(operation);
			exeOperation(operation);
			modified = true;
		}
	}

	/** Registration function, receive the user operation and remember it in the 
	 * backstage. The attributeType should be picked from Operation.TYPE_XXX. The
	 *  folAttribute is the attribute changed into, such as Point, Color */
	public void modifyComponent(int theID, int attributeType, Object folAttribute)
	{
		Component component = findComponentByID(theID);
		if (component == null)
			return;
		// operation.previous is null to be parsed in switch(){}
		Operation operation = new Operation
				(component.ID, Operation.MODIFY, attributeType, null, folAttribute);
		switch (attributeType)
		{	case Operation.TYPE_STRING:
				operation.previous = component.getString();	break;
			case Operation.TYPE_BOUNDS:
				operation.previous = component.getBounds();	break;
			case Operation.TYPE_THICKNESS:
				operation.previous = component.getThickness();	break;
			case Operation.TYPE_FONT:
				operation.previous = component.getFont();	break;
			case Operation.TYPE_COLOR:
				operation.previous = component.getColor();	break;
			default:
				return;
		}
		operationHistory.putInItem(operation);
		exeOperation(operation);
		modified = true;
	}
	
	/** Registration function, receive the user operation and remember it in the 
	 * backstage.  */
	public void onAbsorb(int backBoneID, int bbkID, int index)
	{	
		Component comp = findComponentByID(backBoneID);
		if (comp == null || !comp.primaryType.equals(BackBone.class.getSimpleName()))
			return;
		// else
		comp.toBackBone().bbkChildren.add(index, bbkID);
	}
	
	/** Registration function, receive the user operation and remember it in the 
	 * backstage.  */
	public void onDesorb(int backBoneID, int bbkID)
	{	
		Component comp = findComponentByID(backBoneID);
		if (comp == null || !comp.primaryType.equals(BackBone.class.getSimpleName()))
			return;
		// else
		BackBone backBone = comp.toBackBone();
		for (Integer IDInList : backBone.bbkChildren)
			if (IDInList == bbkID)
				backBone.bbkChildren.remove(IDInList);
	}
	
	/** Guidance function, guide the GUI the operation from the history list. 
	 * No registration is needed since the operation is not read from the user.  */
	public Operation ctrlZ()
	{
		Operation originalOp = operationHistory.rollBack();
		if (originalOp == null)
			return null;
		// else... 
		Operation reversedOp = originalOp.generateReversedOp();
		exeOperation(reversedOp);
		modified = true;
		return reversedOp;
	}

	/** Guidance function, guide the GUI the operation from the history list. 
	 * No registration is needed since the operation is not read from the user.  */
	public Operation ctrlY()
	{
		Operation originalOp = operationHistory.goForward();
		if (originalOp == null)
			return null;
		// else... 
		exeOperation(originalOp);
		modified = true;
		return originalOp;
	}
	
	public void displayComponents()
	{	
		for (Component component : componentList)
			component.display();
	}
	
	
	
	
	/** Change the componentList without change the historyList 
	 * because it is set to act under an operation, not to generate one.  */
	private void exeOperation(Operation operation)
	{	
		switch (operation.operationType)
		{	case Operation.ADD:
				Component compToAdd = (Component) operation.following;
				componentList.add(compToAdd);
				if (compToAdd.primaryType.equals(BackBone.class.getSimpleName()))
					backBoneList.add(compToAdd.toBackBone());
				break;
			case Operation.REMOVE:
				Component compToRemove = (Component) operation.previous;
				componentList.remove(compToRemove);
				if (compToRemove.primaryType.equals(BackBone.class.getSimpleName()))
					backBoneList.remove(compToRemove.toBackBone());
				break;
			case Operation.MODIFY:
				Component component = findComponentByID(operation.ID);
				Object object = operation.following;
				switch (operation.attributeType)
				{	case Operation.TYPE_STRING:
						component.setString((String) object);	break;
					case Operation.TYPE_BOUNDS:
						component.setBounds((Rectangle) object);
						if (isMoveEvent(operation.previous, operation.following))
							if (component.primaryType.equals(BackBone.class.getSimpleName()))
								onLinkage(component.toBackBone(), operation.previous, operation.following);
							else if (component.primaryType.equals(BackBone.class.getSimpleName()))
								onLinkage(component.toRelation(), operation.previous, operation.following);
						break;
					case Operation.TYPE_THICKNESS:
						component.setThickness((Double) object);	break;
					case Operation.TYPE_FONT:
						component.setFont((Font) object);	break;
					case Operation.TYPE_COLOR:
						component.setColor((Color) object);	break;
					default:
						return;
				}
		}
	}
	
	/** Judge if the modify contains a move event by the size of the component. 
	 * If the size has not changed(and the position has changed, namely), 
	 * it should be a move event. Otherwise it is the user dragging the edge 
	 * of the backbone to extend or shrink it, not contains a linkage event.  */
	private boolean isMoveEvent(Object previous, Object following)
	{	
		Rectangle preBounds = (Rectangle) previous;
		Rectangle folBounds = (Rectangle) following;
		return !(preBounds.width - folBounds.width == 0 && 
				 preBounds.height - folBounds.height == 0);
	}
	
	/** 联动 in Chinese, move the biobricks on the same backbone together if the 
	 * backbone is moved.  */
	private void onLinkage(BackBone backBone, Object previous, Object following)
	{
		Rectangle preBounds = (Rectangle) previous;
		Rectangle folBounds = (Rectangle) following;
		int dx = folBounds.x - preBounds.x,
			dy = folBounds.y - preBounds.y;
		for (Integer bbkID : backBone.bbkChildren)
		{	Component comp = findComponentByID(bbkID);
			if (comp == null || !comp.primaryType.equals(BioBrick.class.getSimpleName()))
				continue;
			// else...
			Rectangle preBoundsOfBbk = comp.getBounds();
			Rectangle folBoundsOfBbk = 
				new Rectangle(preBoundsOfBbk.x + dx, preBoundsOfBbk.y + dy, 
						preBoundsOfBbk.width, preBoundsOfBbk.height);
			comp.setBounds(folBoundsOfBbk);
		}
	}
	
	/** 联动 in Chinese, move the points of a curve in a relation when the 
	 * relation picture is moved.  */
	private void onLinkage(Relation relation, Object previous, Object following)
	{
		Rectangle preBounds = (Rectangle) previous;
		Rectangle folBounds = (Rectangle) following;
		int dx = folBounds.x - preBounds.x,
			dy = folBounds.y - preBounds.y;
		for (Point point : relation.posList)
		{	point.x += dx;
			point.y += dy;
		}
	}
	
	
	
	
	/** Different from the overrided version in that this function is used to save
	 * into the file path already specified, like "Save", not "Save As".  */
	public void saveIntoFile()
	{	
		saveIntoFile(filePath);
	}
	
	/** Save the project graph into XML file, note that the project.name will be 
	 * specified after the file name. This function is used as the "Save As" 
	 * function, if path already specified(namely already saved once), you may 
	 * call the saveIntoFile(), the function with no parameter to save again.  */
	public void saveIntoFile(String filePath)
	{
		Document doc = null;
  	  	try {
  		  doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
  	  	} catch (ParserConfigurationException e) {e.printStackTrace();}
  	  	Element root = doc.createElement("GraphDesign");
	  	doc.appendChild(root); // 将根元素添加到文档上
	  	
	  	// 获取节点信息
  	  	for (Component component : componentList)
  	  	{	Element componentNode = createComponentNode(doc, component);
  	  		root.appendChild(componentNode);
  	  	}
  	  		
  	  	try
  	  	{	FileOutputStream fos = new FileOutputStream(filePath);
  	  		OutputStreamWriter outwriter = new OutputStreamWriter(fos);
  	  		
  	  		Source source = new DOMSource(doc);
  	  		Result result = new StreamResult(outwriter);
  	  		Transformer xformer = TransformerFactory.newInstance().newTransformer();
  	  		xformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
  	  		xformer.transform(source, result);
  	  		
  	  		outwriter.close();
	  		fos.close();
  	  	} catch(Exception e) {e.printStackTrace();}
  	  	
  	  	name = getFileName(filePath);
  	  	this.filePath = filePath;
  	  	modified = false;
	}
	
	/** Load the project graph from XML file, note that the project.name will be 
	 * specified after the file name.  */
	public void loadFromFile(String filePath)
	{	
		componentList.clear();
    	operationHistory.clear();
    	
    	Document doc = null;
  	  	try {
  		  doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filePath);
  	  	} catch (Exception e) {e.printStackTrace();}
        
  	  	// 下面开始读取
    	Element root = doc.getDocumentElement(); // 获取根元素
    	NodeList nodes = root.getElementsByTagName("Component");
    	
    	for (int i = 0; i < nodes.getLength(); i++)
    	{	// 依次取得每一个节点
	    	Element componentNode = (Element) nodes.item(i);
	    	Component component = parseComponentNode(componentNode);
	    	componentList.add(component);
	    }
	    
    	name = getFileName(filePath);
    	this.filePath = filePath;
    	modified = false;
	}
	
	
	
	
	
	/** Utility function to cut the file name off from the file path.  */
	private String getFileName(String filePath)
	{	
		String fileNameWithSuffix = new File( filePath.trim()).getName();
		int index = fileNameWithSuffix.lastIndexOf(".");
		return fileNameWithSuffix.substring(0, index);
	}
	
	
	
	private static final String ID = "ID";
	private static final String PRIMARY_TYPE = "primaryType";
	private static final String COMPONENT = "Component";
	private static final String SECONDARY_TYPE = "secondaryType";
	private static final String STRING = "string";
	private static final String BOUNDS = "bounds";
	private static final String CURVE = "curve";
	private static final String THICKNESS = "thickness";
	private static final String FONT = "font";
	private static final String COLOR = "color";
	private static final String CHILDREN = "children";
	private static final String NULL = "null";
	
	
	private Element createComponentNode(Document doc, Component component)
	{
		Element componentNode = doc.createElement(COMPONENT);
  		componentNode.setAttribute(ID, Integer.toString(component.ID));
  		componentNode.setAttribute(PRIMARY_TYPE, component.primaryType);
  		
  		// about secondaryType
  		Element eleSecType = doc.createElement(SECONDARY_TYPE);
  		Integer secondaryType = component.getSecondaryType();
  		Text txtSecType = doc.createTextNode(
  				secondaryType != null ? secondaryType.toString() : NULL);
  		eleSecType.appendChild(txtSecType);
  		componentNode.appendChild(eleSecType);
  		
  		// about string (text, bbkName)
  		Element eleString = doc.createElement(STRING);
  		String string = component.getString();
  		Text txtString = doc.createTextNode(
  				string != null && !string.equals("") ? string : NULL);
  		eleString.appendChild(txtString);
  		componentNode.appendChild(eleString);
  		
  		// about bounds
  		Element eleBounds = doc.createElement(BOUNDS);
  		Rectangle bounds = component.getBounds();
  		Text txtBounds = doc.createTextNode(bounds != null ? 
  			bounds.x + " " + bounds.y + " " + bounds.width + " " + bounds.height : NULL);
  		eleBounds.appendChild(txtBounds);
  		componentNode.appendChild(eleBounds);
  		
  		// about curve (relation.points)
  		Element eleCurve = doc.createElement(CURVE);
  		ArrayList<Point> curve = component.getCurve();
  		String curveStr = "";
  		if (curve != null && curve.size() != 0)	// a list of [] become <curve/> in file
  			for (Point point : curve)
  				curveStr += point.x + " " + point.y + ",";
  		else
  			curveStr = NULL;
  		Text txtCurve = doc.createTextNode(curveStr);
  		eleCurve.appendChild(txtCurve);
  		componentNode.appendChild(eleCurve);
  		
  		// about thickness
  		Element eleSize = doc.createElement(THICKNESS);
  		Double size = component.getThickness();
  		Text txtSize = doc.createTextNode(
  				size != null ? size.toString() : NULL);
  		eleSize.appendChild(txtSize);
  		componentNode.appendChild(eleSize);
  		
  		// about font
  		Element eleFont = doc.createElement(FONT);
  		Font font = component.getFont();
  		Text txtFont = doc.createTextNode(font != null ? 
  				font.getName() + "," + font.getStyle() + "," + font.getSize() : NULL);
  		eleFont.appendChild(txtFont);
  		componentNode.appendChild(eleFont);
  		
  		// about color
  		Element eleColor = doc.createElement(COLOR);
  		Color color = component.getColor();
  		Text txtColor = doc.createTextNode(
  				color != null ? Integer.toString(color.getRGB()) : NULL);
  		eleColor.appendChild(txtColor);
  		componentNode.appendChild(eleColor);
  		
  		// about children
  		Element eleChildren = doc.createElement(CHILDREN);
  		ArrayList<Integer> children = component.getChildren();
  		String childrenStr = "";
  		if (children != null && children.size() != 0)
  			for (Integer bbkID : children)
  				childrenStr += bbkID + " ";
  		else
  			childrenStr = NULL;
  		Text txtChildren = doc.createTextNode(childrenStr);
  		eleChildren.appendChild(txtChildren);
  		componentNode.appendChild(eleChildren);
  		
  		return componentNode;
	}
	
	private Component parseComponentNode(Element componentNode)
	{	
		int ID = Integer.parseInt(componentNode.getAttribute(SketchProject.ID));
		String primaryType = componentNode.getAttribute(SketchProject.PRIMARY_TYPE);
		
		// about secondaryType
		Element eleSecType = 
				(Element) componentNode.getElementsByTagName(SECONDARY_TYPE).item(0);
		String secTypeStr = eleSecType.getFirstChild().getNodeValue();
		Integer secondaryType = null;
		if ( !secTypeStr.equals(NULL) )
			secondaryType = Integer.parseInt(secTypeStr);
		
		// about string (text, bbkName)
		Element eleString = (Element) componentNode.getElementsByTagName(STRING).item(0);
		String string = eleString.getFirstChild().getNodeValue();
		if (string.equals(NULL))
			string = null;
		
		// about bounds
		Element eleBounds = (Element) componentNode.getElementsByTagName(BOUNDS).item(0);
		String boundsStr = eleBounds.getFirstChild().getNodeValue();
		Rectangle bounds = null;
		if ( !boundsStr.equals(NULL) )
		{	String[] xy = boundsStr.split(" ");
			bounds = new Rectangle(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]), 
								   Integer.parseInt(xy[2]), Integer.parseInt(xy[3]));
		}
		
		// about curve
		Element eleCurve = (Element) componentNode.getElementsByTagName(CURVE).item(0);
		String curveStr = eleCurve.getFirstChild().getNodeValue();
		ArrayList<Point> curve = null;
		if ( !curveStr.equals(NULL) )
		{	curve = new ArrayList<Point>();
			String[] pointStrList = curveStr.split(",");
			for (String pointStr : pointStrList)
				if ( !pointStr.equals("") )	// there WILL be an "" in each list
				{	String[] xy = pointStr.split(" ");
					curve.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
				}
		}
		
		// about thickness
		Element eleThickness = (Element) componentNode.getElementsByTagName(THICKNESS).item(0);
		String thicknessStr = eleThickness.getFirstChild().getNodeValue();
		Double thickness = null;
		if ( !thicknessStr.equals(NULL) )
			thickness = Double.parseDouble(thicknessStr);
		
		// about font
		Element eleFont = (Element) componentNode.getElementsByTagName(FONT).item(0);
		String fontStr = eleFont.getFirstChild().getNodeValue();
		Font font = null;
		if ( !fontStr.equals(NULL) )
		{	String[] tokens = fontStr.split(",");
			font = new Font
				(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
		}
		
		// about color
		Element eleColor = (Element) componentNode.getElementsByTagName(COLOR).item(0);
		String colorStr = eleColor.getFirstChild().getNodeValue();
		Color color = null;
		if ( !colorStr.equals(NULL) )
			color = new Color(Integer.parseInt(colorStr));
		
		// about children
		Element eleChildren = (Element) componentNode.getElementsByTagName(CHILDREN).item(0);
		String childrenStr = eleChildren.getFirstChild().getNodeValue();
		ArrayList<Integer> children = null;
		if ( !childrenStr.equals(NULL) )
		{	children = new ArrayList<Integer>();
			String[] childStrList = childrenStr.split(" ");
			for (String childStr : childStrList)
				if ( !childStr.equals("") )	// there WILL be an "" in each list
					children.add(Integer.parseInt(childStr));
		}
		
		Component component = null;
		if (primaryType.equals(Label.class.getSimpleName()))
			component = new Label(ID, string, bounds, font, color);
		else if (primaryType.equals(BioBrick.class.getSimpleName()))
			component = new BioBrick(ID, string, secondaryType, bounds, color);
		else if (primaryType.equals(Protein.class.getSimpleName()))
			component = new Protein(ID, secondaryType, bounds, color);
		else if (primaryType.equals(BackBone.class.getSimpleName()))
			component = new BackBone(ID, bounds, children);
		else if (primaryType.equals(Relation.class.getSimpleName()))
			component = new Relation(ID, secondaryType, bounds,  curve, color, thickness);
		else if (primaryType.equals(BioVector.class.getSimpleName()))
			component = new BioVector(ID, secondaryType, bounds);
		
		return component;
	}
}
