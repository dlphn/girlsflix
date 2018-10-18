package server;

import java.util.ArrayList;
import java.util.List;

public class Enjoyer extends User{
	
	private List<Integer> favorites = new ArrayList<Integer>();
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super(login, pseudo,password,firstName,lastName,gender);
	}
	
	public void addToFavorites(Integer id) {
		favorites.add(id);
	}
	
	public void removeFromFavorties(Integer id) {
		favorites.remove(id);
	}
	
	public void save() {
		
	}

	public List<Integer> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Integer> favorites) {
		this.favorites = favorites;
	}
	
	
	

}
