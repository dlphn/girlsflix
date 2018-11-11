package com.gfx.domain.series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

/**
 * Stores Serie instances.
 * Methods to interact with the list of series.
 */
@Component
public class Data {
	private static List<Serie> seriesList;
	
	public static List<Serie> getListSeries() {
		return seriesList;
	}

	public static void setListSeries(List<Serie> series) {
		seriesList = series;
	}
	
	public static Serie getById(int searchedId) {
		Serie searched = null;
		for (Serie serie : seriesList) {
			if(serie.getId() == searchedId) {
				 searched = serie;
			}
		}
		return searched;
	}
	
	public static List<Serie> pickNRandom(int numberOfSeries) {
	    List<Serie> copyOfSeries = new ArrayList<Serie>(seriesList);
	    Collections.shuffle(copyOfSeries);
	    if (copyOfSeries.size() < numberOfSeries) {
	    	numberOfSeries = copyOfSeries.size();
	    }
	    return copyOfSeries.subList(0, numberOfSeries);
	}
	
	/**
	 * Send a number of random series with a specific genre
	 * 
	 * @param numberOfSeries		the number of random series to return
	 * @param genre	the genre to filter on
	 * @return		the list of random series
	 */
	public static List<Serie> pickNRandomSameGenre(int numberOfSeries, String genre) {
		List<Serie> groupByGenre = new ArrayList<Serie>();
		for (Serie serie : seriesList) {
			if (serie.getSerieGenres().contains(genre)) {
				groupByGenre.add(serie);
			}
		}
	    Collections.shuffle(groupByGenre);
	    if (numberOfSeries > groupByGenre.size()) {
	    	numberOfSeries = groupByGenre.size();
	    }
	    return groupByGenre.subList(0, numberOfSeries);
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
}
