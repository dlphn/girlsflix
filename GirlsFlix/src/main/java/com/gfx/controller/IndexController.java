package com.gfx.controller;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * setting serialVersionUID to default value. Enables to deserialize Exceptions properly.
	 */
	private static final long serialVersionUID = 1L;

}

@Controller
public class IndexController {
	@Inject
	SerieFactory serieFactory = new SerieFactory();
	
	
	/**
	 * Controller to set the different elements of the Home Page
	 * When a user is connected, we display recommendations in addition to popular series
	 * 
	 * @param model
	 * @return Home Page
	 */
	@RequestMapping({"/index", "/"})
    public String index(ModelMap model) {
		if (UserService.currentUserLogin() != null) { //the user is authenticated
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			model.put("user", user);
			List<String> affinities = user.getAffinities();
			List<Serie> recommendations = new ArrayList<Serie>();
			List<Serie> result = new ArrayList<Serie>();
			for (String affinity : affinities) {
				recommendations.addAll(Data.pickNRandomSameGenre(3, affinity));
			}
			//remove duplicates
			for (Serie serie : recommendations) {
				if (!result.contains(serie)) {
					result.add(serie);
				}
			}
			model.put("recommendations", result);
			if (recommendations.size() == 0) {
				model.put("recommendationsMsg", "Indiquez vos préférences dans votre profil pour recevoir des recommandations :)");
			}
		}
		model.put("columns", Data.pickNRandom(9));
        return "index";
    }
	
	 @RequestMapping("/404")
	 public String quatre_cent_quatre() {
		 return "404";
	 }

	 @RequestMapping("/500")
	 public String cinq_cent() {
		 return "500";
	 }
	 
	/**
	 * Controller to construct the page to Search/Filter on all series
	 * 
	 * @param model
	 * @param search	the user query
	 * @param genre		the user selected genre
	 * @return All series or filtered series
	 */
	@RequestMapping(value = "/series", method = RequestMethod.GET)
	public String Search(ModelMap model, 
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "genre", required = false) String genre
	) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			model.put("user", user);
		}

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
			// no filter => return all series
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
	
	
	/**
	 * Contruct page for a single serie view
	 * 
	 * @param id of the serie
	 * @param model
	 * @return Serie information : image, title, seasons, episodes..
	 */
	@RequestMapping("/serie/{id}")
    public String serie(@PathVariable("id") String id, ModelMap model) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			model.put("user", user);
		}
        Serie serie = Data.getById(Integer.parseInt(id));
        if (serie == null) {
            throw new ResourceNotFoundException();
        } else {
        	// Add/Remove from favorites only available when the user is connected
        	if (UserService.currentUserLogin() != null) {
        		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
            	model.put("isFavorite", user.getFavorites().contains(Integer.parseInt(id)));
        	}
            model.put("serie", serie);
            //Badge to know whether there is an episode coming soon 
            model.put("isSoon", serie.isSoon());
            return "views/serie";
        }
    }
	
	
	/**
	 * Processing step, to add a serie to the List of Favorites of the connected user
	 * 
	 * @param id of serie
	 * @param model
	 * @return redirects to Serie page
	 */
	@RequestMapping("/serie/{id}/addFav")
	public String addFavoriteSerie (@PathVariable("id") int id, ModelMap model) {
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		model.addAttribute("user", user);
		UserService.addToFavorites(user, id);
		return "redirect:/serie/" + id;
	}
	
	/**
	 *Processing step, to remove a serie from the List of Favorites of the connected user
	 *
	 * @param id of serie
	 * @param model
	 * @return redirects to Serie page
	 */
	@RequestMapping("/serie/{id}/removeFav")
	public String removeFavoriteSerie(@PathVariable("id") int id, ModelMap model) {
		Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
		model.addAttribute("user", user);
		UserService.removeFromFavorites(user, id);
		return "redirect:/serie/" + id;
	}
	
	/**
	 * Construct a Random Serie page. If the user is connected, display whether it's a favorite serie.
	 * 
	 * @param model
	 * @return Page of a serie randomly chosen
	 */
	@RequestMapping("/serie-surprise")
	public String serieSurprise(ModelMap model) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			model.put("user", user);
		}
		model.put("series", Data.pickNRandom(1));
		Serie serie = Data.pickNRandom(1).get(0);
        if (serie == null) {
            throw new ResourceNotFoundException();
        } else {
        	if (UserService.currentUserLogin() != null) {
            	Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
            	model.addAttribute("user", user);
            	model.put("isFavorite", user.getFavorites().contains(serie.getId()));
        	}
            model.put("serie", serie);
            return "views/serie-surprise";
        }
    }
	
	/**
	 * Page of contact with our names and emails 
	 * 
	 * @param model
	 * @return Contact Page
	 */
	@RequestMapping("/contact")
	public String showContact(ModelMap model) {
		if (UserService.currentUserLogin() != null) {
			Enjoyer user = UserDB.getUser(UserService.currentUserLogin());
			model.put("user", user);
		}
		return "views/contact";
	}
}
