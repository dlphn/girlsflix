package com.gfx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("user/register");
	    //mav.addObject("user", new Enjoyer());
	    return mav;
	}
	
	/*@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
		public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
		@ModelAttribute("user") User user) {
			UserDB.insertOne(user);
			return new ModelAndView("welcome", "firstname", user.getFirstName());
	}
*/
}
