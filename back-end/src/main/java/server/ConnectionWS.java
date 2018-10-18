package server;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ConnectionWS {

    /** 
     * Connects to an external API and returns the result.
     * 
     * @param url	the API url
     * @return the API response as a String
     */
	public String connect(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line;
			String str = "";
			while ((line = bufReader.readLine()) != null) {
				// lines.add(line);
				str += line;
            }
            return str;
		} finally {
	    	response.close();
		}
	}
	
}
