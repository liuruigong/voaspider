
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContentFile 
{
	public static class ContentInfo
	{
		public String mp3 = null;
		public String lrc = null;
		public String content = null;
		public String filename = "unknow";
		
		public void save()
		{
			try
			{
				String filepath = Download.instance().getFilePath();
				FileOutputStream out = new FileOutputStream(new File(filepath + "/" + filename + ".html"));
				out.write(content.getBytes());
				out.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	public static ContentInfo getInfo(String url)
	{
		try
		{
			ContentInfo info = new ContentInfo();
			List<String> lsttitles = new LinkedList<String>();
			
			Document doc = Jsoup.connect(url).get();
			
			if(doc == null)
			{
				return null;
			}
			Element nav = doc.getElementById("menubar");
			
			
			Element map3 = nav.getElementById("mp3");
			if(map3 != null)
			{
				info.mp3 = map3.attr("href");
				String tmp = info.mp3;
				String arr[] = tmp.split("/");
				info.filename = arr[arr.length - 1];
				info.filename = info.filename.replaceAll(".mp3", "");
			}
			
			Element lrc = nav.getElementById("lrc");
			if(lrc != null)
			{
				info.lrc = lrc.attr("href");
				info.lrc = VolUtil.vol + info.lrc;
			}
			
			Element content = doc.getElementById("content");
			String byline = content.select(".byline").text();
			String datetime = content.select(".datetime").text();
			info.content = content.html();
			info.content = info.content.replaceAll("src=\"", "src=\"" + VolUtil.vol);
			info.content = info.content.replaceAll("Player\\(\"", "Player\\(\"" + VolUtil.vol);
			String link = "<br><a href=\"" + url + "\">" + "original : " + url + "</a>\r\n<br>";
			info.content = "<html>\r\n <head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n</head>\r\n <body>\r\n<div>\r\n " + link  + info.content  ;
			info.content = info.content + "</div>\r\n</body>\r\n</html>";
			info.content = info.content.replace("¡±", "\"").replace("¡°", "\"");
			info.content = info.content.replace('¡ª', '-').replace('¡¯', '\'').replace("¡­", "...");
			

			
			return info;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
