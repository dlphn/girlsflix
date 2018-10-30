package com.gfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gfx.domain.series.TypeSerie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.domain.users.Gender;
import com.gfx.helper.LoginExistsException;
import com.gfx.service.UserDB;

@Controller
public class RegistrationController {
	
	/*@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView Registration() {
		return new ModelAndView("user/register", "command", new Enjoyer());
	}
	
	@RequestMapping(value = "/addEnjoyer", method = RequestMethod.POST)
	public String AddEnjoyer(@ModelAttribute("GirlsFlix") Enjoyer user, ModelMap model) {
		model.addAttribute("login", user.getLogin());
		model.addAttribute("pseudo", user.getPseudo());
		return "userSuccess";
	}*/
	
	
	@GetMapping("/register")
		public ModelAndView loadRegisterPage() {
	    ModelAndView mav = new ModelAndView("user/register", "command", new Enjoyer());
	    mav.addObject("user", new Enjoyer());
	    mav.addObject("serieTypesList", TypeSerie.values());
	    //mav.addObject("genderTypes", Gender.values());
	    return mav;
	}
	
	@RequestMapping( "/register")
		public String addUser(ModelMap model, @ModelAttribute("user") Enjoyer user) throws LoginExistsException {
		model.addAttribute("login", user.getLogin());
		model.addAttribute("pseudo", user.getPseudo());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		//TODO: Voir pb avec gender -> Erreur État HTTP 400 – Bad Request même si la variable Gender est bien instanciée.
		model.addAttribute("gender", user.getGender());
		model.addAttribute("affinities", user.getAffinities());
		UserDB.connect();
		Boolean notUsed = UserDB.checkLoginNotUsed(user.getLogin());
		System.out.println("**********************$");
		System.out.println("Login déjà utilisé ? " + user.getLogin());
		if(notUsed) {
			UserDB.insertOne(user);
			UserDB.update(user);
			UserDB.updateFav(user.getLogin(), user.getAffinities());
			}
		else {throw new LoginExistsException(
	              "Il existe déjà un compte avec le login:"  +  user.getLogin());
	        }
		return "user/userSuccess";
	}
	
	
	
	/*@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("views/helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;*/
	//}
	
	/*@ModelAttribute("affinities")
	   public List<TypeSerie> getserieTypesList() {
	      List<TypeSerie> serieTypesList = new ArrayList<TypeSerie>();
	      serieTypesList.add(TypeSerie.valueOf("ACTION"));
	      serieTypesList.add(TypeSerie.valueOf("ANIMATION"));
	      serieTypesList.add(TypeSerie.valueOf("ADVENTURE"));
	      serieTypesList.add(TypeSerie.valueOf("COMEDY"));
	      serieTypesList.add(TypeSerie.valueOf("CRIME"));
	      serieTypesList.add(TypeSerie.valueOf("DOCUMENTARY"));
	      serieTypesList.add(TypeSerie.valueOf("DRAMA"));
	      serieTypesList.add(TypeSerie.valueOf("FAMILY"));
	      serieTypesList.add(TypeSerie.valueOf("FANTASTIC"));
	      serieTypesList.add(TypeSerie.valueOf("WAR"));
	      serieTypesList.add(TypeSerie.valueOf("HISTORY"));
	      serieTypesList.add(TypeSerie.valueOf("HORROR"));
	      serieTypesList.add(TypeSerie.valueOf("MUSICAL"));
	      serieTypesList.add(TypeSerie.valueOf("MYSTERY"));
	      serieTypesList.add(TypeSerie.valueOf("ROMANCE"));
	      serieTypesList.add(TypeSerie.valueOf("SCIENCEFICTION"));
	      serieTypesList.add(TypeSerie.valueOf("THRILLER"));
	      serieTypesList.add(TypeSerie.valueOf("WESTERN"));
	      return serieTypesList;
	   }*/


}
