package data_center; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class OfficialUploadFiller
{
	private static CloseableHttpClient httpClient;
	private static HttpClientContext context;
	
	public static boolean login(String username, String password) throws Exception
	{
		httpClient = HttpClients.createDefault();
		context = HttpClientContext.create();
		context.setCookieStore(new BasicCookieStore());
	    
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("Login", "Login"));
	    nvps.add(new BasicNameValuePair("username", username));
	    nvps.add(new BasicNameValuePair("password", password));
	    
	    HttpPost httpost = new HttpPost("https://igem.org/Login2");
	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
		httpClient.execute(httpost, context);
	
	    HttpGet httpget = new HttpGet("http://igem.org/Login_Confirmed");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
		
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		while ( (line = reader.readLine()) != null )
			if (line.contains("You have successfully logged into the iGEM and Registry web sites."))
				return true;
		reader.close();
		// else... 
		return false;
	}
	
	public static String getNextAvailablePartName() throws Exception
	{	
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		// the next available name is surrounded like "<TD>BBa_XXXX<TR>"
		// 	before it there is a "<TD>BBa_XXX to BBa_XXX<TD>" token to be excluded. 
		while ( (line = reader.readLine()) != null )
			if (line.contains("Next Available Part"))
			{	String[] rawTokens = line.split("<TD>");
				for (String rawToken : rawTokens)
					if (rawToken.startsWith("BBa_") && !rawToken.contains(" to BBa_"))
					{	String[] tokens = rawToken.split("<TR>");
						return tokens[0];
					}
			}
		reader.close();
		// not found the string that fits the conditions
		return null;
	}
	
	public static String[] getAvailablePartNameRegion() throws Exception
	{	
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		// the available name region is surrounded like "<TD>BBa_XXX to BBa_XXX<TD>"
		while ( (line = reader.readLine()) != null )
			if (line.contains("Next Available Part"))
				for (String token : line.split("<TD>"))
					if (token.startsWith("BBa_") && token.contains(" to BBa_"))
						return token.split(" to ");
		reader.close();
		// not found the string that fits the conditions
		return null;
	}
	
	public static String createNewPart(String part_name, BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("id", "0"));
	    nvps.add(new BasicNameValuePair("group_1859", "on"));
	    nvps.add(new BasicNameValuePair("part_name", part_name));
	    nvps.add(new BasicNameValuePair("type", bbkUpload.type));
	    nvps.add(new BasicNameValuePair("short_description", bbkUpload.shortDesc));
	    nvps.add(new BasicNameValuePair("long_description", bbkUpload.longDesc));
	    nvps.add(new BasicNameValuePair("source", bbkUpload.source));
	    nvps.add(new BasicNameValuePair("notes", bbkUpload.notes));
	    nvps.add(new BasicNameValuePair("proceed", "Go on to enter the sequence and add feature annotations"));
	    
	    HttpPost httpost = new HttpPost("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
		httpClient.execute(httpost, context);
		
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/xml/part.cgi?part=" + part_name);
		return httpget.toString();
	}
	
  
}