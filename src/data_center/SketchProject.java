package data_center;

import java.util.ArrayList;

import data_center.SketchComponent.BackBone;
import data_center.SketchComponent.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

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
		public final static int TYPE_CENTER = 2;
		public final static int TYPE_FONT = 3;
		public final static int TYPE_COLOR = 4;
		public final static int TYPE_SIZE = 5;
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
				case "Point":
					return TYPE_CENTER;
				case "Integer":
				case "Double":
					return TYPE_SIZE;
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

	/**
	 *  attributeType should be picked from Operation.TYPE_
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
			case Operation.TYPE_CENTER:
				operation.previous = component.getCenter();	break;
			case Operation.TYPE_SIZE:
				operation.previous = component.getSize();	break;
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
	
	public void onAbsorb(int backBoneID, int bbkID)
	{	
		Component comp = findComponentByID(backBoneID);
		if (comp == null || !comp.primaryType.equals(BackBone.class.getSimpleName()))
			return;
		// else
		comp.toBackBone().bbkChildren.add(bbkID);
	}
	
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
	 * because it is set to act under an operation, not to generate one */
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
					case Operation.TYPE_CENTER:
						component.setCenter((Point) object);
						if (component.primaryType.equals(BackBone.class.getSimpleName()))
							onLinkage(component.toBackBone(), (Point)operation.previous, (Point)operation.following);
						break;
					case Operation.TYPE_SIZE:
						component.setSize((Double) object);	break;
					case Operation.TYPE_FONT:
						component.setFont((Font) object);	break;
					case Operation.TYPE_COLOR:
						component.setColor((Color) object);	break;
					default:
						return;
				}
		}
	}
	
	private void onLinkage(BackBone backBone, Point previous, Point following)
	{
		int dx = following.x - previous.x,
			dy = following.y - previous.y;
		for (Integer bbkID : backBone.bbkChildren)
		{	Component comp = findComponentByID(bbkID);
			if (comp == null || !comp.primaryType.equals(BioBrick.class.getSimpleName()))
				continue;
			// else...
			Point preCenterOfBbk = comp.getCenter();
			Point folCenterOfBbk = new Point(preCenterOfBbk.x + dx, preCenterOfBbk.y + dy);
			comp.setCenter(folCenterOfBbk);
		}
		
	}
	
	
	


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
  	  	modified = false;
	}
	
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
    	modified = false;
	}
	
	
	
	
	
	
	private String getFileName(String filePath)
	{	
		String fileNameWithSuffix = new File( filePath.trim()).getName();
		int index = fileNameWithSuffix.lastIndexOf(".");
		return fileNameWithSuffix.substring(0, index);
	}
	
	
	private Element createComponentNode(Document doc, Component component)
	{
		Element componentNode = doc.createElement("Component");
  		componentNode.setAttribute("ID", Integer.toString(component.ID));
  		componentNode.setAttribute("primaryType", component.primaryType);
  		
  		// about secondaryType
  		Element eleSecType = doc.createElement("secondaryType");
  		Integer secondaryType = component.getSecondaryType();
  		Text txtSecType = doc.createTextNode(
  				secondaryType != null ? secondaryType.toString() : "null");
  		eleSecType.appendChild(txtSecType);
  		componentNode.appendChild(eleSecType);
  		
  		// about string (text, bbkName)
  		Element eleString = doc.createElement("string");
  		String string = component.getString();
  		Text txtString = doc.createTextNode(
  				string != null ? string : "null");
  		eleString.appendChild(txtString);
  		componentNode.appendChild(eleString);
  		
  		// about center
  		Element eleCenter = doc.createElement("center");
  		Point center = component.getCenter();
  		Text txtCenter = doc.createTextNode(
  				center != null ? center.x + " " + center.y : "null");
  		eleCenter.appendChild(txtCenter);
  		componentNode.appendChild(eleCenter);
  		
  		// about curve (relation.points)
  		Element eleCurve = doc.createElement("curve");
  		ArrayList<Point> curve = component.getCurve();
  		String curveStr = "";
  		if (curve != null && curve.size() != 0)	// a list of [] become <curve/> in file
  			for (Point point : curve)
  				curveStr += point.x + " " + point.y + ",";
  		else
  			curveStr = "null";
  		Text txtCurve = doc.createTextNode(curveStr);
  		eleCurve.appendChild(txtCurve);
  		componentNode.appendChild(eleCurve);
  		
  		// about size (length, thickness, scale)
  		Element eleSize = doc.createElement("size");
  		Double size = component.getSize();
  		Text txtSize = doc.createTextNode(
  				size != null ? size.toString() : "null");
  		eleSize.appendChild(txtSize);
  		componentNode.appendChild(eleSize);
  		
  		// about font
  		Element eleFont = doc.createElement("font");
  		Font font = component.getFont();
  		Text txtFont = doc.createTextNode(font != null ? 
  				font.getName() + "," + font.getStyle() + "," + font.getSize() : "null");
  		eleFont.appendChild(txtFont);
  		componentNode.appendChild(eleFont);
  		
  		// about color
  		Element eleColor = doc.createElement("color");
  		Color color = component.getColor();
  		Text txtColor = doc.createTextNode(
  				color != null ? Integer.toString(color.getRGB()) : "null");
  		eleColor.appendChild(txtColor);
  		componentNode.appendChild(eleColor);
  		
  		// about children
  		Element eleChildren = doc.createElement("children");
  		ArrayList<Integer> children = component.getChildren();
  		String childrenStr = "";
  		if (children != null && children.size() != 0)
  			for (Integer bbkID : children)
  				childrenStr += bbkID + " ";
  		else
  			childrenStr = "null";
  		Text txtChildren = doc.createTextNode(childrenStr);
  		eleChildren.appendChild(txtChildren);
  		componentNode.appendChild(eleChildren);
  		
  		return componentNode;
	}
	
	private Component parseComponentNode(Element componentNode)
	{	
		int ID = Integer.parseInt(componentNode.getAttribute("ID"));
		String primaryType = componentNode.getAttribute("primaryType");
		
		// about secondaryType
		Element eleSecType = 
				(Element) componentNode.getElementsByTagName("secondaryType").item(0);
		String secTypeStr = eleSecType.getFirstChild().getNodeValue();
		Integer secondaryType = null;
		if ( !secTypeStr.equals("null") )
			secondaryType = Integer.parseInt(secTypeStr);
		
		// about string (text, bbkName)
		Element eleString = (Element) componentNode.getElementsByTagName("string").item(0);
		String string = eleString.getFirstChild().getNodeValue();
		if (string.equals("null"))
			string = null;
		
		// about center
		Element eleCenter = (Element) componentNode.getElementsByTagName("center").item(0);
		String centerStr = eleCenter.getFirstChild().getNodeValue();
		Point center = null;
		if ( !centerStr.equals("null") )
		{	String[] xy = centerStr.split(" ");
			center = new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
		}
		
		// about curve
		Element eleCurve = (Element) componentNode.getElementsByTagName("curve").item(0);
		String curveStr = eleCurve.getFirstChild().getNodeValue();
		ArrayList<Point> curve = null;
		if ( !curveStr.equals("null") )
		{	curve = new ArrayList<Point>();
			String[] pointStrList = curveStr.split(",");
			for (String pointStr : pointStrList)
				if ( !pointStr.equals("") )	// there WILL be an "" in each list
				{	String[] xy = pointStr.split(" ");
					curve.add(new Point(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
				}
		}
		
		// about size
		Element eleSize = (Element) componentNode.getElementsByTagName("size").item(0);
		String sizeStr = eleSize.getFirstChild().getNodeValue();
		Double size = null;
		if ( !sizeStr.equals("null") )
			size = Double.parseDouble(sizeStr);
		
		// about font
		Element eleFont = (Element) componentNode.getElementsByTagName("font").item(0);
		String fontStr = eleFont.getFirstChild().getNodeValue();
		Font font = null;
		if ( !fontStr.equals("null") )
		{	String[] tokens = fontStr.split(",");
			font = new Font
				(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
		}
		
		// about color
		Element eleColor = (Element) componentNode.getElementsByTagName("color").item(0);
		String colorStr = eleColor.getFirstChild().getNodeValue();
		Color color = null;
		if ( !colorStr.equals("null") )
			color = new Color(Integer.parseInt(colorStr));
		
		// about children
		Element eleChildren = (Element) componentNode.getElementsByTagName("children").item(0);
		String childrenStr = eleChildren.getFirstChild().getNodeValue();
		ArrayList<Integer> children = null;
		if ( !childrenStr.equals("null") )
		{	children = new ArrayList<Integer>();
			String[] childStrList = childrenStr.split(" ");
			for (String childStr : childStrList)
				if ( !childStr.equals("") )	// there WILL be an "" in each list
					children.add(Integer.parseInt(childStr));
		}
		
		Component component = null;
		if (primaryType.equals(Label.class.getSimpleName()))
			component = new Label(ID, string, center, font, color);
		else if (primaryType.equals(BioBrick.class.getSimpleName()))
			component = new BioBrick(ID, string, secondaryType, center, color);
		else if (primaryType.equals(Protein.class.getSimpleName()))
			component = new Protein(ID, secondaryType, center, color);
		else if (primaryType.equals(BackBone.class.getSimpleName()))
			component = new BackBone(ID, center, (int)size.doubleValue(), children);
		else if (primaryType.equals(Relation.class.getSimpleName()))
			component = new Relation(ID, secondaryType, curve, color, (int)size.doubleValue());
		else if (primaryType.equals(BioVector.class.getSimpleName()))
			component = new BioVector(ID, secondaryType, center, size);
		
		return component;
	}
	
	
	
	
	
	
	public static void callWriteXmlFile(Document doc, Writer w, String encoding) {
		  try {
		   Source source = new DOMSource(doc);
		   Result result = new StreamResult(w);
		   Transformer xformer = TransformerFactory.newInstance()
		     .newTransformer();
		   xformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		   xformer.transform(source, result);
		  } catch (TransformerConfigurationException e) {
		   e.printStackTrace();
		  } catch (TransformerException e) {
		   e.printStackTrace();
		  }
		 }

    public void SaveIntoFile(String filePath, ArrayList<Component> List3)
    {  
    	System.out.println(List3.size());
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  	  	DocumentBuilder builder = null;
  	  	try {
  	  		builder = dbf.newDocumentBuilder();
  	  	} catch (Exception e) {
  	  	}
  	  	Document doc = builder.newDocument();
  	  	Element root = doc.createElement("GraphDesign");
  	  	doc.appendChild(root); // 将根元素添加到文档上
  	  	// 获取节点信息
  	  	for (int i = 0; i < List3.size(); i++) {
  	  	Component it= List3.get(i);
  	    System.out.println(it.getClass().getSimpleName());
  	  	if (it.getClass().getSimpleName().equals("Label")){
  		   Label l = it.toLabel();
  		   // 创建一个节点
  		   Element nod = doc.createElement("Component");
  		   nod.setAttribute("name", "Label");
  		   root.appendChild(nod);// 添加属性   
  		   // 创建文本姓名节点
  		   Element h1 = doc.createElement("text");
  		   nod.appendChild(h1);
  		   Text th1 = doc.createTextNode(l.text);
  		   h1.appendChild(th1);
  		   Element h2 = doc.createElement("centerx");
  		   nod.appendChild(h2); 
  		   Text th2 = doc.createTextNode(Integer.toString(l.center.x));
  		   h2.appendChild(th2);
  		   Element h3 = doc.createElement("centery");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode(Integer.toString(l.center.y));
		   h3.appendChild(th3);
		   Element h4 = doc.createElement("fonta");
  		   nod.appendChild(h4); 
  		   Text th4 = doc.createTextNode(l.font.getName());
  		   h4.appendChild(th4);
  		   Element h5 = doc.createElement("fontb");
		   nod.appendChild(h5); 
		   Text th5 = doc.createTextNode(Integer.toString(l.font.getStyle()));
		   h5.appendChild(th5);
		   Element h6 = doc.createElement("fontc");
  		   nod.appendChild(h6); 
  		   Text th6 = doc.createTextNode(Integer.toString(l.font.getSize()));
  		   h6.appendChild(th6);
  		   Element h7 = doc.createElement("colorx");
		   nod.appendChild(h7); 
		   Text th7 = doc.createTextNode(Integer.toString(l.color.getRed()));
		   h7.appendChild(th7);
		   Element h8 = doc.createElement("colory");
		   nod.appendChild(h8); 
		   Text th8 = doc.createTextNode(Integer.toString(l.color.getGreen()));
		   h8.appendChild(th8);
		   Element h9 = doc.createElement("colorz");
		   nod.appendChild(h9); 
		   Text th9 = doc.createTextNode(Integer.toString(l.color.getBlue()));
		   h9.appendChild(th9);
  		  }
  	  if (it.getClass().getSimpleName().equals("BioBrick")){
 		   BioBrick b = it.toBioBrick();
 		   // 创建一个节点
 		   Element nod = doc.createElement("Component");
 		   nod.setAttribute("name", "BioBrick");
 		   root.appendChild(nod);// 添加属性   
 		   // 创建文本姓名节点
 		   Element h1 = doc.createElement("secondaryType");
 		   nod.appendChild(h1);
 		   Text th1 = doc.createTextNode(Integer.toString(b.secondaryType));
 		   h1.appendChild(th1);
 		   Element h2 = doc.createElement("centerx");
 		   nod.appendChild(h2); 
 		   Text th2 = doc.createTextNode(Integer.toString(b.center.x));
 		   h2.appendChild(th2);
 		   Element h3 = doc.createElement("centery");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode(Integer.toString(b.center.y));
		   h3.appendChild(th3);
 		   Element h4 = doc.createElement("colorx");
		   nod.appendChild(h4); 
		   Text th4 = doc.createTextNode(Integer.toString(b.color.getRed()));
		   h4.appendChild(th4);
		   Element h5 = doc.createElement("colory");
		   nod.appendChild(h5); 
		   Text th5 = doc.createTextNode(Integer.toString(b.color.getGreen()));
		   h5.appendChild(th5);
		   Element h6 = doc.createElement("colorz");
		   nod.appendChild(h6); 
		   Text th6 = doc.createTextNode(Integer.toString(b.color.getBlue()));
		   h6.appendChild(th6);
		   Element h7 = doc.createElement("bbkName");
		   nod.appendChild(h7); 
		   Text th7 = doc.createTextNode(b.bbkOutline.name);
		   h7.appendChild(th7);
 		  }
  	if (it.getClass().getSimpleName().equals("BackBone")){
		   BackBone b = it.toBackBone();
		   // 创建一个节点
		   Element nod = doc.createElement("Component");
		   nod.setAttribute("name", "BackBone");
		   root.appendChild(nod);// 添加属性   
		   // 创建文本姓名节点
		   // modified by chyb due to the change in BackBone
		   Element h1 = doc.createElement("centerx");
		   nod.appendChild(h1);
		   Text th1 = doc.createTextNode(Integer.toString(b.center.x));
		   h1.appendChild(th1);
		   Element h2 = doc.createElement("centery");
		   nod.appendChild(h2); 
		   Text th2 = doc.createTextNode(Integer.toString(b.center.y));
		   h2.appendChild(th2);
		   Element h3 = doc.createElement("length");
		   nod.appendChild(h3);
		   Text th3 = doc.createTextNode(Integer.toString(b.length));
		   h3.appendChild(th3);
		   /*
		   Element h1 = doc.createElement("leftPointx");
		   nod.appendChild(h1);
		   Text th1 = doc.createTextNode("it.leftPoint.x");
		   h1.appendChild(th1);
		   Element h2 = doc.createElement("leftPointy");
		   nod.appendChild(h2); 
		   Text th2 = doc.createTextNode("it.leftPoint.y");
		   h2.appendChild(th2);
		   Element h3 = doc.createElement("rightPointx");
		   nod.appendChild(h3);
		   Text th3 = doc.createTextNode("it.rightPoint.x");
		   h3.appendChild(th3);
		   Element h4 = doc.createElement("rightPointy");
		   nod.appendChild(h4); 
		   Text th4 = doc.createTextNode("it.rightPoint.y");
		   h4.appendChild(th4);
			*/
		  }
  	if (it.getClass().getSimpleName().equals("BioVector")){
		   BioVector b = it.toBioVictor();
		   // 创建一个节点
		   Element nod = doc.createElement("Component");
		   nod.setAttribute("name", "BioVector");
		   root.appendChild(nod);// 添加属性   
		   // 创建文本姓名节点
		   Element h1 = doc.createElement("secondaryType");
 		   nod.appendChild(h1);
 		   Text th1 = doc.createTextNode(Integer.toString(b.secondaryType));
 		   h1.appendChild(th1);
 		   Element h2 = doc.createElement("centerx");
 		   nod.appendChild(h2); 
 		   Text th2 = doc.createTextNode(Integer.toString(b.center.x));
 		   h2.appendChild(th2);
 		   Element h3 = doc.createElement("centery");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode(Integer.toString(b.center.y));
		   h3.appendChild(th3);
		   Element h4 = doc.createElement("scale");
		   nod.appendChild(h4); 
		   Text th4 = doc.createTextNode(Double.toString(b.scale));
		   h4.appendChild(th4);
		  
		  }
  	if (it.getClass().getSimpleName().equals("Relation")){
		   Relation r = it.toRelation();
		   // 创建一个节点
		   Element nod = doc.createElement("Component");
		   nod.setAttribute("name", "Relation");
		   root.appendChild(nod);// 添加属性   
		   // 创建文本姓名节点
		   Element h1 = doc.createElement("secondaryType");
		   nod.appendChild(h1);
		   Text th1 = doc.createTextNode(Integer.toString(r.secondaryType));
		   h1.appendChild(th1);
		   Element h2 = doc.createElement("posListx");
		   nod.appendChild(h2); 
		   Text th2 = doc.createTextNode("it.posList.x");
		   h2.appendChild(th2);
		   Element h3 = doc.createElement("posListy");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode("it.posList.y");
		   h3.appendChild(th3);
		   Element h4 = doc.createElement("colorx");
		   nod.appendChild(h4); 
		   Text th4 = doc.createTextNode(Integer.toString(r.color.getRed()));
		   h4.appendChild(th4);
		   Element h5 = doc.createElement("colory");
		   nod.appendChild(h5); 
		   Text th5 = doc.createTextNode(Integer.toString(r.color.getGreen()));
		   h5.appendChild(th5);
		   Element h6 = doc.createElement("colorz");
		   nod.appendChild(h6); 
		   Text th6 = doc.createTextNode(Integer.toString(r.color.getBlue()));
		   h6.appendChild(th6);
		   Element h7 = doc.createElement("thickness");
		   nod.appendChild(h7); 
		   Text th7 = doc.createTextNode(Integer.toString(r.thickness));
		   h7.appendChild(th7);
		  
		  }
  	  	}
  	 
  	  try {
  		   FileOutputStream fos = new FileOutputStream(filePath);
  		   OutputStreamWriter outwriter = new OutputStreamWriter(fos);
  		   // ((XmlDocument)doc).write(outwriter); //出错！
  		   callWriteXmlFile(doc, outwriter, "gb2312");
  		   outwriter.close();
  		   fos.close();
  		  } catch (Exception e) {
  		   e.printStackTrace();
  		  }
  	  	
    
    }

    public ArrayList<Component> LoadFromFile (String filePath)throws Exception
    {   
    	ArrayList<Component> List = new ArrayList<Component>(); //用来储存所有的node节点
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.parse(filePath); // 获取到xml文件
    	// 下面开始读取
    	Element root = doc.getDocumentElement(); // 获取根元素
    	NodeList nodes = root.getElementsByTagName("Component");
    	 
    	for (int i = 0; i < nodes.getLength(); i++) {
        // 依次取得每一个节点
    		
    	Element ss = (Element) nodes.item(i);
    	System.out.println(ss.getAttribute("name"));
        // 创建一个节点的实例
    	System.out.println(List.size());
    	if (ss.getAttribute("name").equals("Label"))
    	{
    	 	
    		Point p = new Point();
    		p.x = Integer.parseInt(ss.getElementsByTagName("centerx").item(0).getFirstChild().getNodeValue());
    		p.y = Integer.parseInt(ss.getElementsByTagName("centery").item(0).getFirstChild().getNodeValue());
    		Font mf = new Font(ss.getElementsByTagName("fonta").item(0).getFirstChild().getNodeValue(),Integer.parseInt(ss.getElementsByTagName("fontb").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("fontc").item(0).getFirstChild().getNodeValue()));//字体，风格，字号
    		Color c = new Color(Integer.parseInt(ss.getElementsByTagName("colorx").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colory").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colorz").item(0).getFirstChild().getNodeValue()));//红绿蓝
    		Component temp = new Label(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),ss.getElementsByTagName("text").item(0).getFirstChild().getNodeValue(),p,mf,c);
    		List.add(temp);
    		
    	}
    	else if (ss.getAttribute("name").equals("BioBrick"))
    	{
    		Point p = new Point();
    		p.x = Integer.parseInt(ss.getElementsByTagName("centerx").item(0).getFirstChild().getNodeValue());
    		p.y = Integer.parseInt(ss.getElementsByTagName("centery").item(0).getFirstChild().getNodeValue());
    		Color c = new Color(Integer.parseInt(ss.getElementsByTagName("colorx").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colory").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colorz").item(0).getFirstChild().getNodeValue()));//红绿蓝
    		Component temp = new BioBrick(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),ss.getElementsByTagName("bbkName").item(0).getFirstChild().getNodeValue(), Integer.parseInt(ss.getElementsByTagName("secondaryType").item(0).getFirstChild().getNodeValue()),p,c);
    		List.add(temp);
    	}
    	else if (ss.getAttribute("name").equals("Protein"))
    	{
    		Point p = new Point();
    		p.x = Integer.parseInt(ss.getElementsByTagName("centerx").item(0).getFirstChild().getNodeValue());
    		p.y = Integer.parseInt(ss.getElementsByTagName("centery").item(0).getFirstChild().getNodeValue());
    		Color c = new Color(Integer.parseInt(ss.getElementsByTagName("colorx").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colory").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colorz").item(0).getFirstChild().getNodeValue()));//红绿蓝
    		Component temp = new Protein(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("secondaryType").item(0).getFirstChild().getNodeValue()),p,c);
    		List.add(temp);
    	}
    	else if (ss.getAttribute("name").equals("BackBone"))
    	{
    		Point p1 = new Point();
    		//Point p2 = new Point();
    		p1.x = Integer.parseInt(ss.getElementsByTagName("centerx").item(0).getFirstChild().getNodeValue());
    		p1.y = Integer.parseInt(ss.getElementsByTagName("centery").item(0).getFirstChild().getNodeValue());
    		int length = Integer.parseInt(ss.getElementsByTagName("length").item(0).getFirstChild().getNodeValue());
    		//p2.x = Integer.parseInt(ss.getElementsByTagName("rightPointx").item(0).getFirstChild().getNodeValue());
    		//p2.y = Integer.parseInt(ss.getElementsByTagName("rightPointy").item(0).getFirstChild().getNodeValue());
    		Component temp = new BackBone(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),p1,length, null);
    		List.add(temp);
    	}
    	else if (ss.getAttribute("name").equals("Relation"))
    	{
    		Color c = new Color(Integer.parseInt(ss.getElementsByTagName("colorx").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colory").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("colorz").item(0).getFirstChild().getNodeValue()));
    		String a = ss.getElementsByTagName("posListx").item(0).getFirstChild().getNodeValue().toString();
    		String b = ss.getElementsByTagName("posListy").item(0).getFirstChild().getNodeValue().toString();
    		ArrayList<Point> h = new ArrayList<Point>();
    		a=a.trim();
    		
            String[] aa=a.split("\\s+");
            String[] bb=b.split("\\s+");
            
            for(int j=0;j<aa.length;j++){
            	Point p1 = new Point();
                p1.x = Integer.parseInt(aa[j]);
                p1.y = Integer.parseInt(bb[j]);
                h.add(p1);
            }
            Component temp = new Relation(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("secondaryType").item(0).getFirstChild().getNodeValue()),h,c,Integer.parseInt(ss.getElementsByTagName("thickness").item(0).getFirstChild().getNodeValue()));
            List.add(temp);
    	}
    	else if (ss.getAttribute("name").equals("BioVector"))
    	{
    		Point p = new Point();
    		p.x = Integer.parseInt(ss.getElementsByTagName("centerx").item(0).getFirstChild().getNodeValue());
    		p.y = Integer.parseInt(ss.getElementsByTagName("centery").item(0).getFirstChild().getNodeValue());
    		Component temp = new BioVector (Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("secondaryType").item(0).getFirstChild().getNodeValue()),p,Double.valueOf(ss.getElementsByTagName("scale").item(0).getFirstChild().getNodeValue()));
    		List.add(temp);
    	}
    	}
    	return List;
    }
    
}
