package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Enjoyer extends User implements Runnable{
	
	private List<Integer> favoriteSeriesId = new ArrayList<Integer>();
	private Visualization visualization;
	
	
	public Enjoyer(String pseudo) {
		super();
		setPseudo(pseudo);
	}
	
	public Enjoyer(String pseudo, Visualization visu) {
		super();
		setPseudo(pseudo);
		this.visualization = visu;
	}
	
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super(login, pseudo,password,firstName,lastName,gender);
	}
	
	public void addToFavorites(int idSerie) {
		boolean alreadyInFavorites = false;
		for (int id : favoriteSeriesId) {
			if(id == idSerie) {
				System.out.println("Cette série fait déjà partie de vos favoris");
				alreadyInFavorites = true;
			}
		}
		
		if(!alreadyInFavorites) {
			favoriteSeriesId.add(idSerie);
			System.out.println("la série " + idSerie + " a été ajoutée dans la liste de l'utilisateur " + this.getPseudo());
		}
	}
	
	public void removeFromFavorties(int idSerie) {
		if(favoriteSeriesId.contains(idSerie)) {
			favoriteSeriesId.remove(idSerie);
		}
	}
	
	public void run() {
		visualization.setEnjoyerLogged(this);
		visualization.showSeries();
	}
	
	public void save() {
		
	}

	public List<Integer> getFavoriteSeriesId() {
		return favoriteSeriesId;
	}

	public Visualization getVisualization() {
		return visualization;
	}

	public void setVisualization(Visualization visualization) {
		this.visualization = visualization;
	}

	public void setFavoriteSeriesId(List<Integer> favoriteSeriesId) {
		this.favoriteSeriesId = favoriteSeriesId;
	}
	
	
	
	
	

}
