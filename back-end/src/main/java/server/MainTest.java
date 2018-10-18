package server;



public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
	    
		SerieService service = new SerieService();
		service.init();
		
		/*System.out.println("\n"+"**********************************"+"\n");
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
		visu.getListSeries();*/

	}
}
