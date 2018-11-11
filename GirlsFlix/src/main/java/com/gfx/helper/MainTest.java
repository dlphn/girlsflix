package com.gfx.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.bson.Document;

import com.gfx.Config;
import com.gfx.domain.series.Data;
import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.domain.users.Gender;
import com.gfx.domain.users.User;
import com.gfx.service.*;


@SuppressWarnings("unused")
public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		// System.out.println(Gender.MALE.toString());
		/* MySQL USers */
		// UserDB.connect();
		//test
		/*Enjoyer Jihane = UserDB.getUser("loginé");
		System.out.println(Jihane.toString());
		List<String> notif = Arrays.asList("La série Beta3 va bientôt sortir", "La série Gamma2 est sortie !");
		List<Integer> fav = Arrays.asList(1,2,3,4,0,56);
		Jihane.setNotifications(notif);
		Jihane.setPseudo("Bibou");
		Jihane.setFavorites(fav);
		//Jihane.setPseudo("Jihane");
		System.out.println("************************");
		UserDB.update(Jihane);
		System.out.println("update : " + Jihane.toString());
		System.out.println("************************");
		UserDB.connect();
		Enjoyer Jihane2 = UserDB.getUser("loginé");
		System.out.println(Jihane2.getFavorites());
		System.out.println("************************");
		System.out.println("readDB : " + Jihane2.toString());
		System.out.println("************************");
		List<String> aff = Arrays.asList("Comédie", "Horreur", "Policier", "Mystère");
		Jihane2.setAffinities(aff);
		
		Serie serie = new Serie("Grey's Anatomy");
		serie.setId(421);
		serie.setNbSeasonNEOA(2);
		serie.setNextEpisodeOnAir(3);
		serie.setDateNextEpisodeOnAir(LocalDate.now());
		List<Serie> series = new ArrayList<Serie>();
		System.out.println("***** series ******" +series);
		series.add(serie);
		Data.setListSeries(series);
		UserService.addToFavorites(Jihane2, 421);
		UserService.notifyNextEpisodeOnAirSoon(Jihane2, serie);
		UserDB.update(Jihane2);
		System.out.println("Jihane2 modified Affinities" + Jihane2.toString());
		

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
		
		//new SerieFactory();
		//serieFactory.getSeasons(60735);
		//serieFactory.getEpisodes(60735, 1);

		/*List<Serie> series = Data.getListSeries();
		for (Serie s : series) {
			System.out.println(s.toString());
		}
		
		List<Serie> result = Data.searchGenre("Drame");
		System.out.println("Search results :");
		for (Serie s : result) {
			System.out.println(s.toString());
		}*/
		
		/**
		 * Test for recommendations
		 */
		/*new SerieFactory();
		List<Serie> result = Data.pickNRandomSameGenre(2, "Documentary");
		for (Serie s : result) {
			System.out.println(s.toString());
		}*/
    
		/*
		 * Test mongo update
		 */
		/*SerieDB.connect();
		Enjoyer e1 = new Enjoyer("enjoyer1");
		Enjoyer e2 = new Enjoyer("enjoyer2");
		Map<String, Boolean> enjoyers = new HashMap<String, Boolean>();
		enjoyers.put(e1.getLogin(), false);
		enjoyers.put(e2.getLogin(), true);
		Serie s = new Serie(60735, enjoyers); // Flash
		System.out.println(s.getEnjoyersToNotify());
		SerieDB.updateEnjoyers(s);*/
    
		/**
		 * Test for the notifications
		 */
		// for(Serie s : series) {
		// 	System.out.println(s.getId());
		// }
		/*Enjoyer e1 = new Enjoyer("enjoyer1");
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
		
		e1.displayAllNotificationsUnread();*/
		/**End Test for notification*/
		
		
		/**Test for scheduler**/
		/*Serie s1 = new Serie("Grey's Anatomy");
		instanciateSerie(s1, 10, LocalDate.of(2018, Month.NOVEMBER, 6), 3, 2);
		Serie s2 = new Serie("Suits");
		instanciateSerie(s2, 11, LocalDate.of(2018, Month.NOVEMBER, 8), 8, 9);
		Serie s3 = new Serie("Blindspot");
		instanciateSerie(s3, 13, LocalDate.of(2018, Month.DECEMBER, 31), 4, 7);
		
		List <Serie> list = new ArrayList<Serie>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		Data.setListSeries(list);
		
		Enjoyer e1 = new Enjoyer("enjoyer 1");
		Enjoyer e2 = new Enjoyer("enjoyer 2");
		
		UserService.addToFavorites(e1,10);
		UserService.addToFavorites(e2,10);
		UserService.addToFavorites(e1, 11);
		UserService.addToFavorites(e2,13);

		Thread scheduler = new Thread(new Scheduler());
		scheduler.start();
		
		try {
			Thread.sleep(15000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		Serie s4 = new Serie ("Nina");
		instanciateSerie(s4, 14, LocalDate.of(2018, Month.NOVEMBER, 7), 4, 11);
		list.add(s4);
		Data.setListSeries(list);
		
		UserService.addToFavorites(e2, 14);
		
		try {
			Thread.sleep(15000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("notifications de " + e1.getLogin());
		for (String notif: e1.getNotifications()) {
			System.out.println(notif);
		}
		System.out.println("notifications de " + e2.getLogin());
		for (String notif: e2.getNotifications()) {
			System.out.println(notif);
		}*/
		/** end Test for the scheduler**/
		
		//Scheduler scheduler = new Scheduler(new SerieFactory());
		//scheduler.start();
		
		//Enjoyer userToInsert = new Enjoyer("a", "pseudo", "pwd", "prénom");
		//UserDB.insertOne(userToInsert);
		//Enjoyer user = UserDB.getUser("a");
		//user.displayAllNotificationsUnread();
		
		
	}
	
	public static void instanciateSerie(Serie s, int id, LocalDate date, int nbEpisode, int nbSaison) {
		s.setId(id);
		s.setDateNextEpisodeOnAir(date);
		s.setNextEpisodeOnAir(nbEpisode);
		s.setNbSeasonNEOA(nbSaison);
	}
}
