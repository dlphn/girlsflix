# GirlsFlix

[![Java](https://img.shields.io/badge/java-v10.0.2-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![MySQL](https://img.shields.io/badge/mysql-v8.0.13-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![Apache Maven](https://img.shields.io/badge/maven-v3.5.4-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![Apache Tomcat](https://img.shields.io/badge/tomcat-v9.0.12-blue.svg?style=flat-square)](https://docs.python.org/3/)
[![API](https://img.shields.io/badge/api_provider-tmdb-orange.svg?style=flat-square)](https://www.themoviedb.org/documentation/api)


GirlsFlix is a web app that allows you to search for TV series and add your favorites so you never miss any new episode.

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

Add `Keys.java` in `src/main/java/com.gfx.helper` (ignored by Git).

```java
public class Keys {
	private final String apiKey = "${tmdb_api_key}";
	
	private final String mongoUser = "${mongo_user}";
	private final String mongoPwd = "${mongo_password}";
	private final String mongoHost = "${mongo_host}";
	private final String mongoDb = "girlsflix";
	
	private final String mysqlUser = "${mysql_user}";
	private final String mysqlPwd = "${mysql_password}";
	private final String mysqlHost = "${mysql_host}";
	private final String mysqlDb = "girlsflix";
	
	/***********/
	/* Getters */
	/***********/
}
```

### Dependencies

- Maven 3.5.4
- Tomcat 9.0.12 - download [latest](https://tomcat.apache.org/download-90.cgi)
- JDK 10.0.2 - download [latest](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- MySQL 8.0.13 - download [latest](https://dev.mysql.com/downloads/mysql/)

### Start MySQL server

Make sure that your MySQL is running.

Initiate database. [...]

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


### Start server from console (not working)

Use maven and tomcat to install and run the web app.

```bash
cd GirlsFlix
mvn clean install
mvn tomcat7:run
```

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

[project folder tree]

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Team

- Jihane Bennis | [jihane.bennis@student.ecp.fr](mailto:jihane.bennis@student.ecp.fr)  | [@JihaneBennis](https://github.com/JihaneBennis)
- Sophie Dambricourt | [sophie.dambricourt@student.ecp.fr](mailto:sophie.dambricourt@student.ecp.fr) | [@SophieKaramazov](https://github.com/SophieKaramazov)
- Delphine Shi | [delphine.shi@student.ecp.fr](mailto:delphine.shi@student.ecp.fr) | [@dlphn](https://github.com/dlphn)

