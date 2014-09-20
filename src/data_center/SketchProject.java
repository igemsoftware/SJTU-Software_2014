package data_center;

import java.util.ArrayList;

import data_center.SketchComponent.*;
import data_center.SketchComponent.Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
		public final static int ADD = 1;
		public final static int REMOVE = 2;
		public final static int REPLACE = 3;

		// attributes
		public Component previous = null;
		public int operationType;
		public Component following = null;
		
		public Operation(Component pre, Component fol)
		{
			previous = pre;
			following = fol;
			if (pre == null && fol == null)
				System.out.println("Meaningless operation... ");
			else if (pre == null)
				operationType = Operation.ADD;
			else if (fol == null)
				operationType = Operation.REMOVE;
			else // pre, fol != null
				operationType = Operation.REPLACE;
		}

		public Operation generateReversedOp()
		{
			return new Operation(this.following, this.previous);
		}
	}

	
	public final static int EXPORT_JPG = 0;
    public final static int EXPORT_BMP = 1;
    public final static int EXPORT_PNG = 2;
    public final static int EXPORT_GIF = 3;

	// attributes
	public ArrayList<Component> componentList;
	public HistoryList<Operation> operationHistory;

	public boolean modified = false;
	

    public SketchProject()
    {
		componentList = new ArrayList<Component>();
		operationHistory = new HistoryList<Operation>();
    }

	public Component FindComponentByID(int ID)
	{
		for (Component comp : componentList)
			if (comp.ID == ID)
				return comp;
		// not found
		return null;
	}

	public void AddComponent(Component component)
	{
		if (component != null)
		{
			componentList.add(component);
			operationHistory.putInItem(new Operation(null, component));
			modified = true;
		}
	}

	public void DelComponent(Component component)
	{
		if (component != null)
		{
			componentList.remove(component);
			operationHistory.putInItem(new Operation(component, null));
			modified = true;
		}
	}

	public void ModifyComponent(Component pre, Component fol)
	{
		if (pre != null && fol != null)
		{
			componentList.remove(pre);
			componentList.add(fol);
			operationHistory.putInItem(new Operation(pre, fol));
			modified = true;
		}
	}

	public Operation CtrlZ()
	{
		Operation originalOperation = operationHistory.rollBack();
		if (originalOperation == null)
			return null;

		// else... 
		componentList.add(originalOperation.following);
		// previous can be null, if is, nothing to be done
		componentList.remove(originalOperation.previous);
		modified = true;
		return originalOperation.generateReversedOp();
	}

	public Operation CtrlY()
	{
		Operation originalOperation = operationHistory.goForward();
		if (originalOperation == null)
			return null;

		// else... 
		componentList.add(originalOperation.previous);
		// previous can be null, if is, nothing to be done
		componentList.remove(originalOperation.following);
		modified = true;
		return originalOperation;
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
  		   
  		   // 创建一个节点
  		   Element nod = doc.createElement("Component");
  		   nod.setAttribute("name", "Label");
  		   root.appendChild(nod);// 添加属性   
  		   // 创建文本姓名节点
  		   Element h1 = doc.createElement("text");
  		   nod.appendChild(h1);
  		   Text th1 = doc.createTextNode("it.text");
  		   h1.appendChild(th1);
  		   Element h2 = doc.createElement("centerx");
  		   nod.appendChild(h2); 
  		   Text th2 = doc.createTextNode("it.center.x");
  		   h2.appendChild(th2);
  		   Element h3 = doc.createElement("centery");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode("it.center.y");
		   h3.appendChild(th3);
		   Element h4 = doc.createElement("fonta");
  		   nod.appendChild(h4); 
  		   Text th4 = doc.createTextNode("123");
  		   h4.appendChild(th4);
  		   Element h5 = doc.createElement("fontb");
		   nod.appendChild(h5); 
		   Text th5 = doc.createTextNode("123");
		   h5.appendChild(th5);
		   Element h6 = doc.createElement("fontc");
  		   nod.appendChild(h6); 
  		   Text th6 = doc.createTextNode("123");
  		   h6.appendChild(th6);
  		   Element h7 = doc.createElement("colorx");
		   nod.appendChild(h7); 
		   Text th7 = doc.createTextNode("it.blue");
		   h7.appendChild(th7);
		   Element h8 = doc.createElement("colory");
		   nod.appendChild(h8); 
		   Text th8 = doc.createTextNode("it.green");
		   h8.appendChild(th8);
		   Element h9 = doc.createElement("colorz");
		   nod.appendChild(h9); 
		   Text th9 = doc.createTextNode("it.red");
		   h9.appendChild(th9);
  		  }
  	  if (it.getClass().getSimpleName().equals("BioBrick")){
 		   
 		   // 创建一个节点
 		   Element nod = doc.createElement("Component");
 		   nod.setAttribute("name", "BioBrick");
 		   root.appendChild(nod);// 添加属性   
 		   // 创建文本姓名节点
 		   Element h1 = doc.createElement("secondaryType");
 		   nod.appendChild(h1);
 		   Text th1 = doc.createTextNode("it.secondaryType");
 		   h1.appendChild(th1);
 		   Element h2 = doc.createElement("centerx");
 		   nod.appendChild(h2); 
 		   Text th2 = doc.createTextNode("it.center.x");
 		   h2.appendChild(th2);
 		   Element h3 = doc.createElement("centery");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode("it.center.y");
		   h3.appendChild(th3);
 		   Element h4 = doc.createElement("colorx");
		   nod.appendChild(h4); 
		   Text th4 = doc.createTextNode("it.blue");
		   h4.appendChild(th4);
		   Element h5 = doc.createElement("colory");
		   nod.appendChild(h5); 
		   Text th5 = doc.createTextNode("it.green");
		   h5.appendChild(th5);
		   Element h6 = doc.createElement("colorz");
		   nod.appendChild(h6); 
		   Text th6 = doc.createTextNode("it.red");
		   h6.appendChild(th6);
 		  }
  	if (it.getClass().getSimpleName().equals("BackBone")){
		   
		   // 创建一个节点
		   Element nod = doc.createElement("Component");
		   nod.setAttribute("name", "BackBone");
		   root.appendChild(nod);// 添加属性   
		   // 创建文本姓名节点
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
		  
		  }
  	if (it.getClass().getSimpleName().equals("BioVector")){
		   
		   // 创建一个节点
		   Element nod = doc.createElement("Component");
		   nod.setAttribute("name", "BioVector");
		   root.appendChild(nod);// 添加属性   
		   // 创建文本姓名节点
		   Element h1 = doc.createElement("secondaryType");
 		   nod.appendChild(h1);
 		   Text th1 = doc.createTextNode("it.secondaryType");
 		   h1.appendChild(th1);
 		   Element h2 = doc.createElement("centerx");
 		   nod.appendChild(h2); 
 		   Text th2 = doc.createTextNode("it.center.x");
 		   h2.appendChild(th2);
 		   Element h3 = doc.createElement("centery");
		   nod.appendChild(h3); 
		   Text th3 = doc.createTextNode("it.center.y");
		   h3.appendChild(th3);
		   Element h4 = doc.createElement("scale");
		   nod.appendChild(h4); 
		   Text th4 = doc.createTextNode("it.scale");
		   h4.appendChild(th4);
		  
		  }
  	if (it.getClass().getSimpleName().equals("Relation")){
		   
		   // 创建一个节点
		   Element nod = doc.createElement("Component");
		   nod.setAttribute("name", "Relation");
		   root.appendChild(nod);// 添加属性   
		   // 创建文本姓名节点
		   Element h1 = doc.createElement("secondaryType");
		   nod.appendChild(h1);
		   Text th1 = doc.createTextNode("it.secondaryType");
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
		   Text th4 = doc.createTextNode("it.blue");
		   h4.appendChild(th4);
		   Element h5 = doc.createElement("colory");
		   nod.appendChild(h5); 
		   Text th5 = doc.createTextNode("it.green");
		   h5.appendChild(th5);
		   Element h6 = doc.createElement("colorz");
		   nod.appendChild(h6); 
		   Text th6 = doc.createTextNode("it.red");
		   h6.appendChild(th6);
		   Element h7 = doc.createElement("thickness");
		   nod.appendChild(h7); 
		   Text th7 = doc.createTextNode("it.thickness");
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
    		Component temp = new BioBrick(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),Integer.parseInt(ss.getElementsByTagName("secondaryType").item(0).getFirstChild().getNodeValue()),p,c);
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
    		Point p2 = new Point();
    		p1.x = Integer.parseInt(ss.getElementsByTagName("leftPointx").item(0).getFirstChild().getNodeValue());
    		p1.y = Integer.parseInt(ss.getElementsByTagName("leftPointy").item(0).getFirstChild().getNodeValue());
    		p2.x = Integer.parseInt(ss.getElementsByTagName("rightPointx").item(0).getFirstChild().getNodeValue());
    		p2.y = Integer.parseInt(ss.getElementsByTagName("rightPointy").item(0).getFirstChild().getNodeValue());
    		Component temp = new BackBone(Integer.parseInt(ss.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue()),p1,p2);
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

    public void ExportIntoPicture(int exportFormat)
    {   
        // fix me
		switch (exportFormat)
        {   case EXPORT_JPG:

                break;
            case EXPORT_BMP:

                break;
            case EXPORT_PNG:

                break;
            case EXPORT_GIF:

                break;
        }
    }
}
