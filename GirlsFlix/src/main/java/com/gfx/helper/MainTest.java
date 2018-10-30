package com.gfx.helper;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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
		System.out.println("login not used ? " + UserDB.checkLoginNotUsed("testhjk3@test.com"));
        User newUser = new Enjoyer("test2457893@test.com", "test23R", "pwd", "Charli", "Chapli", Gender.MALE);
        if(UserDB.checkLoginNotUsed(newUser.getLogin())) {
        UserDB.insertOne(newUser);
        UserDB.update(newUser);
		List<TypeSerie> fav = new ArrayList<TypeSerie>();
		fav.add(TypeSerie.ACTION);
		UserDB.updateFav(newUser.getLogin(), fav);
        UserDB.readDatabase();
        System.out.println("check pwd == pwd0" + UserDB.checkPwd(newUser.getPassword(), "pwd0"));
		UserDB.updatePwd(newUser.getLogin(), "pwd0");
        }
        else {System.out.println("Please choose another login value");}
	    
		/*new SerieService();
		System.out.println(SerieService.getGenres());
		//service.init();
		
		
		
		System.out.println("\n"+"**********************************"+"\n");
		SerieFactory factoryTest = new SerieFactory();
		
		System.out.println("\n"+"**********************************"+"\n");
		List<Serie> series = factoryTest.getSeries();
		System.out.println("\n"+"**********************************"+"\n");
		System.out.println("result of json series : " + series.toString() +"\n");
		System.out.println("**********************************"+"\n");
		Visualization visu = new Visualization();
		visu.showSeries(); // before update
		
		visu.update(factoryTest);
		visu.showSeries(); // after udpate
		visu.getListSeries();*/

	}
}
