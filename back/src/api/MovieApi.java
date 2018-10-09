package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
/*import org.json.JSONArray;
import org.json.JSONObject;*/

public class MovieApi {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("https://api.themoviedb.org/3/movie/550?api_key=dbd1261470d6dfededebf40207478bef");
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(entity.getContent()));
		    String str = new String();
			String line;
			while ((line = bufReader.readLine()) != null) {
				str += line;
            }
			System.out.println(str);
            /*JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < jsonArray.length(); i++){
                if (jsonArray.get(i) instanceof JSONObject){
                    JSONObject jsnObj = (JSONObject)jsonArray.get(i);
                    System.out.println(jsnObj);
                }
            }*/
		} finally {
		    response.close();
		}
	}

}

