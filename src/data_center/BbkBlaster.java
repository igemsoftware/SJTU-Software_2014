package data_center;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BbkBlaster
{
	public static final String BLAST_PROGRAM_NAME = "./Blast/blastn.exe";
	public static final String TASK = " -task blastn";
	public static final String DEFAULT_INFILE = "./Blast/input";
	public static final String SEQUENCE_DATABASE = "./Blast/test.fasta";
	public static final String OUTFILE = "./Blast/output";
	
	public static final String RESULT_LINE_PREFIX = "lcl|";
	
	public static final int MODE_INPUT_SEQUENCE = 0;
	public static final int MODE_INPUT_FILE_PATH = 1;
	
	public static SearchResultList blast_local(String input, int mode)
	{	
		String infile = null;
		switch (mode)
		{	
			case MODE_INPUT_SEQUENCE:
				fillDefaultInfile(input);
				infile = DEFAULT_INFILE;
				break;
			case MODE_INPUT_FILE_PATH:
				infile = input;
				break;
			default:
				return null;
		}
		
		try
		{	Runtime.getRuntime().exec(BLAST_PROGRAM_NAME + TASK 
									+ " -query " + infile
									+ " -db " + SEQUENCE_DATABASE
									+ " -out " + OUTFILE).waitFor();
		} catch (Exception e) {e.printStackTrace();}
		// file generated at this time
		
		return readOutfile();
	}

	private static void fillDefaultInfile(String sequence)
	{
		try
		{	BufferedWriter writer 
				= new BufferedWriter(new FileWriter(DEFAULT_INFILE, false));
			writer.write(sequence);
			writer.flush();
			writer.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	private static SearchResultList readOutfile()
	{
		SearchResultList list = new SearchResultList();
		try
		{	BufferedReader reader 
				= new BufferedReader(new FileReader(OUTFILE));
			String line;
			boolean startReading = false, endReading = false;
			while ((line = reader.readLine()) != null)
				if (line.startsWith(RESULT_LINE_PREFIX))
				{	startReading = true;
					break;	
				}
			while (startReading && (!endReading))
			{	processLine(line, list);
				if ( !(line = reader.readLine()).startsWith(RESULT_LINE_PREFIX) )
					endReading = true;
			}
			reader.close();
		} catch (IOException e) {e.printStackTrace();}
		
		return list;
	}

	private static void processLine(String line, SearchResultList list)
	{
		line = line.substring(RESULT_LINE_PREFIX.length());
		String[] rawResults = line.split(" ");
		ArrayList<String> results = new ArrayList<String>();
		for (String str : rawResults)
			if ( !str.equals("") )
				results.add(str);
		
		String bbkName = results.get(0), 
			   scoreStr = results.get(1), 
			   eValueStr = results.get(2);

		BbkOutline outline = BbkDatabaseConnector.getOutlineByName(bbkName);
		outline.blasting = new BbkOutline.Blasting();
		outline.blasting.score = Integer.parseInt(scoreStr);
		outline.blasting.eValue = Double.parseDouble(eValueStr);
		list.add(outline);
	}
	
	
}
