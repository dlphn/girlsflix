package com.gfx.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gfx.domain.series.Serie;

@Service
public class Visualization {
	protected List<Serie> listSeries;
	
	public Visualization() {}
	
	public Visualization(List<Serie> list) {
		this.listSeries = list;
	}
	
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
	
	public List<Serie> pickNRandom(int n) {
	    List<Serie> copy = new ArrayList<Serie>(this.listSeries);
	    Collections.shuffle(copy);
	    return copy.subList(0, n);
	}
	
	public List<Serie> search(String query) {
		List<Serie> result = this.listSeries.stream()
			     .filter(item -> 
			     	Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(item.getTitle()).find())
			     .collect(Collectors.toList());
		return result;
	}
}
