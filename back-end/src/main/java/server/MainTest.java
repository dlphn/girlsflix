package server;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		ConnectionWS connection = new ConnectionWS();
		try {
			connection.connect("series", "https://api.themoviedb.org/3/tv/popular?api_key=dbd1261470d6dfededebf40207478bef&language=en-US&page=1");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
