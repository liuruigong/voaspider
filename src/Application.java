
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Application 
{

    public static void main(String[] args) 
    {
    	List<Category.CateInfo> catamap = Category.getCategory();
    	
    	
		InputStreamReader instream = new InputStreamReader(System.in);    
		BufferedReader reader = new BufferedReader(instream);
    	String cmd = "";
    	while(true)
    	{
    		try
    		{
				for(int i = 0 ; i < catamap.size() ; i++)
				{
					System.out.println(i + "\t" + catamap.get(i));
				}
    			
    			cmd = reader.readLine();
    			
    			if(cmd.equalsIgnoreCase("x"))
    			{
    				break;
    			}
    			else
    			{
    				int n = Integer.parseInt(cmd);
    				Category.CateInfo info = catamap.get(n);    
    				Download.instance().create(info.catagory);
    				String catahref = VolUtil.vol + info.href;
    				List<String> lstpage = TitleFile.getPages(catahref);
    				List<Integer> lst = new LinkedList<Integer>();
    				System.out.println("input page rage or page number");
    				cmd = reader.readLine();
    				
    				if(cmd.equalsIgnoreCase("all") || cmd.isEmpty() == true)
    				{
    					for(int j = 0 ; j < lstpage.size() ; j++)
    					{
    						lst.add(j);
    					}
    				}
    				else
    				{
	    				String pages[] = cmd.split(",");    				    				
	    				for(int p = 0 ; p < pages.length ; p++)
	    				{
	    					String page = pages[p];
	    					if(page.indexOf('-') == -1)
	    					{
	    						lst.add(Integer.parseInt(page));
	    					}
	    					else
	    					{
	    						String rages[] = page.split("-");
	    						for(int m = Integer.parseInt(rages[0]) ; m <= Integer.parseInt(rages[1]) ; m++)
	    						{
	    							lst.add(m);
	    						}
	    					}
	    				}
    				}
    				
    				for(int i = 0 ; i < lst.size() ; i++)
    				{
    					List<String> lsttitle = TitleFile.getTitles(VolUtil.vol + "/" + lstpage.get(i));
    					for(String href : lsttitle)
    					{
    						ContentFile.ContentInfo info2 = ContentFile.getInfo(VolUtil.vol + href);
    						if(info2 != null)
    						{
    							info2.save();
    							
    							if(info2.mp3 != null)
    							{
    								Download.instance().wget(info2.mp3);
    							}
    							if(info2.lrc != null)
    							{
    								Download.instance().wget(info2.lrc);
    							}
    							
    						}
    					}
    				}
    			}
    			    		
    			
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		
    	}
    	Download.instance().destory();
    }
}