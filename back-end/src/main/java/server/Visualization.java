package server;

import java.util.List;

public class Visualization {
	protected List<Serie> listSeries;
	
	public Visualization(List<Serie> list) {
		this.listSeries = list;
	}
	public Visualization() {}
	
	public void showSeries() {
		try {
			int size = listSeries.size();
			System.out.println("The series available are : ");
			for (int i = 0; i < size; i++)
				{System.out.println(i);
				Serie s = listSeries.get(i);
				System.out.println(s);
				System.out.println(s.info());
				//System.out.println("\n\n");
				} 
		} catch (NullPointerException e) {
			System.out.println("size is :   No Series for the moment.");}
	}
	
	public List<Serie> getListSeries() {
		return listSeries;
	}
	
	public void setListSeries(List<Serie> listSeries) {
		this.listSeries = listSeries;
	}

	
	public void update(SerieFactory f) {
		this.listSeries = f.getList();
	}
	
	
}
