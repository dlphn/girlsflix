package server;

import java.util.*;
import org.json.*;
import java.io.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class SerieFactory {
	
	ObjectMapper mapper = new ObjectMapper();
	ArrayList<Serie> seriesList =  new ArrayList<Serie>(); // A changer plus tard en HashMap par exemple
	JSONObject currentJson;
	
	public SerieFactory() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public List<Serie> getSeries(){
		
		//String name = currentJson.get("name").toString();
        //name = name.replace(" ", "_");
        //seriesList.add(new Serie(name));
        
        try {

			// Convert JSON string from file to Object
			Serie s = mapper.readValue(new File("/server/src/main/resources/serie_test.json"), Serie.class);
			System.out.println(s);
			seriesList.add(s);

			// Convert JSON string to Object
			/*String jsonInString = "{\"age\":33,\"messages\":[\"msg 1\",\"msg 2\"],\"name\":\"mkyong\"}";
			User user1 = mapper.readValue(jsonInString, User.class);
			System.out.println(user1);*/

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       	
		return seriesList;
	}

}
