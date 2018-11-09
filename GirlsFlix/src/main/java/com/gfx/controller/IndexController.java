package com.gfx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import java.lang.Thread;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.service.Scheduler;
import com.gfx.service.SerieFactory;
import com.gfx.service.UserDB;
import com.gfx.service.UserService;

 
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {

}

@Controller
public class IndexController {
	@Inject
	SerieFactory serieFactory = new SerieFactory();
	//Scheduler scheduler = new Scheduler(serieFactory);
	String message = "Welcome!";
	
	
	
	@RequestMapping({"/index", "/"})
    public String index(ModelMap model) {
		model.put("columns", Data.pickNRandom(9));
		System.out.println(UserService.currentUserLogin());

        return "index";
    }
	
	@RequestMapping(value = "/series", method = RequestMethod.GET)
	public String Search(ModelMap model, 
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "genre", required = false) String genre
	) {
		
		// render the filter values
		model.put("genres", Genre.getGenres());
	    model.put("search", search);
		String genreFilter = "";
		if (genre != null) {
			genreFilter = new String(genre).replaceAll("and", "&");
		}
	    model.put("genreFilter", genreFilter);
	    
	    List<Serie> titleResult = new ArrayList<Serie>();
	    List<Serie> genreResult = new ArrayList<Serie>();
		
	    // get result for title search
		if (search != null && search.length() > 0) {
			titleResult = Data.search(search);
		}
		// get result for genre filter
		if (genre != null && genre.length() > 0) {
			genreResult = Data.searchGenre(genreFilter);
		}
		
		if (search == null && genre == null) {
			// no filter
			model.put("series", Data.getListSeries());
		} else {
			if (search.length() > 0 && genre.length() == 0) {
				// search only on title
				model.put("series", titleResult);
			}
			if (search.length() == 0 && genre.length() > 0) {
				// filter only on genre
				model.put("series", genreResult);
			}
			if (search.length() > 0 && genre.length() > 0) {
				// filter on both title and genre
				titleResult.retainAll(genreResult);
				model.put("series", titleResult);
			}
		}

	    return "views/series";
	}
	
	@RequestMapping("/serie/{id}")
    public String serie(@PathVariable("id") String id, ModelMap model) {
        Serie serie = Data.getById(Integer.parseInt(id));
        if (serie == null) {
            throw new ResourceNotFoundException();
        }
        else {
        	if (UserService.currentUserLogin() != null) {
        		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
            	model.put("isFavorite", user.getFavorites().contains(Integer.parseInt(id)));
        	}
            model.put("serie", serie);
            return "views/serie";
        }
    }
	
	@RequestMapping("/serie/{id}/addFav")
	public String addFavoriteSerie (@PathVariable("id") int id, ModelMap model) {
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		UserService.addToFavorites(user, id);
		//model.put("message", user.getFavorites().contains(id) ? "La série a bien été ajoutée à vos favoris !" : "Oups, pourriez-vous rééssayer ?");
		//model.put("isFavorite", user.getFavorites().contains(id));
		//Serie serie = Data.getById(id);
		// model.put("serie", serie);
		return "redirect:/serie/" + id;
	}
	
	@RequestMapping("/serie/{id}/removeFav")
	public String removeFavoriteSerie(@PathVariable("id") int id, ModelMap model) {
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		UserService.removeFromFavorites(user, id);
		//model.put("message", user.getFavorites().contains(id) ? "Oups, pourriez-vous rééssayer ?" : "La série ne fait plus partie de vos favoris.");
		//model.put("isFavorite", user.getFavorites().contains(id));
		//Serie serie = Data.getById(id);
		//model.put("serie", serie);
		return "redirect:/serie/" + id;
	}
	
	@RequestMapping("/serie-surprise")
	public String serieSurprise(ModelMap model) {
		model.put("series", Data.pickNRandom(1));
		Serie serie = Data.pickNRandom(1).get(0);
        if (serie == null) {
            throw new ResourceNotFoundException();
        } else {
        	if (UserService.currentUserLogin() != null) {
            	Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
            	model.put("isFavorite", user.getFavorites().contains(serie.getId()));
        	}
            model.put("serie", serie);
            return "views/serie-surprise";
        }
    }
	
	@RequestMapping("/contact")
	public String showContact() {
		return "views/contact";
	}
	
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
}
