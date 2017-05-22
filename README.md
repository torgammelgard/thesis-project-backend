# Thesis Project - Backend

## Documentation
Javadoc location,

`[project_path]/doc/index.html`

## Building
In order to build this project, clone the project,

`git clone https://github.com/torgammelgard/thesis-project-backend`

and use the gradle wrapper

`./gradlew build`  (on Mac)

`gradlew build`    (on Windows)

The 'build/libs/thesis.war'-file can then be deployed to a Tomcat 8 server.

### Building in Eclipse (Spring Tool Suite)
Run
`./gradlew eclipse`  (on Mac)

`gradlew eclipse`    (on Windows)

and add the module to an Apache 8 server and run.

Use *Buildship - Eclipse Plug-ins for Gradle*. Nice features to check out are *Update gradle project* and the gradle task *cleanEclipse*.

### Jpa settings
In jpa.properties, set

`hibernate.hbm2ddl.auto=create`, in order to create database tables.

And after the tables have been created,

`hibernate.hbm2ddl.auto=validate`, to only validate that they are in place.
 
### Database settings
The application tries to connect to the MySQL database through JDBC interface on, 

Port : `3306`

Username : `root`

Password : `root`

These setting can be changed in `persistence-hibernate.properties`.

## Technologies

Java 8, Spring 4, Spring Security, Spring Data, Hibernate, MySQL, Gradle, Thymeleaf, 
Bootstrap, Git, GitHub.

## Recommended software
Tested on a Mac with Tomcat 8.0.43, Spring Tool Suite 3.8.4.RELEASE.