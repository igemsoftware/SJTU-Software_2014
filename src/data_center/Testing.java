package data_center;

import java.awt.Color;
import java.awt.Font;

import data_center.SketchComponent.*;

public class Testing
{	
	public static void main(String[] args)
	{	
		/*
		ArrayList<Component> List2 = null;
		String str = "saved graph.xml";
		SketchProject t = new SketchProject();
		try {
			List2 = t.LoadFromFile(str);
		} catch (Exception e) {
		e.printStackTrace();
		}
		String outfile = "º«½¨»ªµÄXML.xml";
		t.SaveIntoFile(outfile,List2);
	`*/
		
		Component component = null;//new Label(0, null, null, null, null);
		
		Font font = new Font(null, 0, 0);
		Color color = new Color(23, 43, 109);
		color = new Color(-15258771);
		System.out.println(color.getRGB());
		
	}
}
