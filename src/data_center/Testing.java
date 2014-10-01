package data_center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data_center.SketchComponent.*;

@SuppressWarnings("unused")
public class Testing
{	
	static DataCenter dataCenter = new DataCenter();
	
	public static void main(String[] args) throws Exception
	{	
		searchKeywordAndGetDetail();
		//compareAssignDetail();
		//sketchXMLReadWrite();
		//BbkDatabaseConnector.displayTable(BbkDB.TABLE_TWINS, 2);
	}
	
	
	
	
	private static void searchKeywordAndGetDetail()
	{	
		SearchResultList list = dataCenter.searchCenter.search("GFP");
		list.display();
		System.out.println("List size: " + list.size());
		for (int i = 0; i < list.size(); i += 10)
		{	BbkDetail detail = dataCenter.searchCenter.getDetail(list.get(i).name);
			detail.display();
		}
	}
	
	private static void searchFilterAndSort()
	{	
		// fix me
	}
	
	private static void searchHistory()
	{	
		// fix me
	}
	
	private static void compareAssignDetail()
	{	
		dataCenter.compareCenter.assignDetail("BBa_B1005", 2).display();
	}
	
	private static void sketchXMLReadWrite()
	{	
		ArrayList<Point> curve = new ArrayList<Point>();
		curve.add(new Point(11, 11));	curve.add(new Point(22, 22));
		curve.add(new Point(33, 33));	curve.add(new Point(44, 44));
		ArrayList<Integer> backBoneChildren = new ArrayList<Integer>();
		backBoneChildren.add(1);	backBoneChildren.add(6);
		
		ArrayList<Component> componentList = new ArrayList<Component>();
		componentList.add(new Label(0, "Lable text", 
				new Point(5, 5), new Font("Times Roman", 10, 3), new Color(0, 0, 0)));
		componentList.add(new BioBrick(1, "Bba_B0034", BbkType.Sketch.BioBrick.PROMOTER, 
				new Point(10, 10), null));
		componentList.add(new BioBrick(1, "Bba_B0012", BbkType.Sketch.BioBrick.PROMOTER, 
				new Point(10, 10), null));
		componentList.add(new Protein(2, BbkType.Sketch.Protein.FACTOR, 
				new Point(20, 20), Color.BLUE));
		componentList.add(new BackBone(3, new Point(50, 50), 50, backBoneChildren));
		componentList.add(new Relation(4, BbkType.Sketch.Relation.SUPPRESS, 
				curve, new Color(50, 50, 50), 10));
		componentList.add(new BioVector(5, BbkType.Sketch.Vector.BACTERIA, 
				new Point(300, 300), 3));
		
		SketchProject project = dataCenter.sketchCenter.newProject();
		System.out.println("Auto generated project name: " + project.name);
		project.componentList = componentList;
		
		project.saveIntoFile("testXML.xml");
		project.loadFromFile("testXML.xml");
		
		project.displayComponents();
	}
	
	
	
}
