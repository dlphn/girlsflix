package server;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		Keys apiKey = new Keys();
		
		ConnectionWS connection = new ConnectionWS();
		try {
			connection.connect("series", "https://api.themoviedb.org/3/tv/popular?api_key=" + apiKey.getApiKey() + "&language=en-US&page=1");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n"+"**********************************"+"\n");
		SerieFactory factoryTest = new SerieFactory();
		
		System.out.println("\n"+"**********************************"+"\n");
		List<Serie> series = factoryTest.getSeries();
		System.out.println("\n"+"**********************************"+"\n");
		System.out.println("result of json series : " + series.toString() +"\n");
		System.out.println("**********************************"+"\n");
		Visualization visu = new Visualization();
		visu.showSeries(); // before update
		
		visu.update(factoryTest);
		visu.showSeries(); // after udpate
		visu.getListSeries();
		

	}
}
