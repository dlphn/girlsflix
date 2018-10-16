package server;

import java.time.LocalDate;
import java.util.ArrayList;

public class Season {
	
	private ArrayList<Episode> episodes = new ArrayList<Episode>();
	private int numberSeason;
	private String summary;
	private LocalDate releaseDate;
	private int rating;
	
	public Season(int numberSeason, String summary, LocalDate releaseDate, int rating) {
		super();
		this.numberSeason = numberSeason;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
	
	public void addEpisode(Episode episode) {
		episodes.add(episode);
		
	}
	
	/*******************/
	/*Getters & Setters*/
	/*******************/
	
	public int getNumberSeason() {
		return numberSeason;
	}

	public void setNumberSeason(int numberSeason) {
		this.numberSeason = numberSeason;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
