package com.gfx.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class IndexController {
	String message = "Welcome!";
	
	@RequestMapping({"/index", "/"})
    public String index(ModelMap model) {
		List<JSONObject> series = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();
		obj.put("id", 1);
		obj.put("title", "Série test");
		obj.put("intro", "Série test intro");
		JSONObject obj2 = new JSONObject();
		obj2.put("id", 2);
		obj2.put("title", "Série test 2");
		obj2.put("intro", "Série test intro 2");
		series.add(obj2);
        // model.put("columns", randomColumns());
		model.put("columns", series);

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
		List<JSONObject> series = new ArrayList<JSONObject>();
		JSONObject obj = new JSONObject();
		obj.put("id", 1);
		obj.put("title", "Série test");
		obj.put("intro", "Série test intro");
		JSONObject obj2 = new JSONObject();
		obj2.put("id", 2);
		obj2.put("title", "Série test 2");
		obj2.put("intro", "Série test intro 2");
		series.add(obj2);

        model.put("series", series);

        return "views/series";
    }
}