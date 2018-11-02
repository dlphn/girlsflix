package com.gfx.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.gfx.domain.series.Notification;
import com.gfx.domain.series.Serie;
import com.gfx.service.Visualization;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Enjoyer extends User{
	
	private List<Integer> favorites = new ArrayList<Integer>();
	private List<String> notifications = new ArrayList<String>();
	
	public Enjoyer() {
		super();
	}
	
	public Enjoyer(String pseudo) {
		super();
		setPseudo(pseudo);
	}

	public Enjoyer (String login, String pseudo, String password) {
		super(login, pseudo, password);
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName) {
		super(login, pseudo, password, firstName);
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender) {
		super(login, pseudo, password, firstName,lastName, gender);
	}
	
	public Enjoyer (String login, String pseudo, String password, String firstName, String lastName, Gender gender, List<Integer> favorites) {
		super(login, pseudo, password, firstName, lastName, gender);
		this.favorites = favorites;
	}

	/**
	 * Getters and Setters
	 */

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
	
	public String toString() {
		String result = "";
		result += super.toString();
		result += "Favorites : " + this.favorites + "\n";
		// result += "Notifications : " + this.notifications + "\n";
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
		favorites.add(id);
	}
	
	public void removeFromFavorites(Integer id) {
		favorites.remove(id);
	}
	

}
