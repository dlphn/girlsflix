package com.gfx.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;

/**
 * Methods to interact with User/Enjoyer instances
 */
@Service
public class UserService {
	
	public static String currentUserLogin(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : null;
	    }
	    return null;
	}
	
	/**
	 * Add the serie id in the user's favorites and add the user's login in the serie's enjoyersToNotify list
	 * Then update databases
	 * 
	 * @param enjoyer
	 * @param id
	 */
	public static void addToFavorites(Enjoyer enjoyer, int id) {
	    if (enjoyer.isEnabled()) {
	    	enjoyer.addToFavorites(id);
	    	Serie s = Data.getById(id);
	    	if (s.getEnjoyersToNotify() != null) {
	    		s.getEnjoyersToNotify().put(enjoyer.getLogin(), false);
	    	} else {
		        Map<String, Boolean> enjoyersToNotify = new HashMap<String, Boolean>();
		        enjoyersToNotify.put(enjoyer.getLogin(), false);
		        s.setEnjoyersToNotify(enjoyersToNotify);
	    	}
	    	UserDB.updateFavorites(enjoyer);
		    SerieDB.updateEnjoyers(s);
	    }
	}
	
	public static void removeFromFavorites(Enjoyer enjoyer, int id) {
		if (enjoyer.isEnabled()) {
			enjoyer.removeFromFavorites(id);
			Serie s = Data.getById(id);
			if (s.getEnjoyersToNotify() != null) {
				s.getEnjoyersToNotify().remove(enjoyer.getLogin());
			} else {
		        Map<String, Boolean> enjoyerToNotify = new HashMap<String, Boolean>();
		        s.setEnjoyersToNotify(enjoyerToNotify);
		    }
			UserDB.updateFavorites(enjoyer);
			SerieDB.updateEnjoyers(s);
		}
	}

	/**
	 * Add a notification to the Enjoyer's notifications list for the next episode on air of 
	 * one of her favorite series
	 * 
	 * @param enjoyer	The enjoyer to notify
	 * @param serie		The series with a new episode coming soon
	 */
	public static synchronized void notifyNextEpisodeOnAirSoon(String loginEnjoyer, Serie serie) {
		String notification = serie.getTitle() + " : "
				+ "S" + serie.getNbSeasonNEOA()  + "E" + serie.getNextEpisodeOnAir()
				+ " diffusé le " + serie.getDateNextEpisodeOnAir() + " !";
		Enjoyer enjoyer = UserDB.getUser(loginEnjoyer);
		enjoyer.getNotifications().add(notification);
		UserDB.updateNotifications(enjoyer);
		serie.setEnjoyerAsNotified(loginEnjoyer);
	}
	
	
	/**
	 * Delete notification from the enjoyer's notifications list
	 * 
	 * @param enjoyer
	 * @param index 	notification to remove
	 */
	public static void deleteNotification(Enjoyer enjoyer, int index) {
		enjoyer.removeFromNotifications(index);
		UserDB.updateNotifications(enjoyer);
	}
	
}
