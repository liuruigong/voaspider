
import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.StringBuffer;



public class VolUtil
{
	
	   public static String vol = "http://www.51voa.com";
	
	   public static String getHttpContent(String url)
	   {
	        HttpURLConnection connection = null;
	        StringBuffer content = new StringBuffer();
	        try
	        {
	            URL address_url = new URL(url);
	            connection = (HttpURLConnection) address_url.openConnection();
//	            connection.setRequestMethod("GET");
	            //���÷��ʳ�ʱʱ�估��ȡ��ҳ���ĳ���ʱ��,����ֵ
	            System.setProperty("sun.net.client.defaultConnectTimeout","30000");
	            System.setProperty("sun.net.client.defaultReadTimeout", "30000");

	            //�õ�����ҳ��ķ���ֵ
	            int response_code = connection.getResponseCode();
	            if (response_code == HttpURLConnection.HTTP_OK) 
	            {
	                InputStream in = connection.getInputStream();
	                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
	                String line = null;
	                while ((line = reader.readLine()) != null)
	                {
	                   content.append(line + "\r\n");
	                }
	                return content.toString();
	            }
	        } 
	        catch (MalformedURLException e) 
	        {
	            e.printStackTrace();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        } 
	        finally 
	        {
	            if(connection !=null)
	            {
	                connection.disconnect();
	            }
	        }
	        return "";
	    }
}
