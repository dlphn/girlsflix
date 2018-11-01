package com.gfx.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gfx.domain.series.Data;
import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.service.SerieFactory;
 
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

        return "index";
    }
 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("views/helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/series")
    public String series(ModelMap model) {
		model.put("series", Data.getListSeries());
		model.put("genres", Genre.getGenres());

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
	public ModelAndView showContact() {
		ModelAndView mv = new ModelAndView("views/contact");
		return mv;
	}
}