package data_center;

import java.util.Stack;

/** The class implements a history list contains roll back and go forward function. 
 * Different from the SearchHistory, the OperationHistory aims the pointer at the
 * nick between data, not the data, like [H H H¡ýF F] (history, pointer,  future) */
class OperationHistory
{
	Stack<SketchOperation> history = new Stack<SketchOperation>();
	Stack<SketchOperation> future = new Stack<SketchOperation>();
	
	public void newOperation(SketchOperation operation)
	{	
		future.clear();
		history.push(operation);
	}
	
	public boolean canCtrlZ()
	{	
		return !history.empty();
	}
	
	public boolean canCtrlY()
	{	
		return !future.empty();
	}
	
	public SketchOperation ctrlZ()
	{	
		if (history.empty())
		{	System.out.println("Oh already in the head of the history... 0.0");
			return null;
		}
		// else... 
		SketchOperation operation = history.pop();
		future.push(operation);
		return operation;
	}
	
	public SketchOperation ctrlY()
	{	
		if (future.empty())
		{	System.out.println("Oh already in the tail of the history... 0.0");
			return null;
		}
		// else... 
		SketchOperation operation = future.pop();
		history.push(operation);
		return operation;
	}
	
	public void clear()
	{	
		history.clear();
		future.clear();
	}
	
	public void display()
	{	
		System.out.println("In the history: ");
		for (SketchOperation operation : history)
			operation.display();
		System.out.println("In the future: ");
		for (SketchOperation operation : future)
			operation.display();
		
	}
}
