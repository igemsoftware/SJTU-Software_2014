package data_center;

import java.util.ArrayList;

import data_center.SketchComponent.Component;

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

    public void SaveIntoFile(String filePath)
    {   
		// fix me
    }

    public void LoadFromFile(String filePath)
    {   
		// fix me
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
