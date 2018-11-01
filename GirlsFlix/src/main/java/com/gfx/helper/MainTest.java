package com.gfx.helper;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.domain.series.TypeSerie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.domain.users.Gender;
import com.gfx.domain.users.User;
import com.gfx.service.*;


public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		System.out.println(Gender.MALE.toString());
		/* MySQL USers */
		UserDB.connect();
/*
		System.out.println("login not used ? " + UserDB.checkLoginNotUsed("testhjk3@test.com"));
        User newUser = new Enjoyer("Sandra@test.com", "test23R", "pwd", "Charli", "Chapli", Gender.MALE);
        if(UserDB.checkLoginNotUsed(newUser.getLogin())) {
        UserDB.insertOne(newUser);
        UserDB.update(newUser);
		List<TypeSerie> fav = new ArrayList<TypeSerie>();
		fav.add(TypeSerie.ACTION);
		UserDB.updateFav(newUser.getLogin(), fav);

		// System.out.println("login not used ? " + UserDB.checkLoginNotUsed("testhjk3@test.com"));
        User newUser = new Enjoyer("thello", "hi", "pwd", "Paulo");
        //if(UserDB.checkLoginNotUsed(newUser.getLogin())) {
        //UserDB.insertOne(newUser);
        //UserDB.update(newUser);
//        List<TypeSerie> aff = new ArrayList<TypeSerie>();
//        aff.add(TypeSerie.ACTION);
//		UserDB.updateAffinities(newUser.getLogin(), aff);
//        List<Integer> fav = new ArrayList<Integer>();
//        fav.add(1);
//        fav.add(4);
//        fav.add(98);
//		UserDB.updateFav(newUser.getLogin(), fav);
//        List<String> notif = new ArrayList<String>();
//      	notif.add("Prochain épisode de la saison 7 de Grey's Anatomy le 12-10-2018");
//      	notif.add("Prochain épisode de la saison 2 de Flash le 22-12-2018");
//      	UserDB.updateNotifications(newUser.getLogin(), notif);
        //UserDB.readDatabase();
        User user = UserDB.checkPwd("thello", "pwd");
        System.out.println(user.toString());
        //System.out.println("check pwd == pwd0" + UserDB.checkPwd(newUser.getPassword(), "pwd0"));
		//UserDB.updatePwd(newUser.getLogin(), "pwd0");
        //}
        //else {System.out.println("Please choose another login value");}
	    
		SerieService service = new SerieService();
		System.out.println(Genre.getGenres());
		//service.init();
		
		
		
		/*System.out.println("\n"+"**********************************"+"\n");
		SerieFactory factoryTest = new SerieFactory();
		List<Serie> series = factoryTest.getSeries();
		System.out.println("\n"+"**********************************"+"\n");
		System.out.println("result of json series : " + series.toString() +"\n");
		System.out.println("**********************************"+"\n");
		Visualization visu = new Visualization();
		visu.update(factoryTest);
		visu.showSeries(); // after udpate
		visu.getListSeries();*/
		
		/*SerieFactory serieFactory = new SerieFactory();
		Visualization visu = new Visualization(serieFactory.getSeries());
		List<Serie> result = visu.search("the walk");
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).info());
		}*/

	}
}
