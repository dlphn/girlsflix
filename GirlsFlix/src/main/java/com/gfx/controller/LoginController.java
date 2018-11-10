package com.gfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
}
