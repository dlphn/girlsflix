# GirlsFlix

[![Java](https://img.shields.io/badge/java-v10.0.2-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![MySQL](https://img.shields.io/badge/mysql-v8.0.13-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![Apache Maven](https://img.shields.io/badge/maven-v3.5.4-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![Apache Tomcat](https://img.shields.io/badge/tomcat-v9.0.12-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![API](https://img.shields.io/badge/api_provider-tmdb-orange.svg?style=flat-square)](https://www.themoviedb.org/documentation/api)


> GirlsFlix is a web app that allows you to search for TV series and add your favorites so you never miss any new episode.

## Description
It is a Java school project developed by 3 students from CentraleSupélec : Jihane, Sophie, and Delphine as part of the POOA Module.

### Features
The GirlsFlix web app allows you to:

- :tv: Browse through TV series
- :mag: Search for specific series and filter by genre
- :page_facing_up: Get details about a series
- :bust_in_silhouette: Log in to your profile
- :star2: Add your favorites
- :mailbox_with_mail: Get a notification when a new episode of one of your favorites series is up
- :gift: Be surprised with a random popular series suggestion

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
	public static final String mongoDb = "girlsflix";
	
	public static final String mysqlUser = "${mysql_user}";
	public static final String mysqlPwd = "${mysql_password}";
	public static final String mysqlHost = "localhost:3306";
	public static final String mysqlDb = "girlsflix";
}
```

### Dependencies
- Maven 3.5.4
- Tomcat 9.0.12 - download [latest](https://tomcat.apache.org/download-90.cgi)
- JDK 10.0.2 - download [latest](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- MySQL 8.0.13 - download [latest](https://dev.mysql.com/downloads/mysql/)

### Start MySQL server
:warning: Make sure that your MySQL is running.

#### Initiate database
```sql
CREATE USER '${mysql_user}'@'localhost' IDENTIFIED WITH mysql_native_password BY '${mysql_password}';

CREATE DATABASE girlsflix;

GRANT ALL ON girlsflix.* TO '${mysql_user}'@'localhost';

USE girlsflix;

CREATE TABLE users (
	login VARCHAR(45) NOT NULL,
	pseudo VARCHAR(45) NOT NULL,
  	password VARCHAR(45) NOT NULL,
  	enabled TINYINT NOT NULL DEFAULT 1,
  	firstname VARCHAR(45) NULL,
	lastname VARCHAR(45) NULL,
	gender VARCHAR(45) NULL,
	favorites VARCHAR(100) NULL,
	notifications VARCHAR(255) NULL,
	affinities VARCHAR(255) NULL,
	PRIMARY KEY (login)
);

CREATE TABLE user_roles (
	user_role_id int(11) NOT NULL AUTO_INCREMENT,
	login varchar(45) NOT NULL,
	role varchar(45) NOT NULL,
	PRIMARY KEY (user_role_id),
	UNIQUE KEY uni_login_role (role, login),
	KEY fk_login_idx (login),
	CONSTRAINT fk_login FOREIGN KEY (login) REFERENCES users (login)
);
```

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

