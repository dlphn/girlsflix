package com.gfx.domain.series;

import java.time.LocalDate;

public class Episode {
	
	private int numberEpisode;
	private String summary;
	private String name;
	private LocalDate releaseDate;
	private double rating;
	private int seasonId;
	private int serieId;
	private String picture;

	
	public Episode(int numberEpisode, String summary, String name, LocalDate releaseDate, double rating, int seasonId,
			int serieId, String picture) {
		super();
		this.numberEpisode = numberEpisode;
		this.summary = summary;
		this.name = name;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.seasonId = seasonId;
		this.serieId = serieId;
		this.picture = picture;
	}

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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public int getSerieId() {
		return serieId;
	}

	public void setSerieId(int serieId) {
		this.serieId = serieId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	


}
