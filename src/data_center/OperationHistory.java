package data_center;

import java.util.Stack;

import data_center.SketchProject.Operation;

/** The class implements a history list contains roll back and go forward function. 
 * Different from the SearchHistory, the OperationHistory aims the pointer at the
 * nick between data, not the data, like [H H H¡ýF F] (history, pointer,  future) */
public class OperationHistory
{
	Stack<Operation> history = new Stack<Operation>();
	Stack<Operation> future = new Stack<Operation>();
	
	public void newOperation(Operation operation)
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
	
	public Operation ctrlZ()
	{	
		if (history.empty())
		{	System.out.println("Oh already in the head of the history... 0.0");
			return null;
		}
		// else... 
		Operation operation = history.pop();
		future.push(operation);
		return operation;
	}
	
	public Operation ctrlY()
	{	
		if (future.empty())
		{	System.out.println("Oh already in the tail of the history... 0.0");
			return null;
		}
		// else... 
		Operation operation = future.pop();
		history.push(operation);
		return operation;
	}
	
	public void clear()
	{	
		history.clear();
		future.clear();
	}
}
