package com.gfx.domain.series;

import java.time.LocalDate;

/**
 * Episode Object with its Getters and Setters
 *The attributes are Private. We can only manipulate them through the getters and setters.
 */
public class Episode {
	
	private int episodeId;
	private int episodeNb;
	private String summary;
	private String name;
	private LocalDate releaseDate;
	private double rating;
	private int seasonNb;
	private int serieId;
	private String image;

	
	public Episode(int episodeId, int episodeNb, String name, String summary, LocalDate releaseDate, double rating, int seasonNb,
			int serieId, String image) {
		super();
		this.episodeId = episodeId;
		this.episodeNb = episodeNb;
		this.name = name;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.seasonNb = seasonNb;
		this.serieId = serieId;
		this.image = image;
	}
	
	/**
	 * Getters & Setters
	 */

	public int getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}
	
	public int getEpisodeNb() {
		return episodeNb;
	}

	public void setEpisodeNb(int episodeNb) {
		this.episodeNb = episodeNb;
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

	public int getSeasonNb() {
		return seasonNb;
	}

	public void setSeasonNb(int seasonNb) {
		this.seasonNb = seasonNb;
	}

	public int getSerieId() {
		return serieId;
	}

	public void setSerieId(int serieId) {
		this.serieId = serieId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
