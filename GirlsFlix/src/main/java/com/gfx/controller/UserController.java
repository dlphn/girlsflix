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


@Controller
public class UserController {
	
	@RequestMapping("/favoris")
	public String showFavorites(ModelMap model) {
		if (UserService.currentUserLogin() == null) {
			return "user/login";
		}
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		List<Serie> favoritesSeries = new ArrayList<Serie>();
		for(int fav:user.getFavorites()) {		
			favoritesSeries.add(Data.getById(fav));}
		model.addAttribute("favorites", favoritesSeries);
		return "user/favorites";
	}
	
	@RequestMapping("/favoris/remove/{id}")
	public String removeFavorite(@PathVariable("id") int id, ModelMap model) {
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		UserService.removeFromFavorites(user, id);
		return "redirect:/favoris";
	}

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public String showNotifications(ModelMap model) {
		if (UserService.currentUserLogin() == null) {
			return "user/login";
		}
		
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			model.put("notifications", user.getNotifications());
		} else {
			model.put("notifications", new ArrayList<String>());
		}
		return "user/notifications";
	}
	
	@RequestMapping(value = "/notifications/remove", method = RequestMethod.GET)
	public String removeNotification(ModelMap model, 
			@RequestParam(value = "index", required = false) String removeId
	) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			
			if (removeId != null) {
				UserService.deleteNotification(user, Integer.parseInt(removeId));
			}
		}
		return "redirect:/notifications";
	}
	
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
			// TODO Les préférences ne sont pas rendues correctement à cause d'espaces avant les valeurs
			System.out.println(user.toString());
		    model.put("user", user);
		} else {
		    model.put("user", new Enjoyer());
		    model.put("message", "Oups, nous ne sommes pas parvenus à récupérer vos informations. Réessayez plus tard.");
		}
	    model.put("genres", Genre.getGenres());
	    return "/user/profile";
	}
	
	@RequestMapping(value = "/profil", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("user") Enjoyer user) {
		if (UserDB.update(user)) {
			return "redirect:/profil?success";
		} else {
			return "redirect:/profil?error";
		}
	}
}
