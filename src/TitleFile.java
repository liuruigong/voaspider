
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TitleFile 
{
	public static List<String> getTitles(String url)
	{
		try
		{
			List<String> lsttitles = new LinkedList<String>();
			Document doc = Jsoup.connect(url).get();
			Element nav = doc.getElementById("list");
			
			Elements lis = nav.getElementsByTag("li");
			
			int i = 0;
			for (Element li : lis) 
			{
				  Element link = li.getElementsByTag("a").first();
				  String href = link.attr("href");
				  String text = link.text();
				  lsttitles.add(href);
				  String txt = text + "	=>	" + href;
				  Download.instance().log(txt);
				  System.out.println(i++ + "\t"  + txt);		 
				  
				  
			}
			return lsttitles;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getPages(String url)
	{
		try
		{
			List<String> lsttitles = new LinkedList<String>();
			Document doc = Jsoup.connect(url).get();
			Element nav = doc.getElementById("list");
			
			Elements links = nav.select("a");
			
			int i = 0;
			for (Element link : links) 
			{
				  if(link.parent().nodeName().equalsIgnoreCase("li") == false)
				  {
					  String href = link.attr("href");
					  String text = link.text();
					  lsttitles.add(href);
					  System.out.println(i++ + "\t" + text + "	=>	" + href);		
				  }				   				  				  
			}
			return lsttitles;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
