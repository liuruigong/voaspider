
import java.io.*;   

public class Download
{
	private static Download s_instance = null;
	private static int	s_number = 1;
	private FileOutputStream out = null;   
	private FileOutputStream logout = null;  
	private String filepath;
	
	public static Download instance()
	{
		if(s_instance == null)
		{
			s_instance = new Download();
		}
		return s_instance;
	}

	
	private Download()
	{
		try
		{
			logout = new FileOutputStream(new File("d:\\voa\\voalog.txt"),true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getFilePath()
	{
		return filepath;
	}
	
   public void createDirectory(String path)
   {
	   File file = new File(path);
	   if(file.exists())
	   {
		   return;
	   }
	   else
	   {
		   if(file.getParentFile().exists())
		   {
			   file.mkdir();
		   }
		   else
		   {
			   createDirectory(file.getParentFile().getAbsolutePath());
			   file.mkdir();
		   }
	   }
   }
	
	public void create(String name)
	{		
		filepath = "d:\\voa\\" + name ;
		createDirectory(filepath);
		
		try
		{
			if(out != null)
			{
				out.close();
			}
			out = new FileOutputStream(new File(filepath + ".txt"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void log(String str)
	{
		if(logout != null)		
		{
			try
			{
				logout.write((s_number++ + "\t=>\t" + str + "\r\n").getBytes());				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void wget(String str)
	{
		if(out != null)		
		{
			try
			{
				out.write((str + "\r\n").getBytes());
				System.out.println(str);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void destory()
	{
		try
		{
			if(logout != null)
			{
				logout.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
    public static String download(String url) throws IOException 
    { 
        StringBuffer cmdout = new StringBuffer(); 
        Process process = Runtime.getRuntime().exec("wget " + url);
        InputStream fis = process.getInputStream(); 
        BufferedReader br = new BufferedReader(new InputStreamReader(fis)); 
        String line = null; 
        while ((line = br.readLine()) != null) 
        { 
                cmdout.append(line); 
        } 
        
        System.out.println(cmdout.toString()); 
        return cmdout.toString().trim(); 
    } 
}
