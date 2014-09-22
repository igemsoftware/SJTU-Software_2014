package data_center;

import java.util.ArrayList;

public class SketchCenter
{
	//SketchProject currentProject = null;
	ArrayList<SketchProject> projectList = new ArrayList<SketchProject>();
	
	public SketchProject newProject()
	{	
		return new SketchProject(generateProjectName());
	}
	
	public void closeProject(String projectName)
	{	
		SketchProject project = findProjectByName(projectName);
		projectList.remove(project);
	}
	
	public void saveProject(SketchProject project, String filePath)
	{	
		if (project == null)
			return;
		project.saveIntoFile(filePath);
	}
	
	public SketchProject loadProject(String filePath)
	{	
		SketchProject project = new SketchProject(null);
		project.loadFromFile(filePath);	// project.name set in this function
		return project;
	}
	
	public SketchProject findProjectByName(String projectName)
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
