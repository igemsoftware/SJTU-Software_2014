package data_center;

/** The class to store the constants of the types of biobrick.  */
public class BbkType
{
	public final static int PROMOTER = 1;
	public final static int RBS = 2;
	public final static int PROTEIN_DOMAIN = 3;
	public final static int PROTEIN_CODING_SEQUENCE = 4;
	public final static int TRANSLATIONAL_UNIT = 5;
	public final static int TERMINATOR = 6;
	public final static int DNA = 7;
	public final static int PLASMID_BACKBONE = 8;
	public final static int PLASMID = 9;
	public final static int PRIMER = 10;
	public final static int COMPOSITE = 11;
	public final static int PROTEIN_GENERATOR = 12;
	public final static int REPORTER = 13;
	public final static int INVERTER = 14;
	public final static int SIGNALLING = 15;
	public final static int MEASUREMENT = 16;
	public final static int OTHER = 0;

	public final static String strOf_PROMOTER = "Regulatory";
	public final static String strOf_RBS = "RBS";
	public final static String strOf_PROTEIN_DOMAIN = "Tag";
	public final static String strOf_PROTEIN_CODING_SEQUENCE = "Coding";
	public final static String strOf_TRANSLATIONAL_UNIT = "Translational_Unit";
	public final static String strOf_TERMINATOR = "Terminator";
	public final static String strOf_DNA = "DNA";
	public final static String strOf_PLASMID_BACKBONE = "Plasmid_Backbone";
	public final static String strOf_PLASMID = "Plasmid";
	public final static String strOf_PRIMER = "Primer";
	public final static String strOf_COMPOSITE = "Composite";
	public final static String strOf_PROTEIN_GENERATOR = "Generator";
	public final static String strOf_REPORTER = "Reporter";
	public final static String strOf_INVERTER = "Inverter";
	public final static String strOf_SIGNALLING = "Signalling";
	public final static String strOf_MEASUREMENT = "Measurement";

	
	/** The types defined in the sketch function in EasyBbk.  */
	public static class Sketch
	{	
		public final static int LABEL = 1;
		public final static int BIO_BRICK = 2;
		public final static int PROTEIN = 3;
		public final static int BACKBONE = 4;
		public final static int RELATION = 5;
		public final static int VECTOR = 6;

		public static class BioBrick
		{	
			public final static int PROMOTER = 1;
			public final static int RBS = 2;
			public final static int PROTEIN_CODING_SEQUENCE = 3;
			public final static int TERMINATOR = 4;
			public final static int PRIMER = 5;
			public final static int REPORTER = 6;
			public final static int OTHER = 0;
		}
		
		public static class Protein
		{	
			public final static int FACTOR = 0;
			public final static int RECEPTER = 1;
			public final static int COMBINED = 3;
		}
		
		public static class BackBone
		{	
			public final static int LINEAR = 1;
			public final static int CIRCULAR = 2;	
		}
		
		public static class Relation
		{	
			public final static int PROMOTE = 1;
			public final static int SUPPRESS = 2;
			public final static int OTHER = 0;
		}
		
		public static class BioVector
		{	
			public final static int PLASMID = 0;
			public final static int VIRUS = 1;
			public final static int BACTERIA = 2;
			public final static int PLANT_CELL = 3;
			public final static int ANIMAL_CELL = 4;
		}
	}
	
	public static int bbkTypeToSketchBbkType(String bbkType)
	{	
		if (bbkType.equals(strOf_PROMOTER))
			return Sketch.BioBrick.PROMOTER;
		else if (bbkType.equals(strOf_RBS))
			return Sketch.BioBrick.RBS;
		else if (bbkType.equals(strOf_PROTEIN_CODING_SEQUENCE))
			return Sketch.BioBrick.PROTEIN_CODING_SEQUENCE;
		else if (bbkType.equals(strOf_TERMINATOR))
			return Sketch.BioBrick.TERMINATOR;
		else if (bbkType.equals(strOf_PRIMER))
			return Sketch.BioBrick.PRIMER;
		else if (bbkType.equals(strOf_REPORTER))
			return Sketch.BioBrick.REPORTER;
		else 
			return Sketch.BioBrick.OTHER;
	}
	

}
