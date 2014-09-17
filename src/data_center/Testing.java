package data_center;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Testing
{	
	public static void main(String[] args) throws IOException, Exception
	{	
		/*
		Connection conn = new Connection(BbkBlaster.SERVER_ADDRESS);
		conn.connect();
		
		conn.authenticateWithPassword(BbkBlaster.USER_NAME, BbkBlaster.PASS_WORD);
		
		Session sess = conn.openSession();
		//sess.execCommand("rm db_linux/InputOutput/input");
		//sess.execCommand("scp -P 22 input igem14@202.120.45.101:/home/igem14/db_linux/");
		sess.execCommand("ls db_linux/InputOutput");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new StreamGobbler(sess.getStdout())));
		BufferedReader brErr = new BufferedReader(new InputStreamReader(new StreamGobbler(sess.getStderr())));
		String str;
		while ((str = br.readLine()) != null)
			System.out.println(str);
		while ((str = brErr.readLine()) != null)
			System.out.println(str);
		br.close();
		brErr.close();
		System.out.println("Exit status: " + sess.getExitStatus());
		conn.close();
		*/
		
		SearchResultList list = BbkBlaster.blast("input", BbkBlaster.MODE_INPUT_FILE_PATH);
		list.display();
		System.out.println(list.size());
		//BbkBlaster.deleteLocalCacheFiles();
		
	}
}
