package server;

import java.time.LocalDate;

public class Episode {
	
	private int numberEpisode;
	private String summary;
	private String name;
	private LocalDate releaseDate;
	private int rating;
	
	public Episode(int numberEpisode, String summary, String name, LocalDate releaseDate, int rating) {
		super();
		this.numberEpisode = numberEpisode;
		this.summary = summary;
		this.name = name;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
	
	/*******************/
	/*Getters & Setters*/
	/*******************/

	public int getNumberEpisode() {
		return numberEpisode;
	}

	public void setNumberEpisode(int numberEpisode) {
		this.numberEpisode = numberEpisode;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
