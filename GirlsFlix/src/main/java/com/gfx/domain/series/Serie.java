package com.gfx.domain.series;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gfx.Config;
import com.gfx.service.SerieDB;


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
	private Map<String, Boolean> enjoyersToNotify;
	private int nextEpisodeOnAir;
	private int nbSeasonNEOA;
	private LocalDate dateNextEpisodeOnAir;
	

	public Serie(String name) {
		this.title = name;
	}
	
	public Serie(int id, String title, List<String> serieGenres, String summary, LocalDate creationDate, String image, double rating) {
		this.id = id;
		this.title = title;
		this.serieGenres = serieGenres;
		this.summary = summary;
		this.creationDate = creationDate;
		this.image = image;
		this.allowed = true;
		this.rating = rating;
	}
	
	public Serie(int id, String title, List<String> serieGenres, String summary, LocalDate creationDate, String image, double rating, List<Season> seasons) {
		this(id, title, serieGenres, summary, creationDate, image, rating);
		this.seasons = seasons;
	}
	
	public Serie(
			int id, 
			String title, 
			List<String> serieGenres, 
			String summary, 
			LocalDate creationDate, 
			String image, 
			double rating, 
			List<Season> seasons, 
			Map<String, Boolean> enjoyersToNotify) {
		this(id, title, serieGenres, summary, creationDate, image, rating, seasons);
		this.enjoyersToNotify = enjoyersToNotify;
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
	
	public Boolean isSoon() {
		Period period = Period.between(LocalDate.now(), this.getDateNextEpisodeOnAir());
		if (period.getDays() <= Config.nbDaysNotifBeforeDiff) {
			return true;
		}
		return false;
	}
	
	public void addSeason(Season season) {
		seasons.add(season);
	}
	
	public void updateAllAttributes(String title, List<String> serieType, String summary, LocalDate creationDate, String picture, double rating, List<Season> seasons, Map<String, Boolean> enjoyersToNotify, int newEpisode, int newSeason, LocalDate newDate) {
		this.title = title;
		this.serieGenres = serieType;
		this.summary = summary;
		this.creationDate = creationDate;
		this.image = picture;
		this.rating = rating;
		this.seasons = seasons;
		this.enjoyersToNotify = enjoyersToNotify;
		if (this.nextEpisodeOnAir != newEpisode) {
			// if the next episode on air has changed, we need to reset enjoyersToNotify
			for (String loginEnjoyer: this.enjoyersToNotify.keySet()) {
				this.enjoyersToNotify.remove(loginEnjoyer);
				this.enjoyersToNotify.put(loginEnjoyer, false);
			}
			SerieDB.updateEnjoyers(this);
		}
		this.nextEpisodeOnAir = newEpisode;
		this.nbSeasonNEOA = newSeason;
		this.dateNextEpisodeOnAir = newDate;
	}
	
	public Boolean isEnjoyerNotified(String login) {
		return this.enjoyersToNotify.get(login);
	}
	
	public void setEnjoyerAsNotified(String login) {
		this.enjoyersToNotify.put(login, true);
		SerieDB.updateEnjoyers(this);
	}
	
	public void setEnjoyerAsNotNotified(String login) {
		this.enjoyersToNotify.put(login, false);
		SerieDB.updateEnjoyers(this);
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

	public Map<String,Boolean> getEnjoyersToNotify() {
		return enjoyersToNotify;
	}

	public void setEnjoyersToNotify(Map<String, Boolean> enjoyersToNotify) {
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

}
