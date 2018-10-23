package com.gfx.service;

import java.util.List;

import com.gfx.domain.series.Serie;

public class Visualization {
	protected List<Serie> listSeries;
	
	public Visualization(List<Serie> list) {
		this.listSeries = list;
	}
	public Visualization() {}
	
	public void showSeries() {
		try {
			System.out.println("The series available are : ");
			for (Serie s:listSeries){
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
