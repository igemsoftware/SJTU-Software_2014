package EasyBBK_Swing.gui;

import java.util.*;

public class Information {
	public String sortby;
	public ArrayList<String> type;
	public int[] enteredyear;
	public ReleaseStatus releasestatus;
	public DNAStatus dnastatus;
	public boolean whetherornot;
	public AverageStars averagestars;
	public Preferences preferences;
	
	public Information(){
		sortby = "";
		type = new ArrayList<String>();
		enteredyear = new int[2];
		releasestatus = new ReleaseStatus();
		dnastatus = new DNAStatus();
		whetherornot = false;
		averagestars = new AverageStars();
		preferences = new Preferences();
	}
	
	public class ReleaseStatus{
		public boolean released;
		public boolean deleted;
		public boolean notreleased;
		
		public ReleaseStatus(){
			released = false;
			deleted = false;
			notreleased = false;
		}
	}
	
	public class DNAStatus{
		public boolean available;
		public boolean planning;
		public boolean informational;
		
		public DNAStatus(){
			available = false;
			planning = false;
			informational = false;
		}
	}
	
	public class AverageStars{
		public boolean high;
		public boolean middle;
		public boolean low;
		
		public AverageStars(){
			high = false;
			middle = false;
			low = false;
		}
	}
	
	public class Preferences{
		public int status;
		public int quality;
		public int feedbacks;
		public int publication;
		
		public Preferences(){
			status = 0;
			quality = 0;
			feedbacks = 0;
			publication = 0;
		}
	}
}