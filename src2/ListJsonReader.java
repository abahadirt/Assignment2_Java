import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class ListJsonReader{
     
    
    ArrayList<String> arrChance = new ArrayList<String>(); 
   	ArrayList<String> arrCommunity = new ArrayList<String>(); 
    
    public void ListJsonReader2(){
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("src/list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            for(Object i:chanceList){
				arrChance.add(((String)((JSONObject)i).get("item")));
				 
            }
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
            	arrCommunity.add(((String)((JSONObject)i).get("item")));

            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
     }

     
}

