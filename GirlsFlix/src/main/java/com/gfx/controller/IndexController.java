package com.gfx.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.service.SerieFactory;
import com.gfx.service.UserDB;
import com.gfx.service.UserService;
 
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {

}

@Controller
public class IndexController {
	@Inject
    private SerieFactory serieFactory = new SerieFactory();
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
	    model.put("search", search);
		model.put("genres", Genre.getGenres());
		String genreFilter = "";
		if (genre != null) {
			genreFilter = new String(genre).replaceAll("and", "&");
		}
	    model.put("genreFilter", genreFilter);
		
		if (search != null) {
			model.put("series", Data.search(search));
		} else {
			if (genre != null) {
				model.put("series", Data.searchGenre(genreFilter));
			} else {
			    model.put("series", Data.getListSeries());
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
            model.put("serie", serie);
            return "views/serie";
        }
    }
	
	@RequestMapping("/serie-surprise")
	public String serieSurprise(ModelMap model) {
		model.put("series", Data.pickNRandom(1));

        return "views/serie-surprise";
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
		model.put("favorites", user.getFavorites());
		return "user/favorites";
	}

	@RequestMapping("/notifications")
	public String showNotifications(ModelMap model) {
		if (UserService.currentUserLogin() == null) {
			return "user/login";
		}
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		model.put("notifications", user.getNotifications());
		return "user/notifications";
	}
}
