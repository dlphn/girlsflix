package com.gfx.domain.series;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gfx.domain.users.Enjoyer;

public class Serie {
	private int id;
	private String title;
	private List<String> serieGenres;
	private String summary;
	private LocalDate creationDate;
	private double rating;
	private boolean allowed = true;
	private String image;
	private List<Season> seasons = new ArrayList<Season>();
	private List<Enjoyer> enjoyersToNotify = new ArrayList<Enjoyer>();
	private int nextEpisodeOnAir;
	private int nbSeasonNEOA;
	private LocalDate dateNextEpisodeOnAir;
	private boolean nextEpisodeHasBeenNotified = false;
		
	public Serie(int id, String title, List<String> serieGenres, String summary, LocalDate creationDate, String image, double rating) {
		super();
		this.id = id;
		this.title = title;
		this.serieGenres = serieGenres;
		this.summary = summary;
		this.creationDate = creationDate;
		this.image = image;
		this.allowed = true;
		this.rating = rating;
	}
	

	public Serie(String name) {
		this.title = name;
	}
	
	public Serie(int id, List<Enjoyer> enjoyersToNotify) {
		// TODO for testing - to be removed
		this.id = id;
		this.enjoyersToNotify = enjoyersToNotify;
	}
	
	public Serie(int id, String title, LocalDate creationDate, String summary) {
		super();
		this.id = id;
		this.title = title;
		this.creationDate = creationDate;
		this.summary = summary;
		this.allowed = true;
		this.rating = 0;
	}
	
	public Serie(String title, LocalDate creationDate, String summary) {
		super();
		this.title = title;
		this.summary = summary;
		this.creationDate = creationDate;
		this.allowed = true;
		this.rating = 0;
	}
	
	public Serie(int id, String title, String summary, LocalDate creationDate, String image) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.creationDate = creationDate;
		this.image = image;
		this.allowed = true;
		this.rating = 0;
	}
	
 	public Serie(int id, String title, List<String> serieGenres, String summary, LocalDate creationDate, String image) {
		super();
		this.id = id;
		this.title = title;
		this.serieGenres = serieGenres;
		this.summary = summary;
		this.creationDate = creationDate;
		this.image = image;
		this.allowed = true;
		this.rating = 0;
	}
 	
	public Serie(int id, String title, List<String> serieGenres, String summary, LocalDate creationDate, String image, List<Season> seasons) {
		super();
		this.id = id;
		this.title = title;
		this.serieGenres = serieGenres;
		this.summary = summary;
		this.creationDate = creationDate;
		this.image = image;
		this.allowed = true;
		this.rating = 0;
		this.seasons = seasons;
	}
	
	public Serie(int id, String title, List<String> serieGenres, String summary, LocalDate creationDate, String image, List<Season> seasons, double rating) {
		this(id, title, serieGenres, summary, creationDate, image,seasons);
		this.rating = rating;
	}

	
	public String info() {
		return "this Series is called "+ this.title + " and was first on air the " + this.creationDate + ".\n its ID is: " + this.id + "\n";
	}
	
	public String display() {
		String result = "";
		result += "Title : " + this.title + "\n";
		result += "Summary : " + this.summary + "\n";
		result += "Creation Date : " + this.creationDate + "\n";
		result += "Genres : " + this.serieGenres.toString() + "\n";
		result += "Rating : " + this.rating + "\n";
		return result;
	}
	
	public void addSeason(Season season) {
		seasons.add(season);
	}
	
	public void updateAllAttributes(String title, List<String> serieType, String summary, LocalDate creationDate, String picture, List<Season> seasons, double rating, int newEpisode, int newSeason, LocalDate newDate) {
		if(!this.title.equals(title)) this.title = title;
		this.serieGenres = serieType;
		if(!this.summary.equals(summary)) this.summary = summary;
		this.creationDate = creationDate;
		if(!this.image.equals(picture)) this.image = picture;
		this.seasons = seasons;
		if(this.rating != rating) this.rating = rating;
		if(this.nextEpisodeOnAir != newEpisode) {
			this.nextEpisodeOnAir = newEpisode;
			this.nextEpisodeHasBeenNotified = false;
		}
		if(this.nbSeasonNEOA != newSeason) this.nbSeasonNEOA = newSeason;
		this.dateNextEpisodeOnAir = newDate;
	}

	
	/*******************/
	/*Getters & Setters*/
	/*******************/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getSerieGenres() {
		return serieGenres;
	}
	
	public void setSerieGenres(List<String> serieGenres) {
		this.serieGenres = serieGenres;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public List<Enjoyer> getEnjoyersToNotify() {
		return enjoyersToNotify;
	}

	public void setEnjoyersToNotify(List<Enjoyer> enjoyersToNotify) {
		this.enjoyersToNotify = enjoyersToNotify;
	}
  
	public int getNextEpisodeOnAir() {
		return nextEpisodeOnAir;
	}

	public void setNextEpisodeOnAir(int nextEpisodeOnAir) {
		this.nextEpisodeOnAir = nextEpisodeOnAir;
	}

	public int getNbSeasonNEOA() {
		return nbSeasonNEOA;
	}

	public void setNbSeasonNEOA(int nbSeasonNEOA) {
		this.nbSeasonNEOA = nbSeasonNEOA;
	}

	public LocalDate getDateNextEpisodeOnAir() {
		return dateNextEpisodeOnAir;
	}

	public void setDateNextEpisodeOnAir(LocalDate dateNextEpisodeOnAir) {
		this.dateNextEpisodeOnAir = dateNextEpisodeOnAir;
	}

	public boolean isNextEpisodeHasBeenNotified() {
		return nextEpisodeHasBeenNotified;
	}

	public synchronized void setNextEpisodeHasBeenNotified(boolean nextEpisodeHasBeenNotified) {
		this.nextEpisodeHasBeenNotified = nextEpisodeHasBeenNotified;
	}
}
