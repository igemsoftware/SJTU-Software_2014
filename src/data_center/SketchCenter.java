package data_center;

import java.util.ArrayList;

import data_center.SketchComponent.BackBone;
import data_center.SketchComponent.BioBrick;

/** This class contains all of the operations of the sketch project. Further operation
 * inside a project can be accessed by the reference of a SketchProject instance. 
 * Please note that the new, save, load all aim at the current project, which need no
 * specification of the project name of its reference. To operate on another project? 
 * you may use the switchCurrentProjectTo() function.  */
public class SketchCenter
{
	public SketchProject currentProject = null;
	public ArrayList<SketchProject> projectList = new ArrayList<SketchProject>();
	
	/** Switch current project to a new one to execute new, save, load 
	 * operation on it.  */
	public SketchProject switchCurrentProjectTo(String projectName)
	{	
		SketchProject project = findProjectByName(projectName);
		if (project == null)	// 404
			return null;
		else
		{	currentProject = project;
			return currentProject;
		}
	}
	
	public SketchProject newProject()
	{	
		currentProject = new SketchProject(generateProjectName());
		projectList.add(currentProject);
		return currentProject;
	}
	
	/** Close a project which can be either current project or not. Pass a null
	 * to represent current project.  */
	public void closeProject(String projectName)
	{	
		SketchProject project = null;
		if (projectName == null || projectName.equals(currentProject.name))
		{	project = currentProject;
			projectList.remove(project);
			currentProject = projectList.size() != 0 ? projectList.get(0) : null;
		}
		else
		{	project = findProjectByName(projectName);
			projectList.remove(project);
		}
		
	}
	
	/** Save the CURRENT project */
	public void saveProject(String filePath)
	{	
		if (currentProject == null)
			return;
		currentProject.saveIntoFile(filePath);
	}
	
	/** Load a project that will OVERWRITE CURRENT project */
	public SketchProject loadProject(String filePath)
	{	
		currentProject.loadFromFile(filePath);	// project.name set in this function
		return currentProject;
	}
	
	public String[] getAllProjectNames()
	{	
		String[] names = new String[projectList.size()];
		for (int i = 0; i < projectList.size(); ++i)
			names[i] = projectList.get(i).name;
		return names;
	}
	
	/** Assign a BbkOutline to a SketchComponent.BioBrick, specified by part_name. 
	 * If the database doesn't have such name, a null will be returned.  */
	public BbkOutline assignBbkOutlineToBioBrick(String bbkName, BioBrick biobrick)
	{	
		biobrick.setString(bbkName); 	// BbkDatabase connected in the function
		return biobrick.bbkOutline;
	}
	
	/** Upload the newly designed biobrick to the database by SJTU-software! This 
	 * function generates a BbkUpload instance to start this process. This function
	 * is aimed to simplify the upload process and as the interaction between the 4
	 * functions of the software.  */
	public BbkUpload generateBbkUploadFromBackBone(BackBone backbone, boolean findTwins)
	{	
		BbkUpload bbkUpload = new BbkUpload();
		int startPos = 1, endPos = 0;
		for (Integer componentID : backbone.bbkChildren)
		{	BioBrick bbk = (BioBrick) currentProject.findComponentByID(componentID);
			if (bbk == null)
				continue;
			// else... 
			startPos = endPos + 1;
			BbkDetail bbkDetail = 
					DatabaseConnector.getDetailByName(bbk.bbkOutline.name);
			endPos = startPos + bbkDetail.sequence.length() - 1;
			bbkUpload.sequenceTokens.add(bbkDetail);
			bbkUpload.features.add(new BbkUpload.Feature
					("", bbkDetail.name, bbkDetail.type, "Fwd", 
							Integer.toString(startPos), Integer.toString(endPos)));
			fillFeatureFromSubpartToBbkUpload(bbkUpload, bbkDetail, startPos - 1);
		}
		bbkUpload.setSequence(false, findTwins);
		return bbkUpload;
	}
	
	/** Check if the sequence string is only consist if base group, not other chars.  */
	public boolean isSequanceValid(String sequenceToken)
	{	
		sequenceToken = sequenceToken.toLowerCase();
		for (int i = 0; i < sequenceToken.length(); ++i)
		{	char ch = sequenceToken.charAt(i);
			if (ch != 'a' && ch != 't' && ch != 'c' && ch != 'g')
				return false;
		}
		return true;
	}
	
	
	
	
	private void fillFeatureFromSubpartToBbkUpload
		(BbkUpload bbkUpload, BbkDetail bbkDetail, int offset)
	{	
		for (BbkDetail.Feature feature : bbkDetail.features)
		{	if (feature.endPos.endsWith("\r"))	// every line in the database ends with "\r"
				feature.endPos = feature.endPos.substring(0, feature.endPos.length() - 1);
			int startPos = Integer.parseInt(feature.startPos) + offset;
			int endPos = Integer.parseInt(feature.endPos) + offset;
			bbkUpload.features.add(new BbkUpload.Feature
					(feature.ID, feature.title, feature.type, feature.direction, 
							Integer.toString(startPos), Integer.toString(endPos)));
		}
	}
	
	private SketchProject findProjectByName(String projectName)
	{	
		for (SketchProject project : projectList)
			if (projectName.equals(project.name))
				return project;
		return null;
	}
	
	private String generateProjectName()
	{	
		boolean toContinue = true;
		String projectName = null;
		int serialNo = 0;
		while (toContinue)
		{	++serialNo;
			projectName = "SketchProject" + serialNo;
			toContinue = false;
			for (SketchProject project : projectList)
				if (projectName.equals(project.name))
				{	toContinue = true;
					break;
				}
		}
		return projectName;
	}
}
