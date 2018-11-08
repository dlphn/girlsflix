package com.gfx.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.Config;

@Service
public class UserService {
	

	public static UserDetails currentUserDetails(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return principal instanceof UserDetails ? (UserDetails) principal : null;
	    }
	    return null;
	}
	
	public static String currentUserLogin(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : null;
	    }
	    return null;
	}
	
	public static void updateAffinities(Enjoyer user, List<String> affinities) {
		user.setAffinities(affinities);
		UserDB.update(user);
	}
	
	public static void updatePwd(Enjoyer user, String pwd) {
		user.setPassword(pwd);
		UserDB.update(user);
	}
	
	
	public static void addToFavorites(Enjoyer enjoyer, int id) {
		enjoyer.addToFavorites(id);
		Serie s = Data.getById(id);
		// TODO add an object with the enjoyer and a boolean False
		s.getEnjoyersToNotify().add(enjoyer);
		UserDB.update(enjoyer);
		// update series in MongoDB
		SerieDB.updateEnjoyers(s);
	}
	
	public static void removeFromFavorites(Enjoyer enjoyer, int id) {
		enjoyer.removeFromFavorites(id);
		Serie s = Data.getById(id);
		// TODO add the object with the enjoyer and the boolean
		s.getEnjoyersToNotify().remove(enjoyer);
		UserDB.update(enjoyer);
		// update series in MongoDB
		SerieDB.updateEnjoyers(s);
	}

	

	/**
	 * Adds a notification to the Enjoyer's notifications list for the next episode on air of 
	 * one of her favorite series
	 * 
	 * @param enjoyer	The enjoyer to notify
	 * @param serie		The series with a new episode coming soon
	 */
	public static synchronized void notifyNextEpisodeOnAirSoon(Enjoyer enjoyer, Serie serie) {
		//Notification notification = new Notification(this, s.getId(), s.getTitle(), s.getNbSeasonNEOA(), s.getNextEpisodeOnAir(), s.getDateNextEpisodeOnAir());
//		String notification = "L'épisode " + serie.getNextEpisodeOnAir()
//				+ " de la saison " + serie.getNbSeasonNEOA() 
//				+ " de la série " + serie.getTitle() 
//				+ " sera diffusé le " + serie.getDateNextEpisodeOnAir() + ".";
		String notification = serie.getTitle() + " : "
				+ "S" + serie.getNbSeasonNEOA()  + "E" + serie.getNextEpisodeOnAir()
				+ " diffusé le " + serie.getDateNextEpisodeOnAir() + " !";
		enjoyer.getNotifications().add(notification);
		UserDB.update(enjoyer);
	}
	
	
	/**
	 * Delete notification from the enjoyer's notifications list
	 * 
	 * @param enjoyer
	 * @param index 	notification to remove
	 */
	public static void deleteNotification(Enjoyer enjoyer, int index) {
		enjoyer.removeFromNotifications(index);
		UserDB.update(enjoyer);
	}
	
}
