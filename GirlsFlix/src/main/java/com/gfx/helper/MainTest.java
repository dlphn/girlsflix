package com.gfx.helper;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gfx.domain.series.Genre;
import com.gfx.domain.series.Serie;
import com.gfx.domain.users.Enjoyer;
import com.gfx.domain.users.Gender;
import com.gfx.domain.users.User;
import com.gfx.service.*;


public class MainTest {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		/* MySQL USers */
		//UserDB.connect();
        //User newUser = new Enjoyer("test@test.com", "test23R", "pwd", "Charli", "Chapli", Gender.MALE);
        //UserDB.insertOne(newUser);
        //UserDB.update(newUser);
		//List<Integer> fav = new ArrayList<Integer>();
		//fav.add(5);
		//UserDB.updateFav("test@test.com", fav);
        //UserDB.readDatabase();
        //System.out.println(UserDB.checkPwd("test@test.com", "pwd0"));
		//UserDB.updatePwd("test@test.com", "pwd0");
		//System.out.println(UserDB.checkLoginNotUsed("test@test.com"));
	    
		SerieService serieService = new SerieService();
		System.out.println(Genre.getGenres());
		
		/**
		 * Test for the notifications
		 */
		/*SerieFactory serieFactory = new SerieFactory();
		List<Serie> series = serieFactory.getSeries();
		for(Serie s :series) {
			System.out.println(s.getId());
		}
		Visualization visu = new Visualization(series);
		Enjoyer e1 = new Enjoyer("enjoyer1");
		
		Serie s1 = visu.getById(1416);
		Serie s2 = visu.getById(61889);
		Serie s3 = visu.getById(1403);
		
		s1.setDateNextEpisodeOnAir(LocalDate.now());
		s1.setNextEpisodeOnAir(18);
		s1.setNbSeasonNEOA(7);
		
		s2.setDateNextEpisodeOnAir(LocalDate.of(2018, Month.NOVEMBER, 15));
		s2.setNextEpisodeOnAir(3);
		s2.setNbSeasonNEOA(4);
		
		s3.setDateNextEpisodeOnAir(LocalDate.of(2018, Month.NOVEMBER, 2));
		s3.setNextEpisodeOnAir(8);
		s3.setNbSeasonNEOA(6);
		
		
		e1.addToFavorites(s1.getId());
		e1.addToFavorites(s2.getId());
		e1.addToFavorites(s3.getId());
		
		
		s1.notifyNextEpisodeOnAirSoon();
		s2.notifyNextEpisodeOnAirSoon();
		s1.notifyNextEpisodeOnAirSoon();
		s3.notifyNextEpisodeOnAirSoon();
		s3.notifyNextEpisodeOnAirSoon();
		
		try {
			Thread.sleep(5000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		e1.displayAllNotificationsUnread();*/
		/**End Test for notification*/
		
		
		
		
		
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
