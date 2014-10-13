package data_center;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/** The class to connect to the remote server set up by SJTU-software to execute 
 * a blast compare between the sequence provided and the sequence in the current 
 * biobrick database. The class depends on the class DatabaseConnector to get the
 * BbkOutline instances.  */
public class BlastingSearcher
{
	// about server
	private static final String SERVER_ADDRESS = "202.120.45.101";
	@SuppressWarnings("unused")
	private static final int PORT = 22;
	private static final String USER_NAME = "igem14";
	private static final String PASS_WORD = "bio34204348;";
	
	// about the cmd of blasting
	private static final String PROGRAM_TASK_DATABASE
		= "./db_linux/blastn -task blastn -db db_linux/bbk";
	private static final String IO_DIR_SERVER = "./db_linux/InputOutput/";
	private static final String IO_DIR_LOCAL = "./BlastIO/";
	private static final String INFILE_NAME = "input";
	private static final String OUTFILE_NAME = "output";
	
	private static final String RESULT_LINE_PREFIX = "lcl|";
	
	public static final int MODE_INPUT_SEQUENCE = 0;
	public static final int MODE_INPUT_FILE_PATH = 1;
	
	private static Connection connection = null;
	
	/** The function to execute blasting compare. Connection automatically established. 
	 * The function will create cache directory and cache files in the current directory. 
	 * @param input The input sequence of file path to be blasted online. 
	 * @param mode Specify the mode MODE_INPUT_SEQUENCE or MODE_INPUT_FILE_PATH, 
	 * which is define as constants in the class. The input string will be interpreted 
	 * differently base on this parameter. 
	 * @return SearchResultList instance just the same as keyword searching.  */
	public static SearchResultList blast(String input, int mode)
	{	
		if (mode != MODE_INPUT_SEQUENCE && mode != MODE_INPUT_FILE_PATH)
			return null;
		
		// else
		checkLocalIODir();
		String tag = TimeTagGenerator.generateTimeTag();
		String infilePath = IO_DIR_LOCAL + INFILE_NAME + tag,
			   outfilePath = IO_DIR_LOCAL + OUTFILE_NAME + tag, 
			   RemoteInfilePath = IO_DIR_SERVER + INFILE_NAME + tag,
			   RemoteOutfilePath = IO_DIR_SERVER + OUTFILE_NAME + tag;
		
		prepareInfile(input, mode, infilePath);
		
		try 
		{	connect();	// connection newed
			// upload the infile
			SCPClient scpClient = new SCPClient(connection);
			scpClient.put(infilePath, IO_DIR_SERVER);
			// blasting
			Session session = connection.openSession();
			session.execCommand(PROGRAM_TASK_DATABASE 
				+ " -query " + RemoteInfilePath
				+ " -out " + RemoteOutfilePath);
			monitorRemoteExecResult(session);
			// download the outfile
			scpClient.get(RemoteOutfilePath, IO_DIR_LOCAL);
			// remove the remote files
			disconnect();
			connect();
			connection.openSession().execCommand("rm " + RemoteInfilePath + "; "
											   + "rm " + RemoteOutfilePath);
			disconnect();
		} catch (IOException e) {e.printStackTrace();}
		
		return readOutfile(outfilePath, input);
	}
	
	/** Used to delete cache files automatically generated when blasting.  */
	public static void deleteLocalCacheFiles()
	{	
		File IODir = new File(IO_DIR_LOCAL);
		String[] fileNames = IODir.list();
		for (String fileName : fileNames)
			new File(IO_DIR_LOCAL + fileName).delete();
	}
	
	
	
	
	
	static void connect() throws IOException
	{	
		connection = new Connection(SERVER_ADDRESS);
		connection.connect();
		connection.authenticateWithPassword(USER_NAME, PASS_WORD);
	}
	
	/** Disconnect is needed between two cmd to avoid remote error */
	static void disconnect()
	{	
		connection.close();
	}
	
	private static void checkLocalIODir()
	{	
		File file = new File(IO_DIR_LOCAL);
		if (!file.exists())
			file.mkdirs();
	}
	
	private static void monitorRemoteExecResult(Session session) throws IOException
	{	
		BufferedReader stdOut = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStdout())));
		BufferedReader stdErr = new BufferedReader(new InputStreamReader(new StreamGobbler(session.getStderr())));
		String line;
		while ((line = stdOut.readLine()) != null)
			System.out.println(line);
		while ((line = stdErr.readLine()) != null)
			System.out.println(line);
		System.out.println("Exit status: " + session.getExitStatus());
		stdOut.close();
		stdErr.close();
	}
	
	private static void prepareInfile(String input, int mode, String infilePath)
	{	
		if (mode == MODE_INPUT_SEQUENCE)	// copy input into file "input"
		{	try
			{	BufferedWriter writer 
					= new BufferedWriter(new FileWriter(infilePath, false));
				writer.write(input);
				writer.flush();
				writer.close();
			} catch (IOException e) {e.printStackTrace();}
		}
		else // mode == MODE_INPUT_FILE_PATH, cpy the content of input into file "input"
		{	try
			{	BufferedReader reader 
					= new BufferedReader(new FileReader(input)); 
				BufferedWriter writer 
					= new BufferedWriter(new FileWriter(infilePath, false));
				String line;
		        while ( (line = reader.readLine()) != null)
		        	writer.write(line + "\n");
		        reader.close();	writer.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	private static SearchResultList readOutfile(String outfilePath, String inputStr)
	{
		SearchResultList list = new SearchResultList(inputStr);
		try
		{	BufferedReader reader 
				= new BufferedReader(new FileReader(outfilePath));
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
		line = line.substring(RESULT_LINE_PREFIX.length());	// cut off the prefix
		String[] rawResults = line.split(" ");
		ArrayList<String> results = new ArrayList<String>();
		for (String str : rawResults)
			if ( !str.equals("") )
				results.add(str);	// keep the unempty tokens
		
		String bbkName = results.get(0), // the first token
			   scoreStr = results.get(results.size() - 2), 	// the second last token
			   eValueStr = results.get(results.size() - 1);	// the last token

		BbkOutline outline = DatabaseConnector.getOutlineByName(bbkName);
		outline.blasting = new BbkOutline.Blasting();
		outline.blasting.score = Double.parseDouble(scoreStr);
		outline.blasting.eValue = Double.parseDouble(eValueStr);
		list.add(outline);
	}
	
	
}
