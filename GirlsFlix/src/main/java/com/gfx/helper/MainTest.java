package com.gfx.helper;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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
	    
		/*new SerieService();
		System.out.println(SerieService.getGenres());
		//service.init();*/
		
		
		
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
		
		SerieFactory serieFactory = new SerieFactory();
		Visualization visu = new Visualization(serieFactory.getSeries());
		List<Serie> result = visu.search("the walk");
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).info());
		}

	}
}
