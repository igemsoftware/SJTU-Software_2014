package data_center;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

import data_center.SketchComponent.*;

@SuppressWarnings("serial")
public class Testing  extends JFrame
{	
	public static void main(String[] args)
	{	
		ArrayList<Component> componentList = new ArrayList<Component>();
		componentList.add(new Label(0, null, null, null, null));
		componentList.add(new BioBrick(0, 0, null, null));
		
		Component component = new Label(0, null, null, null, null);
		System.out.println(component.getClass() == Label.class);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);list.add(2);list.add(3);
		sort(list, false);
		
		try
		{	Integer.parseInt("NULL");
		} catch (Exception e){System.out.println("ei?");}
		
		
	}
	
	public static void sort(ArrayList<Integer> list, final boolean DESC)
	{	
		Comparator<Integer> comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer x1, Integer x2)
			{
				if (DESC)
					return x2 - x1;
				
				else
					return x1 - x2;
			}
		};
		Collections.sort(list, comparator);
	}
}
