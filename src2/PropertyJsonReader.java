import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;



public class PropertyJsonReader {
		ArrayList<String> arrLand = new ArrayList<String>(); 
		ArrayList<String> arrRail = new ArrayList<String>(); 
		ArrayList<String> arrCompany = new ArrayList<String>(); 

		public void PropertyJsonReader2(){
		
    	 
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader("src/property.json")){
             JSONObject jsonfile = (JSONObject) processor.parse(file);
             JSONArray Land = (JSONArray) jsonfile.get("1");

             
            
             for(Object i:Land){
            	 arrLand.add((String)((JSONObject)i).get("id"));
				 arrLand.add((String)((JSONObject)i).get("name"));
				 arrLand.add((String)((JSONObject)i).get("cost"));			 
                 
             }
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
			
            	 arrRail.add((String)((JSONObject)i).get("id"));
				 arrRail.add((String)((JSONObject)i).get("name"));
				 arrRail.add((String)((JSONObject)i).get("cost"));
	
             }
			 
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){

            	 arrCompany.add((String)((JSONObject)i).get("id"));
				 arrCompany.add((String)((JSONObject)i).get("name"));
				 arrCompany.add((String)((JSONObject)i).get("cost"));
             }
             
             
 
             
             
             
             
         } catch (IOException e){
             e.printStackTrace();
         } catch (ParseException e){
             e.printStackTrace();
         }
     }

}