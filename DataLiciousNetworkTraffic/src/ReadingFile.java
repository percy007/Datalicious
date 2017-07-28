import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReadingFile {

	public static String[] checkinRequest(String f) {
	    String u[]= new String[2];      
		JsonParser parser = new JsonParser();
		        try {
		            Object obj = parser.parse(new FileReader(f));
		 
		             ArrayList<JsonElement> urls=new ArrayList<JsonElement>();
		            JsonObject jsonObject = (JsonObject) obj;
		            JsonElement ele= jsonObject.get("log");
		            JsonElement ele2= ((JsonObject) ele).get("entries");
		            JsonArray requestArray= ele2.getAsJsonArray();
		            for(JsonElement a:requestArray){
		            	JsonElement request=((JsonObject) a).get("request");
		            	JsonElement url=((JsonObject) request).get("url");
		            		urls.add(url);
		            	}
		            int k=0;
		           for(int i=0;i<urls.size();i++){
		        	  
		        	   if((urls.get(i).toString()).contains("https://www.google-analytics.com/r/collect")||(urls.get(i).toString()).contains("https://dc.optimahub.com/?c.pr"))
		        	  {
		        		  u[k]=(urls.get(i).toString());
		        		  System.out.println("Request is made to www.google-analytics.com and dc.optimahub.com :: "+urls.get(i));
		        	  k++;
		        	  }
		        
		           }		     
		        }catch (Exception e) {
		            e.printStackTrace();
		            }
				return u;
		        
		        
		        }
	
	public static void writingToCsv(String f) throws IOException{
		String fName="./lib//Result.csv";
		String [] url=checkinRequest(f);
		String s=url[0];
		File outFile= new File(fName);
		FileWriter fw= new FileWriter(outFile);
		
		StringTokenizer st = new StringTokenizer(s, "&");
		while(st.hasMoreTokens()){
			String str=st.nextToken();
			
			
			
			if(str.contains("dt")){
				fw.write(str);
			fw.write("***********Succesfully loged in csv file value of dt is" + str);
			}
			else if(str.contains("dp")){
				
				
				
				fw.write(str);
				System.out.println("Succesfully loged in csv file ");
			}
		
			
		}
		fw.close();

	}
	
	
}