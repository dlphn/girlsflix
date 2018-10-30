package com.gfx.domain.series;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Season {
	
	private List<Episode> episodes = new ArrayList<Episode>();
	private int numberSeason;
	private String nameSeason;
	private String summary;
	private LocalDate releaseDate;
	private double rating;
	private int seasonId;
	private int countEpiosde;
	private int serieId;
	private String picture;
	

	
	public Season(List<Episode> episodes, int numberSeason, String nameSeason, String summary, LocalDate releaseDate,
			double rating, int seasonId, int countEpiosde, int serieId, String picture) {
		super();
		this.episodes = episodes;
		this.numberSeason = numberSeason;
		this.nameSeason = nameSeason;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.seasonId = seasonId;
		this.countEpiosde = countEpiosde;
		this.serieId = serieId;
		this.picture = picture;
	}

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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public String getNameSeason() {
		return nameSeason;
	}

	public void setNameSeason(String nameSeason) {
		this.nameSeason = nameSeason;
	}

	public int getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public int getCountEpiosde() {
		return countEpiosde;
	}

	public void setCountEpiosde(int countEpiosde) {
		this.countEpiosde = countEpiosde;
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
