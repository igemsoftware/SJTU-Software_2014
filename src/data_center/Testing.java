package data_center;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data_center.BbkUpload.Category;
import data_center.SketchComponent.*;

/** The class used to test the data_center. Each private function tests a function in
 * data_center. You may modify this file directly to perform a customized testing.  */
@SuppressWarnings("unused")
class Testing
{	
	static DataCenter dataCenter = new DataCenter();
	
	public static void main(String[] args) throws Exception
	{	
		
		

		
		/*
		searchKeywordAndGetDetail();
		searchFilterAndSort();
		searchBlasting();
		searchHistory();
		compareAssignDetail();
		sketchProjectOperation();
		sketchXMLReadWrite();
		sketchOperationHistory();
		uploadUploadAndReappearBbkUpload();
		uploadPartNameSequenceTokenValidationCheck();
		uploadSubpartSubscarValidationCheck();
		uploadOfficialUploadPost();
		*/
	}
	
	
	
	
	private static void searchKeywordAndGetDetail()
	{	
		int startTime = getTimeInMs();
		SearchResultList list = dataCenter.searchCenter.search("GFP");
		list.sortByTotalScore(true);
		list.displayRating();
		int endTime = getTimeInMs();
		
		System.out.println("Network time: " + (endTime - startTime) + "ms");
		System.out.println("List size: " + list.size());
		
		for (int i = 0; i < list.size(); i += 50)
		{	BbkDetail detail = dataCenter.searchCenter.getDetail(list.get(i).name);
			detail.display();
		}
	}
	
	private static void searchFilterAndSort()
	{	
		BbkOutline bbkOutline = DatabaseConnector.getOutlineByName("BBa_B0034");
		bbkOutline.displayFilteringConditions();
		
		SearchResultList rawList = dataCenter.searchCenter.search("BBa_B0034");
		System.out.println("\n\nFilter by the conditions that fits BBa_B0034: ");
		rawList.filterByDeletedOrNot(false)
			.filterByDNAStatus(SearchResultList.Filter.DNAStatus.AVAILABLE)
			.filterByEnterYear(new int[]{2003, 2013})
			.filterByRelaseStatus(SearchResultList.Filter.ReleaseStatus.RELEASED)
			.displayFilteringConditions();
		
		System.out.println("\n\nSort by enter date: ");
		rawList.sortByEnterDate(true);	rawList.displaySortingConditions();
		System.out.println("\n\nSort by google items: ");
		rawList.sortByGoogleQuoteNum(true);	rawList.displaySortingConditions();
		System.out.println("\n\nSort by star num: ");
		rawList.sortByAverageStars(true);	rawList.displaySortingConditions();
		System.out.println("\n\nSort by confirm: ");
		rawList.sortByConfrimedTimes(true);	rawList.displaySortingConditions();
		System.out.println("\n\nSort by total score: ");
		rawList.sortByTotalScore(true);	rawList.displaySortingConditions();
	}
	
	private static void searchBlasting()
	{	
		SearchResultList list; 
		list = dataCenter.searchCenter.blast
			("blastInput", SearchCenter.BLAST_MODE_INPUT_FILE_PATH);
		list.sortByBlastResult(true);	list.displaySortingConditions();
		System.out.println("Item num: " + list.size());
		
		list = dataCenter.searchCenter.blast
			("tcaaataaaacgaaaggctcagtcg", 
			 SearchCenter.BLAST_MODE_INPUT_SEQUENCE);
		list.sortByBlastResult(true);	list.displaySortingConditions();
		System.out.println("Item num: " + list.size());
		BlastingSearcher.deleteLocalCacheFiles();
	}
	
	private static void searchHistory()
	{	
		System.out.println("The search history can roll back: " 
				+ dataCenter.searchCenter.canRollBack());
		System.out.println("\t\t can go forward: "
				+ dataCenter.searchCenter.canGoForward());
		
		dataCenter.searchCenter.search("BBa_B0012");
		dataCenter.searchCenter.search("BBa_B0011");
		dataCenter.searchCenter.search("GFP BBa_B");
		System.out.println("Searched 3 times, current list... ");
		dataCenter.searchCenter.getCurrentRawSearchResultList().display();
		
		System.out.println("The search history can roll back: " 
				+ dataCenter.searchCenter.canRollBack());
		System.out.println("\t\t can go forward: "
				+ dataCenter.searchCenter.canGoForward());
		System.out.println("Roll back... ");
		dataCenter.searchCenter.rollBack().display();
		System.out.println("Roll back... ");
		dataCenter.searchCenter.rollBack().display();
		System.out.println("Go forward... ");
		dataCenter.searchCenter.goForward().display();
		System.out.println("Go forward... ");
		dataCenter.searchCenter.goForward().display();
		
		System.out.println("Search again... ");
		dataCenter.searchCenter.search("BBa_B0034");
		dataCenter.searchCenter.getCurrentRawSearchResultList().display();
		System.out.println("The search history can go forward: " 
				+ dataCenter.searchCenter.canGoForward());
	}
	
	private static void compareAssignDetail()
	{	
		int startTime = getTimeInMs();
		dataCenter.compareCenter.assignDetail("BBa_I13545", 2);
		dataCenter.compareCenter.getDetail(2).display();
		int endTime = getTimeInMs();
		
		System.out.println("Network time: " + (endTime - startTime) + "ms");
	}
	
	private static void sketchProjectOperation()
	{	
		dataCenter.sketchCenter.newProject();
		dataCenter.sketchCenter.newProject();
		dataCenter.sketchCenter.newProject();
		dataCenter.sketchCenter.newProject();
		
		String[] projectNames = dataCenter.sketchCenter.getAllProjectNames();
		for (String name : projectNames)
			System.out.println("Project name: " + name);
		
		System.out.println("Current: " + dataCenter.sketchCenter.currentProject.name);
		
		System.out.println("Closing project1... ");
		dataCenter.sketchCenter.closeProject(projectNames[0]);
		System.out.println("Current: " + dataCenter.sketchCenter.currentProject.name);
		System.out.println("Closing current... ");
		dataCenter.sketchCenter.closeProject(null);
		System.out.println("Current: " + dataCenter.sketchCenter.currentProject.name);
		System.out.println("Closing current... ");
		dataCenter.sketchCenter.closeProject(null);
		System.out.println("Current: " + dataCenter.sketchCenter.currentProject.name);
		dataCenter.sketchCenter.closeProject(null);
	}
	
	private static void sketchXMLReadWrite()
	{	
		ArrayList<Point> curve = new ArrayList<Point>();
		curve.add(new Point(11, 11));	curve.add(new Point(22, 22));
		curve.add(new Point(33, 33));	curve.add(new Point(44, 44));
		ArrayList<Integer> backBoneChildren = new ArrayList<Integer>();
		backBoneChildren.add(1);	backBoneChildren.add(6);
		
		SketchProject project = dataCenter.sketchCenter.newProject();
		System.out.println("Auto generated project name: " + project.name);
		
		project.addComponent(new Label(0, "", 
				new Rectangle(5, 5, 10, 10), new Font("Times Roman", 10, 3), new Color(0, 0, 0)));
		project.addComponent(new BioBrick(1, null, BbkType.Sketch.BioBrick.PROMOTER, 
				new Rectangle(10, 10, 10, 10), null));
		project.addComponent(new BioBrick(6, null, BbkType.Sketch.BioBrick.PROMOTER, 
				new Rectangle(10, 50, 10, 10), null));
		project.addComponent(new Protein(2, BbkType.Sketch.Protein.FACTOR, 
				new Rectangle(20, 20, 5, 5), Color.BLUE));
		project.addComponent(new BackBone(3, new Rectangle(50, 50, 50, 5), backBoneChildren));
		project.addComponent(new Relation(4, BbkType.Sketch.Relation.SUPPRESS, 
				new Rectangle(50, 50, 50, 5), curve, new Color(50, 50, 50), 10));
		project.addComponent(new BioVector(5, BbkType.Sketch.BioVector.BACTERIA, 
				new Rectangle(300, 300, 100, 100)));
		
		project.saveIntoFile("testXML.xml");
		project.loadFromFile("testXML.xml");
		
		project.displayComponents();
	}
	
	private static void sketchOperationHistory()
	{	
		SketchProject project = dataCenter.sketchCenter.newProject();
		System.out.println("Auto generated project name: " + project.name);
		
		project.addComponent(new Label(0, "", 
				new Rectangle(5, 5, 10, 10), new Font("Times Roman", 10, 3), new Color(0, 0, 0)));
		project.addComponent(new BioBrick(1, null, BbkType.Sketch.BioBrick.PROMOTER, 
				new Rectangle(10, 10, 10, 10), null));
		project.addComponent(new Protein(2, BbkType.Sketch.Protein.FACTOR, 
				new Rectangle(20, 20, 5, 5), Color.BLUE));
		project.modifyComponent(0, SketchOperation.TYPE_STRING, "text");
		
		project.ctrlZ();
		project.ctrlZ();
		project.ctrlZ();
		System.out.println("CtrlZ for 3 times, the history list: ");
		project.displayOperationHistory();
		
		project.ctrlY();
		project.ctrlY();
		project.ctrlY();
		System.out.println("CtrlY for 3 times, the history list: ");
		project.displayOperationHistory();
		
	}
	
	private static void uploadUploadAndReappearBbkUpload()
	{	
		BbkUpload bbkUpload = new BbkUpload();
		bbkUpload.setName("K1479001");
		bbkUpload.setID();
		dataCenter.uploadCenter.uploadAndGetOddNum(bbkUpload);
		DatabaseConnector.displayTable(Consts_DB.Table.MAIN_UPLOAD, 2);
		dataCenter.uploadCenter.getBbkUploadByNameAndOddNum
			("BBa_K1479001_EasyBbk", "201410115566901").display();
	}
	
	private static void uploadPartNameSequenceTokenValidationCheck()
	{	
		System.out.println("Name validation(K1479001): " + 
				dataCenter.uploadCenter.isBbkNameNotOccupied("K1479001"));
		System.out.println("Name validation(K1479010): " + 
				dataCenter.uploadCenter.isBbkNameNotOccupied("K1479010"));
		System.out.println("Sequence validation(atctgctagctgafacgt): " + 
				dataCenter.uploadCenter.isSequanceValid("atctgctagctgafacgt"));
		System.out.println("Sequence validation(atctgctagctgacacgt): " + 
				dataCenter.uploadCenter.isSequanceValid("atctgctagctgacacgt"));
	}
	
	private static void uploadSubpartSubscarValidationCheck()
	{	
		System.out.println("Subpart validation(BBa_I13545): " + 
			(dataCenter.uploadCenter.getSubpartForSequenceToken("BBa_I13545") != null));
		System.out.println("Subpart validation(BBa_K1479010): " + 
			(dataCenter.uploadCenter.getSubpartForSequenceToken("BBa_K1479010") != null));
		System.out.println("Subpart validation(RFC[10]): " + 
			(dataCenter.uploadCenter.getSubscarForSequenceToken("RFC[10]") != null));
		System.out.println("Subpart validation(RFC[1000]): " + 
			(dataCenter.uploadCenter.getSubscarForSequenceToken("RFC[1000]") != null));
	}
	
	private static void uploadOfficialUploadPost()
	{	
		BbkUpload bbkUpload = new BbkUpload();
		bbkUpload.sequenceTokens.add(dataCenter.uploadCenter.getSubpartForSequenceToken("BBa_B0034"));
		bbkUpload.sequenceTokens.add(dataCenter.uploadCenter.getSubscarForSequenceToken("RFC[10]"));
		bbkUpload.setSequence(true, false);
		try {
			System.out.println("Login... Succeed: " + RegistryUploader.login
				(Server.iGemChyb.USER_NAME, Server.iGemChyb.PASSWORD));
			System.out.println("Next avaliable part name: " + 
				RegistryUploader.getNextAvailablePartName());
			String[] region = RegistryUploader.getAvailablePartNameRegion();
			System.out.println("Avaliable region: " + region[0] + " to " + region[1]);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	static int getTimeInMs()
	{	
		Calendar calendar = Calendar.getInstance();
		int sec = calendar.get(Calendar.SECOND);
		int mSec = calendar.get(Calendar.MILLISECOND);
		return 1000 * sec + mSec;
	}
	
}
