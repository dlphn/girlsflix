package com.gfx.domain.series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Data {
	private static List<Serie> seriesList;
	
	public static List<Serie> getListSeries() {
		return seriesList;
	}

	public static void setListSeries(List<Serie> series) {
		seriesList = series;
	}
	
	public void showSeries() {
		try {
			System.out.println("The series available are : ");
			for (Serie s : seriesList){
				System.out.println(s.info());
				//System.out.println("\n\n");
			} 
		} catch (NullPointerException e) {
			System.out.println("size is :   No Series for the moment.");}
	}
	
	public static List<Serie> pickNRandom(int n) {
	    List<Serie> copy = new ArrayList<Serie>(seriesList);
	    Collections.shuffle(copy);
	    if (copy.size() < n) {
	    	n = copy.size();
	    }
	    return copy.subList(0, n);
	}
	
	/**
	 * Runs through the whole list of series to return the series which title contains the search query (case insensitive)
	 * 
	 * @param query		the user search request
	 * @return 			the list of series that match the search query
	 */
	public static List<Serie> search(String query) {
		List<Serie> result = seriesList.stream()
			     .filter(item -> 
			     	Pattern.compile(Pattern.quote(query), Pattern.CASE_INSENSITIVE).matcher(item.getTitle()).find())
			     .collect(Collectors.toList());
		return result;
	}
	
	/**
	 * Runs through the list of series to return those of the same genre as the query
	 * @param query		the genre to filter on
	 * @return 			the list of series of the genre specified
	 */
	public static List<Serie> searchGenre(String query) {
		List<Serie> result = seriesList.stream()
			     .filter(item -> item.getSerieGenres().contains(query))
			     .collect(Collectors.toList());
		return result;
	}
	
	public static Serie getById(int id) {
		Serie searched = null;
		for (Serie s : seriesList) {
			if(s.getId() == id) {
				 searched = s;
			}
		}
		return searched;
	}
}
