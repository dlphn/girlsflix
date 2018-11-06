package com.gfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gfx.domain.users.Enjoyer;
import com.gfx.helper.LoginExistsException;
import com.gfx.service.UserDB;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error
	) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Le login et/ou mot de passe sont invalides.");
		}
		model.setViewName("user/login");

		return model;
	}
	
	/*@RequestMapping( "/login")
	public String connectUser(ModelMap model, @ModelAttribute("user") Enjoyer user) throws LoginExistsException {
		String msg = "";
		Enjoyer loggedInUser = UserDB.checkPwd(user.getLogin(), user.getPassword());//Si le compte existe on instancie l'objet user
		if (loggedInUser != null) {
			msg = "Connexion réussie";
			model.put("msg", msg);
			model.put("login", user.getLogin());
			model.put("password", user.getPassword());
			return "views/welcome";
		} else {
			msg = "Le login et/ou Mot de passe sont invalides.";
			model.put("msg", msg);
			return "user/login";
		}
	
	}*/
}
