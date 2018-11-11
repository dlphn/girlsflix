package com.gfx.domain.series;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Season Object with its Getters and Setters
 *The attributes are Private. We can only manipulate them through the getters and setters.
 */
public class Season {
	
	private int seasonNb;
	private String seasonName;
	private String summary;
	private LocalDate releaseDate;
	private double rating;
	private int seasonId;
	private int episodeCount;
	private int serieId;
	private String image;
	private List<Episode> episodes = new ArrayList<Episode>();

	public Season(int seasonId, int seasonNb, String seasonName, String summary, LocalDate releaseDate,
			int serieId, int episodeCount, String image, List<Episode> episodes) {
		super();
		this.seasonId = seasonId;
		this.seasonNb = seasonNb;
		this.seasonName = seasonName;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.serieId = serieId;
		this.episodeCount = episodeCount;
		this.image = image;
		this.episodes = episodes;
	}
	

	/**
	 * Getters & Setters
	 */

	public int getSeasonNb() {
		return seasonNb;
	}

	public void setSeasonNb(int seasonNb) {
		this.seasonNb = seasonNb;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
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

	public int getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
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
	
	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

}
