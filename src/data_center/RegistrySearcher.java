package data_center;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data_center.RegistryDetailFetcher.*;

class RegistrySearcher
{
	public static final String URL_PREFIX = "http://parts.igem.org/Special:Search?search=";
	public static final String URL_SUFFIX = "&limit=30";
	public static final String QUERY_KEY = "href";
	
	int queryRemains = 0;
	
	public synchronized SearchResultList search(String keyword)
	{	
		SearchResultList searchResultList = new SearchResultList(keyword);
		String url = URL_PREFIX + keyword.replace(" ", "+") + URL_SUFFIX;
		Document doc = getDoc(url);
		if (doc == null)
			return searchResultList;
		
		Elements elements = doc.getElementsByAttribute(QUERY_KEY);
		for (Element element : elements)	// like "Part:BBa B0034:Experience"
		{	String[] tokens = element.text().replace(" ", "_").split(":");
			for (String token : tokens)
				if (token.startsWith("BBa_"))
				{	BbkDetail bbkDetail = new BbkDetail();
					bbkDetail.name = token;
					bbkDetail.rating = new BbkDetail.Rating();
					searchResultList.add(bbkDetail);
				}
		}
		
		searchResultList.display();
		System.out.println("#########################################");
		
		queryRemains = searchResultList.size() * 5;
		for (BbkOutline bbkOutline : searchResultList)
		{	BbkDetail bbkDetail = (BbkDetail) bbkOutline;
			new XMLFetcher(bbkDetail, this).start();
			new PartInfoFetcher(bbkDetail, this).start();
			new PartConfirmFetcher(bbkDetail, this).start();
			new AvgStarTotCommentFetcher(bbkDetail, this).start();
			new NCBIQuoteNumFetcher(bbkDetail, this).start();
		}
		
		if (queryRemains > 0)
			try {wait();}
			catch (InterruptedException e) {e.printStackTrace();}
		return searchResultList;
	}
	
	public BbkDetail searchDetailByName(String bbkName)
	{	
		queryRemains = 5;
		BbkDetail bbkDetail = new BbkDetail();
		bbkDetail.name = bbkName;
		new XMLFetcher(bbkDetail, this).start();
		new PartInfoFetcher(bbkDetail, this).start();
		new PartConfirmFetcher(bbkDetail, this).start();
		new AvgStarTotCommentFetcher(bbkDetail, this).start();
		new NCBIQuoteNumFetcher(bbkDetail, this).start();
		if (queryRemains > 0)
			try {wait();}
			catch (InterruptedException e) {e.printStackTrace();}
		return bbkDetail;
	}
	
	public static BbkDetail getDetailFromSearchResultList
		(String bbkName, SearchResultList list)
	{	
		for (BbkOutline bbkOutline : list)
			if (bbkOutline.name.equals(bbkName))
				return (BbkDetail) bbkOutline;
		return null;
	}
	
	private static Document getDoc(String url)
	{	
		Document doc = null;
		for (int i = 0; i < 3; ++i)
			try {doc = Jsoup.connect(url).get();} 
			catch (IOException e) {continue;}
		return doc;
	}
}
