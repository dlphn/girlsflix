package com.gfx.domain.users;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.gfx.domain.series.Notification;
import com.gfx.domain.series.Serie;
import com.gfx.service.Visualization;

public class Enjoyer extends User{
	
	private List<Integer> favorites = new ArrayList<Integer>();
	private List<Notification> notifications = new ArrayList<Notification>();
	
	public Enjoyer() {
		super();
	}
	
	public Enjoyer(String pseudo) {
		super();
		setPseudo(pseudo);
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
		Serie s = new Visualization().getById(id);
		s.getEnjoyersToNotify().add(this);
	}
	
	public void removeFromFavorties(Integer id) {
		favorites.remove(id);
		Serie s = new Visualization().getById(id);
		s.getEnjoyersToNotify().remove(this);
	}
	
	
	/**
	 * this method is called when an Episode of a Serie which is in favorites of
	 * the enjoyer will be on air soon
	 */
	public synchronized void notifyNextEpisodeOnAirSoon(Serie s) {
		Notification notification = new Notification(this, s.getId(), s.getTitle(), s.getNbSeasonNEOA(), s.getNextEpisodeOnAir(), s.getDateNextEpisodeOnAir());
		notifications.add(notification);
	}
	
	/**
	 * Display all notifications saved for the enjoyer
	 */
	public void displayAllNotifications() {
		System.out.println("Notifications de " + this.getPseudo() + ": \n") ;
		for (Notification notif : notifications) {
			if(notif.isRead()) {
				System.out.println("lue: ");
			}
			else {
				System.out.println("non lue: ");
			}
			System.out.println("l'épisode " + notif.getNbEpisode() + " de la saison " + notif.getNbSeason() + " de la série " + notif.getSerieTitle() + " sera diffusé le " + notif.getDateOnAir() + ".");
		}
		System.out.println("*****************");
	}
	
	/**
	 * Display only the notification not read by the enjoyer
	 */
	public void displayAllNotificationsUnread() {
		System.out.println("Notifications non lues de " + this.getPseudo() + ": \n") ;
		for (Notification notif : notifications) {
			if(!notif.isRead()) {	
				System.out.println("l'épisode " + notif.getNbEpisode() + " de la saison " + notif.getNbSeason() + " de la série " + notif.getSerieTitle() + " sera diffusé le " + notif.getDateOnAir() + ".");
			}
		}
		System.out.println("*****************");
	}
	
	/**
	 * Delete notification from the list of notifications of the enjoyer
	 * @param notif
	 */
	public void deleteNotif(Notification notif) {
		notifications.remove(notif);
	}
	
	/**
	 * Browse the list of notifications of the enjoyer and set to true the boolean
	 * isRead of the notification given in parameter
	 */
	public void readNotif(Notification notifToRead) {
		for(Notification notif : notifications) {
			if(notif.equals(notifToRead)) {
				notif.setRead(true);
			}
		}
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

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	
	

}
