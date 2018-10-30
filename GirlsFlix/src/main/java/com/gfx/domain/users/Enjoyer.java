package com.gfx.domain.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Enjoyer extends User{
	
	private List<Integer> favorites = new ArrayList<Integer>();
	
	public Enjoyer() {
		super();
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super(login, pseudo,password,firstName,lastName,gender);
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender, List<Integer> favorites) {
		super(login, pseudo,password,firstName,lastName,gender);
		this.favorites = favorites;
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
