package data_center;

import java.util.ArrayList;

public class SketchCenter
{
	public SketchProject currentProject = null;
	public ArrayList<SketchProject> projectList = new ArrayList<SketchProject>();
	
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
	
	public void closeProject(String projectName)
	{	
		SketchProject project = findProjectByName(projectName);
		projectList.remove(project);
		currentProject = projectList.size() != 0 ? projectList.get(0) : null;
	}
	
	/** save the CURRENT project */
	public void saveProject(String filePath)
	{	
		if (currentProject == null)
			return;
		currentProject.saveIntoFile(filePath);
	}
	
	/** load a project that will OVERWRITE CURRENT project */
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
