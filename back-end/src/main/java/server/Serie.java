package server;

import java.time.LocalDate;
import java.util.ArrayList;

public class Serie {
	private int id;
	private String title;
	private TypeSerie typeSerie;
	private String summary;
	private LocalDate creationDate;
	private int rating;
	private boolean allowed;
	private String picture;
	private ArrayList<Season> seasons;
	
	public Serie(int id, String title, TypeSerie typeSerie, String summary, LocalDate creationDate, String picture) {
		super();
		this.id = id;
		this.title = title;
		this.typeSerie = typeSerie;
		this.summary = summary;
		this.creationDate = creationDate;
		this.picture = picture;
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
		return "this Series is called "+ this.title + " and was first on air the " + this.creationDate + "\n";
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
	
	public TypeSerie getTypeSerie() {
		return typeSerie;
	}
	
	public void setTypeSerie(TypeSerie typeSerie) {
		this.typeSerie = typeSerie;
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


	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ArrayList<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}

}
