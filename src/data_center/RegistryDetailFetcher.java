package data_center;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RegistryDetailFetcher
{
	public static void fillDetail(BbkDetail bbkDetail) throws IOException
	{	
		String url = "http://parts.igem.org/cgi/xml/part.cgi?part=" + bbkDetail.name;
		Document doc = Jsoup.connect(url).get();
		
		Elements elem = doc.select("part_name");
		System.out.println(elem.text());
		
	}
}
