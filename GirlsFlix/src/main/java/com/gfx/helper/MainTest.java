package com.gfx.helper;

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
		
//		SerieDB.connect();
//		List<Document> documents = SerieDB.find("series");
//		for (Document doc : documents) {
//			System.out.println(doc.toJson());
//		}
		
		UserDB.connect();
        //User newUser = new Enjoyer("test@test.com", "test23R", "pwd", "Charlie", "Chaplin", Gender.MALE);
        //UserDB.insertOne(newUser);
        //UserDB.readDatabase();
        System.out.println(UserDB.checkPwd("admin", "password"));
	    
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
