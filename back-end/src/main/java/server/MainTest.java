package server;

import java.io.IOException;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		SerieService service = new SerieService();
		service.init();
		
	
	
		/*SerieFactory factoryTest = new SerieFactory();
		List<Serie> series = factoryTest.getSeries();
		System.out.println("result of json series : " + series);*/

	}
}
