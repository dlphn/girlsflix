package com.gfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	/**
	 * This function is based on the view user/login in WEB-INF folder.
	 * The parameters login & Password are sent to SpringSecurity Authentication Bean, enabling the creation of a new session for the user.
	 * @param error
	 * @return Login page if there is an error, Home Page when the user logs in.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Le login et/ou mot de passe sont invalides.");
		}
		model.setViewName("user/login");

		return model;
	}
}
