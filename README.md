You can find the project on : https://github.com/D4RT4GNaN/library-web-service.git
# VirtualClimbing
P7 - DA JAVA Openclassroom - A community site around climbing



## Getting Started


You can get this project either by downloading it in ZIP file or cloning it.


This project works in duo with a web service that you will find [here](https://github.com/D4RT4GNaN/library-webapp.git).


To clone it, go to the folder of your choice in command line and use the following command :

```

$ git clone https://github.com/D4RT4GNaN/library-web-service.git

```
See deployment for notes on how to deploy the project on a live system.



### Prerequisites


You need to install :

* [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) version 9.X preferably

* [PostgreSQL](https://www.postgresql.org/download/), the version 11 is used for this project, but you can use the version you like!

* An IDE like [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) or [Eclipse JEE](https://www.eclipse.org/downloads/packages/release/2019-06/r/eclipse-ide-enterprise-java-developers)



### Installing

To start with this project :

1. Open it in IDE and setup a tomcat server, if it has not already been done.

2. In IDE, open **/src/main/resources/application.properties** and change data with your connection information to the database.

3. If you want, a demo data set is available in the folder. There are SQL scripts for creating tables and filling them.
Just open the request tool of PgAdmin and open the script *create_database_library.sql* first and then open the scripts *create_data_example.sql* later.



## Deployment


To deploy the project on Tomcat server, start by cleaning package (optional but advisor)

```

$ mvn clean

```

Continu by packaging the project

```

$ mvn package

```

Put the war which has just been created, into **apache-tomcat-X.X.XX/webapps**.

And finally, launch the tomcat server with the command

```

$ sh startup.sh

```

*You can found the script into the bin folder of the tomcat server*


The web service is normally accessible at [http://localhost:8080/libraryservice-webservice/](http://localhost:8080/libraryservice-webservice/)



## Built With


* [Maven](https://maven.apache.org/) - Dependency Management and Multi-module Management

* [Spring](https://spring.io/projects/spring-framework) - Used to manage the backend service like DAO and Manager



## Versioning


I use [Git](https://git-scm.com/) for versioning.



## Authors


* **Maxime Blaise** - [D4RT4GNaN](https://github.com/D4RT4GNaN)


