package com.gfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gfx.domain.series.Genre;
import com.gfx.domain.users.Enjoyer;
import com.gfx.helper.LoginExistsException;
import com.gfx.service.UserDB;

@Controller
public class RegistrationController {
	
	/**
	 * Load of Registration page with the instanciation of a new Enjoyer
	 * @return Register Page with an Enjoyer created
	 */
	@GetMapping("/register")
		public ModelAndView loadRegisterPage() {
	    ModelAndView mav = new ModelAndView("user/register", "command", new Enjoyer());
	    mav.addObject("user", new Enjoyer());
 	    mav.addObject("genres", Genre.getGenres());
	    return mav;
	}
	
	/**
	 * 
	 * Controller enables to update the attributes of the Enjoyer created during the loadRegisterPage step.
	 * Checks if the new user attributes are compatible: Mandatory fields, no duplicate login compared to the users DataBase.
	 * @param model
	 * @param user
	 * @return Register page if there are errors (login already exists, Mandatory field not filled). Sum-up of the given information otherwise.
	 * @throws LoginExistsException
	 */
	@RequestMapping("/register")
		public String addUser(ModelMap model, @ModelAttribute("user") Enjoyer user) throws LoginExistsException {
		model.addAttribute("login", user.getLogin());
		model.addAttribute("pseudo", user.getPseudo());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("gender", user.getGender());
		model.addAttribute("affinities", user.getAffinities());
		model.addAttribute("password", user.getPassword());
		Boolean notUsed = UserDB.checkLoginNotUsed(user.getLogin());
		if (notUsed) {
			// If the login is not taken, add the user to the database and redirect to Success Page (sum-up of the form information).
			if (UserDB.insertOne(user)) {
				return "user/userSuccess";
			} //In case we have problems (connexion to the database for instance..)
			else {
				model.put("user", new Enjoyer());
				model.put("genres", Genre.getGenres());
				model.put("message" , "La création de compte a été interrompue. Pourriez-vous vous réinscrire ?");
				return "user/register";
			}
		} else {
		    model.put("user", new Enjoyer());
		    model.put("genres", Genre.getGenres());
		    model.put("message" , "Il existe déjà un compte avec le login : "  +  user.getLogin());
		    return "user/register";
	    }
	}
}
