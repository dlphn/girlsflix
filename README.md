# GirlsFlix

[![Java](https://img.shields.io/badge/java-v10.0.2-blue.svg?style=flat-square)](https://www.oracle.com/technetwork/java/javase/documentation/index.html)
[![Apache Maven](https://img.shields.io/badge/maven-v3.5.4-blue.svg?style=flat-square)](http://maven.apache.org/)
[![Apache Tomcat](https://img.shields.io/badge/tomcat-v9.0.12-blue.svg?style=flat-square)](https://tomcat.apache.org/)
[![API](https://img.shields.io/badge/api_provider-tmdb-orange.svg?style=flat-square)](https://www.themoviedb.org/documentation/api)


> GirlsFlix is a web app that allows you to search for TV series and add your favorites so you never miss any new episode.


<p align="center"><img src="./images/GirlsFlix.png" alt="GirlsFlix" width="200"/></p>

## Description
It is a Java school project developed by 3 students from CentraleSupélec : Jihane, Sophie, and Delphine as part of the POOA Module.

### Features
The GirlsFlix web app allows you to:

- :tv: Browse through TV series
- :mag: Search for specific series and filter by genre
- :page_facing_up: Get details about a series
- :bust_in_silhouette: Log in to your profile and customize it
- :star2: Add your favorites
- :mailbox_with_mail: Get a notification when a new episode of one of your favorites series will be aired soon
- :gift: Be surprised with a random popular series suggestion
- :speech_balloon: Get recommendations based on your preferred genres

## Installation
### Settings
Add `Keys.java` in `src/main/java/com.gfx` (ignored by Git).

```java
package com.gfx;

public class Keys {
	public static final String apiKey = "${tmdb_api_key}";
	
	public static final String mongoUser = "${mongo_user}";
	public static final String mongoPwd = "${mongo_password}";
	public static final String mongoHost = "${mongo_host}";
	public static final String mongoDb = "${mongo_db}";
	
	public static final String mysqlUser = "${mysql_user}";
	public static final String mysqlPwd = "${mysql_password}";
	public static final String mysqlHost = "${mysql_host}";
	public static final String mysqlDb = "${mysql_db}";
}
```

### Dependencies
- Maven 3.5.4
- Tomcat 9.0.12 - download [latest](https://tomcat.apache.org/download-90.cgi)
- JDK 10.0.2 - download [latest](https://www.oracle.com/technetwork/java/javase/downloads/index.html)


### Start server on Eclipse
Eclipse IDE for `Java EE` Web Developers (`Photon v4.9.0`) - download [link](http://www.eclipse.org/downloads/packages/).

[Add Apache Tomcat to Eclipse](https://crunchify.com/step-by-step-guide-to-setup-and-install-apache-tomcat-server-in-eclipse-development-environment-ide/)

#### Import project to Eclipse
`File -> Import...`

`Maven -> Existing Maven Projects -> Next`

`Browse -> Finish`

Right Click on `Project -> Maven -> Update Maven Project -> Force Update of Snapshots/Releases`

#### Build project
Right Click on `Project -> Run As -> Maven Build...`

`Add Goals`: `clean install`. Click `Apply` and `Run`.

#### Run on server
Right Click on `Project -> Run As -> Run on Server`


You can then go to [localhost:8080/GirlsFlix](http://localhost:8080/GirlsFlix) and voilà! :tada:

#### Issues you might face while starting the server

##### 1. 'Starting Tomcat v9.0 Server at localhost' has encountered a problem.
<p align="center"><img src="./images/timeout.png" alt="" width="300"/></p>

If you have a bad internet connection, fetching all the series from the database might take more than 45s so you will want to increase start up time.

Open the Servers view -> double click tomcat -> drop down the timeouts section -> increase Start time

<p align="center"><img src="./images/tomcat.png" alt=""/></p>


## Project structure
Technology stack:

- Java
- [Maven](http://maven.apache.org/)
- [Tomcat](http://tomcat.apache.org/)
- [Spring](http://spring.io/projects/spring-framework)
- [MongoDB](https://www.mongodb.com/)
- [MySQL](https://www.mysql.com/)
- [Bootstrap](https://getbootstrap.com/)
- [jQuery](http://jquery.com/)

### Project structure

```
src
└─ main
   ├─ java								// nos classes java
   |  └─ com
   |     └─ gfx							// com.gfx notre package de base
   |        ├─ controller				// nos controllers
   |        ├─ domain					// classes qui définissent notre domaine
   |        |  ├─ series				// classes qui définissent les séries
   |        |  └─ users					// classes qui définissent les utilisateurs
   |        ├─ helper					// classes utilitaires
   |        ├─ service					// les services
   |        ├─ Config.java				// configation générale
   |        ├─ Keys.java				// clés et mots de passe
   |        ├─ SpringConfig.java		// la configation de spring
   |        └─ SpringSecurity.java	// les profils d'éxécution
   └─ webapp								// les fichiers qui vont à la racine de l'application web
      ├─ css
      ├─ js
      ├─ lib
      └─ WEB-INF
         ├─ jsp							// les templates jsp
         ├─ tags							// les tag files (morceaux de template)
         ├─ dispatcher-servlet.xml	// configuration web de spring
         └─ web.xml						// configuration web d'une application
pom.xml									// fichier de configuration de maven
```

### Spring MVC

### Spring Security

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Team
- Jihane Bennis | [jihane.bennis@student.ecp.fr](mailto:jihane.bennis@student.ecp.fr)  | [@JihaneBennis](https://github.com/JihaneBennis)
- Sophie Dambricourt | [sophie.dambricourt@student.ecp.fr](mailto:sophie.dambricourt@student.ecp.fr) | [@SophieKaramazov](https://github.com/SophieKaramazov)
- Delphine Shi | [delphine.shi@student.ecp.fr](mailto:delphine.shi@student.ecp.fr) | [@dlphn](https://github.com/dlphn)

