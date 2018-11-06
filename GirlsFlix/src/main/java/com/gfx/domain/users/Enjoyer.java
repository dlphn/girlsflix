package com.gfx.domain.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Enjoyer extends User{

	private Gender gender;
	private List<String> affinities = new ArrayList<String>();
	private List<Integer> favorites = new ArrayList<Integer>();
	private List<String> notifications = new ArrayList<String>();
	
	public Enjoyer() {
		super();
		this.affinities=null;
		this.notifications=null;
		this.favorites=null;
	}
	
	public Enjoyer(String login) {
		super(login);
	}

	public Enjoyer (String login, String pseudo, String password) {
		super(login, pseudo, password);
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName) {
		super(login, pseudo, password, firstName);
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super(login, pseudo, password, firstName,lastName);
		this.gender = gender;
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender, List<String> affinities) {
		super(login, pseudo, password, firstName, lastName);
		this.gender = gender;
		this.affinities = affinities;
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
	
	@Override
	public String toString() {
		String result = "";
		result += super.toString();
		result += "Gender : " + this.gender.toString() + "\n";
		result += "Affinities : " + this.affinities.toString() + "\n";
		result += "Favorites : " + this.favorites + "\n";
		result += "Notifications : " + this.notifications + "\n";
		return result;
	}
	
	
	/**
	 * Display the notifications
	 */
	public void displayAllNotificationsUnread() {
		System.out.println("Notifications non lues de " + this.getPseudo() + " : \n") ;
		for (String notif : notifications) {
			System.out.println(notif);
		}
		System.out.println("*****************");
	}
	

	
	public void addToFavorites(Integer id) {
		if(favorites.contains(id)==false) {
		favorites.add(id);}
	}
	
	public void removeFromFavorites(Integer id) {
		if(favorites.contains(id)==true) {
		favorites.remove(id);}
	}
	
	public void removeFromNotifications(int index) {
		notifications.remove(index);
	}
	

}
