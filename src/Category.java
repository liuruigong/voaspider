import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Category
{
	
	public static class CateInfo
	{
		public String href;
		public String catagory;
		
		public String toString()
		{
			return catagory + "\t=>\t" + href;
		}
	}
	
	public static List<CateInfo> getCategory()
	{
		
		List<CateInfo> catalist = new LinkedList<CateInfo>();
		
		try
		{
			
			Document doc = Jsoup.connect(VolUtil.vol).get();
			Element nav = doc.getElementById("left_nav");
			
			Elements links = nav.getElementsByTag("a");
			
			for (Element link : links) 
			{
				  String href = link.attr("href");
				  String text = link.text();
				  
				 
				  if(href.indexOf(".html") > -1 && href.indexOf("help.html") == -1)
				  {					  
					  CateInfo info = new CateInfo();
					  info.href = href;
					  info.catagory = text.replace('(', ' ').replace('/',' ').replace(')', ' ');
					  info.catagory = info.catagory.trim();
					  catalist.add(info);
				  }				  				  
				  
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return catalist;
	}
}
