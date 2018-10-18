package server;

import java.util.ArrayList;
import java.util.List;

public class Enjoyer extends User{
	
	private List<Integer> favorites = new ArrayList<Integer>();
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super(login, pseudo,password,firstName,lastName,gender);
	}
	
	public void addToFavorites(Serie s) {
		favorites.add(s);
	}
	
	public void removeFromFavorties(Serie s) {
		favorites.remove(s);
	}
	
	public void save() {
		
	}

	public List<Serie> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Serie> favorites) {
		this.favorites = favorites;
	}
	
	
	

}
