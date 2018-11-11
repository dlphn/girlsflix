package com.gfx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.service.UserDB;
import com.gfx.service.UserService;


/**
 * Manage pages only accessible when logged in
 */
@Controller
public class UserController {
	
	/**
	 * Favorites Page. If the user is connected, we fetch the List from the users DataBase and display it.
	 * 
	 * @param model
	 * @return login Page if the user is not connected. Favorites Page otherwise.
	 */
	@RequestMapping("/favoris")
	public String showFavorites(ModelMap model) {
		if (UserService.currentUserLogin() == null) {
			return "user/login";
		}
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		List<Serie> favoritesSeries = new ArrayList<Serie>();
		for (int fav:user.getFavorites()) {		
			favoritesSeries.add(Data.getById(fav));
		}
		model.addAttribute("favorites", favoritesSeries);
		//If the user has no favorite serie, we suggest to add some to his/her list.
		if (favoritesSeries.size() == 0) {
			model.put("message", "Vous n'avez pas encore de favoris.");
		}
		model.addAttribute("user", user);
		return "user/favorites";
	}
	
	/**
	 * Processing step for Serie removal from the user's favorites List from favorites page.
	 * 
	 * @param id of the Serie
	 * @param model
	 * @return Favorites Page, with the refreshed List of Favorites
	 */
	@RequestMapping("/favoris/remove/{id}")
	public String removeFavorite(@PathVariable("id") int id, ModelMap model) {
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		UserService.removeFromFavorites(user, id);
		model.addAttribute("user", user);
		return "redirect:/favoris";
	}

	/**
	 * Notifications wall for the connected user. The user can delete read Notifications.
	 * The Notifications inform the user on the coming episodes of her favorite series.
	 * 
	 * @param model
	 * @return User's notifications Page
	 */
	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public String showNotifications(ModelMap model) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			List<String> notifications = user.getNotifications();
			model.addAttribute("user", user);
			if (notifications.size() > 0) {
				model.put("notifications", notifications);
			} else { // If there are no unread notifications, we display a message saying so.
				model.put("message", "Vous n'avez aucune notification non lue.");
			}
		} else { // If the user is not connected, we redirect him/her to the login page
			return "user/login";
		}
		return "user/notifications";
	}
	
	/**
	 * Processing step to remove a notification from the Notifications List of the user.
	 * Refreshing of the web Page, but also of the DabaBase.
	 * 
	 * @param model
	 * @param removeId of the Serie
	 * @return Notifications Page refreshed
	 */
	@RequestMapping(value = "/notifications/remove", method = RequestMethod.GET)
	public String removeNotification(ModelMap model, 
			@RequestParam(value = "index", required = false) String removeId
	) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			
			if (removeId != null) {
				UserService.deleteNotification(user, Integer.parseInt(removeId));
			}
			model.addAttribute("user", user);
		}
		return "redirect:/notifications";
	}
	
	/**
	 * Profile Page enables the user to update the information we have, except login and password.
	 * In this step, we get the information the user changes.
	 * 
	 * @param model
	 * @param error
	 * @param success
	 * @return Profile Page with the Object Enjoyer corresponding to the connected user.
	 */
	@GetMapping("/profil")
	public String loadProfilePage(ModelMap model,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "success", required = false) String success) {
		if (error != null) {
			model.put("message", "Oups, un problème est survenu. Réessayez plus tard.");
		}
		if (success != null) {
			model.put("message", "Vos informations ont bien été modifiées.");
		}
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		    model.put("user", user);
		} else {
		    model.put("user", new Enjoyer());
		    model.put("message", "Oups, nous ne sommes pas parvenus à récupérer vos informations. Réessayez plus tard.");
		}
	    model.put("genres", Genre.getGenres());
	    return "/user/profile";
	}
	
	/**
	 * This step enables the update of the user's information in the dataBase when everything works fine.
	 * 
	 * @param model
	 * @param user the object Enjoyer fetched from the dataBase
	 * @return Profile Page with success or failure message.
	 */
	@RequestMapping(value = "/profil", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("user") Enjoyer user) {
		model.addAttribute("user", user);
		if (UserDB.update(user)) {
			return "redirect:/profil?success";
		} else {
			return "redirect:/profil?error";
		}
	}
}
