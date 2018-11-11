package com.gfx.domain.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Enjoyer extends User{

	private Gender gender;
	private boolean enabled;
	private List<String> affinities;
	private List<Integer> favorites;
	private List<String> notifications;
	
	public Enjoyer() {
		super();
		this.affinities = new ArrayList<String>();
		this.favorites = new ArrayList<Integer>();
		this.notifications = new ArrayList<String>(0);
		this.enabled = true;
	}
	
	public Enjoyer (
			String login, 
			String pseudo, 
			String password, 
			String firstName, 
			String lastName, 
			Gender gender, 
			List<String> affinities, 
			List<Integer> favorites, 
			List<String> notifications) {
		super(login, pseudo, password, firstName, lastName);
		this.gender = gender;
		this.affinities = affinities;
		this.favorites = favorites;
		this.notifications = notifications;
	}

	/**
	 * Getters and Setters
	 */


	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<String> getAffinities() {
		return affinities;
	}

	public void setAffinities(List<String> affinities) {
		this.affinities = affinities;
	}
	
	public List<Integer> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Integer> favorites) {
		this.favorites = favorites;
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addToFavorites(Integer id) {
		if(favorites.contains(id)== false && enabled) favorites.add(id);
	}
	
	public void removeFromFavorites(Integer id) {
		if(favorites.contains(id)==true && enabled) favorites.remove(id);
	}
	
	public void removeFromNotifications(int index) {
		notifications.remove(index);
	}
	

}
