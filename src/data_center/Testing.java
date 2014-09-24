package data_center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import data_center.SketchComponent.*;

public class Testing
{	
	public static void main(String[] args) throws Exception
	{	
		/*
		ArrayList<Point> curve = new ArrayList<Point>();
		curve.add(new Point(11, 11));	curve.add(new Point(22, 22));
		curve.add(new Point(33, 33));	curve.add(new Point(44, 44));
		
		ArrayList<Component> componentList = new ArrayList<Component>();
		componentList.add(new Label(0, "Lable text", 
				new Point(5, 5), new Font("Times Roman", 10, 3), new Color(0, 0, 0)));
		componentList.add(new BioBrick(1, "Bba_B0034", BbkType.Sketch.BioBrick.PROMOTER, 
				new Point(10, 10), null));
		componentList.add(new Protein(2, BbkType.Sketch.Protein.FACTOR, 
				new Point(20, 20), Color.BLUE));
		componentList.add(new BackBone(3, new Point(50, 50), 50));
		componentList.add(new Relation(4, BbkType.Sketch.Relation.SUPPRESS, 
				curve, new Color(50, 50, 50), 10));
		componentList.add(new BioVector(5, BbkType.Sketch.Vector.BACTERIA, 
				new Point(300, 300), 3));
		
		SketchProject project = new SketchProject("SketchProject1");
		project.componentList = componentList;
		project.saveIntoFile("testXML.xml");
		
		project.loadFromFile("testXML.xml");
		project.displayComponents();
		*/
		
		BbkDatabaseConnector.getDetailByName("BBa_B0012").display();
		
	}
}
