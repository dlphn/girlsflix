package com.gfx.domain.series;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Serie {
	private int id;
	private String title;
	private List<Integer> serieGenres;
	private String summary;
	private LocalDate creationDate;
	private int rating;
	private boolean allowed;
	private String image;
	private List<Season> seasons = new ArrayList<Season>();
	
	public Serie(int id, String title, List<Integer> serieGenres, String summary, LocalDate creationDate, String image) {
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
	
	public Serie(String name) {
		this.title = name;
	}
	
	public String info() {
		return "this Series is called "+ this.title + " and was first on air the " + this.creationDate + ".\n its ID is: " + this.id + "\n";
	}
	
	public void addSeason(Season season) {
		seasons.add(season);
	}
	
	public void show() {
		//fonction à appeler quand l'utilisateur sélectionnera une série et qu'on souhaitera afficher les détails de la série
		
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
	
	public List<Integer> getSerieGenre() {
		return serieGenres;
	}
	
	public void setSerieGenres(List<Integer> serieGenres) {
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
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

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

}
