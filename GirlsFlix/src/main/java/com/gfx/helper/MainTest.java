package com.gfx.helper;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gfx.domain.series.Data;
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
		// System.out.println(Gender.MALE.toString());
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
	    

		//SerieService service = new SerieService();
		//System.out.println(Genre.getGenres());

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
		
		new SerieFactory();
		//serieFactory.getSeasons(60735);
		//serieFactory.getEpisodes(60735, 1);
		List<Serie> series = Data.getListSeries();
    
    
    
		/**
		 * Test for the notifications
		 */
		// for(Serie s : series) {
		// 	System.out.println(s.getId());
		// }
		Enjoyer e1 = new Enjoyer("enjoyer1");
		UserDB.insertOne(e1);
		
		Serie s1 = Data.getById(1416);
		Serie s2 = Data.getById(61889);
		Serie s3 = Data.getById(1413);
		
		s1.setDateNextEpisodeOnAir(LocalDate.now());
		s1.setNextEpisodeOnAir(18);
		s1.setNbSeasonNEOA(7);
		
		s2.setDateNextEpisodeOnAir(LocalDate.of(2018, Month.NOVEMBER, 15));
		s2.setNextEpisodeOnAir(3);
		s2.setNbSeasonNEOA(4);
		
		s3.setDateNextEpisodeOnAir(LocalDate.of(2018, Month.NOVEMBER, 2));
		s3.setNextEpisodeOnAir(8);
		s3.setNbSeasonNEOA(6);
		
		
		UserService.addToFavorites(e1, s1.getId());
		UserService.addToFavorites(e1, s2.getId());
		UserService.addToFavorites(e1, s3.getId());
		
		
		UserService.notifyNextEpisodeOnAirSoon(s1);
		UserService.notifyNextEpisodeOnAirSoon(s2);
		UserService.notifyNextEpisodeOnAirSoon(s1);
		UserService.notifyNextEpisodeOnAirSoon(s3);
		UserService.notifyNextEpisodeOnAirSoon(s3);
		
		try {
			Thread.sleep(5000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		e1.displayAllNotificationsUnread();
		/**End Test for notification*/

	}
}
