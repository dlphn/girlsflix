package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		//System.out.println("Hello world!");
		
		SerieService service = new SerieService();
		service.init();
		SerieFactory factoryTest = new SerieFactory();
		List<Serie> series = factoryTest.getSeries();
		
		List<Thread> enjoyers = new ArrayList<Thread>();
		
		
		Thread threadEnjoyer1 = new Thread(new Enjoyer("Enjoyer 1", new Visualization(series)));
		Thread threadEnjoyer2 = new Thread(new Enjoyer("Enjoyer 2", new Visualization(series)));
		enjoyers.add(threadEnjoyer1);
		enjoyers.add(threadEnjoyer2);
		threadEnjoyer1.start();
		threadEnjoyer2.start();
		
		
		for(Thread t:enjoyers) {
			try {
				t.join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		//System.out.println("\n"+"**********************************"+"\n");
		//SerieFactory factoryTest = new SerieFactory();
		
		//System.out.println("\n"+"**********************************"+"\n");
		//List<Serie> series = factoryTest.getSeries();
		//System.out.println("\n"+"**********************************"+"\n");
		//System.out.println("result of json series : " + series.toString() +"\n");
		//System.out.println("**********************************"+"\n");
		//Visualization visu = new Visualization();
		//visu.showSeries(); // before update
		
		//visu.update(factoryTest);
		//visu.setEnjoyerLogged(new Enjoyer("enjoyer1", visu));
		//visu.showSeries(); // after udpate
		

	}
}
