Open Parliament API
=============================

Demo website can be found at [http://147.91.128.71:9090/parlament](http://147.91.128.71:9090/parlament).

##Overview

**Open Parliament Data** is a project aiming at opening to the public data from the Parliament of the Republic of Serbia about its members, political parties, sessions and speeches.

In order to make the data from the Serbian Parliament available to the wider audience, we have created an API that can be utilized freely by everyone. This enables any programmer, data analyst, news reporter or any other interested party to easily access the data that was previously siloed in databases, documents and internal records of the Serbian Parliament.

##Installation

In this section, you will find all of the information you need to deploy this project.

###Prerequisites

To be able to use this project, you will need to have installed:
 - [JAVA 1.8](http://www.java.com/en/)
 - [Apache Maven](https://maven.apache.org/)
 - [Apache Tomcat](http://tomcat.apache.org/) or any other Java application server.
 - [MySQL](https://www.mysql.com/)
 - [Elasticsearch 2.1.0](https://www.elastic.co/products/elasticsearch)

###Downloading and importing data

You can download MySQL database HERE. It is a large database (over 400MB). Please, import this database, create a new database user with all privileges granted on the database, and choose database user's username and password. 

###Downloading the project

You can [clone](https://github.com/faculty-of-organizational-sciences/open-parliament-api.git) this project, or [download](https://github.com/faculty-of-organizational-sciences/open-parliament-api/archive/master.zip) the source code.

###Setting up configuration file

Project settings are stored in the file called [config.json](https://github.com/faculty-of-organizational-sciences/open-parliament-api/blob/master/src/main/resources/config/config.json). This file should be located at a path specified by the *System variable* called `OPEN_PARLIAMENT`. Create `OPEN_PARLIAMENT` variable with a value of a path on your file system with proper privileges. Application will try to locate `config.json` at this location.

Copy the file [config.json](https://github.com/faculty-of-organizational-sciences/open-parliament-api/blob/master/src/main/resources/config/config.json) to your specified location of the *System variable* called `OPEN_PARLIAMENT`. If file is not copied, application will create one at that locataion on the first run.

If `OPEN_PARLIAMENT` variable is not defined, `User home` variable will be used instead.

File `config.json` contains following properties:
 - dbConfig:
   - url of the database
   - username of the database user
   - password of the database user
   - pool size - maximum number of pooled connections - 10 is considered optimal
 - query limit - default number of results to return in one API call
 - uri prefix - usually the api domain name
 - elasticConfig:
   - usingElastic - whether ElasticSearch is avaialable and should be used
   - indexDataOnStartup - perform indexing on startup. This should be set only the first time 
   - clusterName - name of the Elastic Search cluster to be used
   - port - port Elastic Search is avaiable at (default is 9300)
   - ipAddress - address Elastic Search is avaiable at

###Elasticsearch configuration

If you want to use Elastic Search (recommended for better performance), download version 2.1.0 from the following [link](https://www.elastic.co/downloads/elasticsearch). Only version 2.1.0 can be used with the project. 

Check the `config.json` file, `usingElastic` should be set to `true`.

####Indexing

Elasticsearch is used for faster searching of data by using indexes.

It is recommended to perform indexing only on the first application run. When first time running the application, set property `indexDataOnStartup` to `true`. This will index all speeches, members, sessions and parties from the database. This can take several minutes.

Next time running the application, set property `indexDataOnStartup` to `false` in order to avoid the application deleting all indexes and perform indexing again.

###Running the applicaiton

From the console, initiate `mvn package` in order for Maven to download all dependencies, compile the code and generate .war file. Deploy this file to the web server of your preference.

Once you've set up the `config.json` file, started the MySQL server, started Elastic Search (if you choose to use it), you can start the web server and application will be deployed and ready for use. 

##API documentation

You can find detailed API documentation [HERE](http://147.91.128.71:9090/parlament/docs.html).

##Development tips

When developing the application, you can use [Maven jetty run plugin](http://www.eclipse.org/jetty/documentation/current/jetty-maven-plugin.html) via command `mvn jetty:run`. Jetty server is configured in project's `pom.xml` file.

For questions, suggestions and support, please contact Nikola at `nikola.milikic@fon.bg.ac.rs`.

###Issues

Report issues and suggest features and improvements on the [GitHub issue tracker](https://github.com/faculty-of-organizational-sciences/open-parliament-api/issues). Don't ask questions on the issue tracker - the mailing list is the right place for questions.

If you want to file a bug, please clone this repo and provide sufficient details to reproduce the issue. By starting fresh, with the latest code, we can ensure that the problem at hand isn't already fixed.

###Patches

Patches in any form are always welcome!

Best way to contribute to this project is to make a [GitHub pull request](https://help.github.com/articles/creating-a-pull-request/).

**Before submitting a patch or a pull request make sure that you are using the latest code.**

##Licence

The API and the data it provides access to are licenced under the [Creative Commons (BY-SA 4.0)](https://creativecommons.org/licenses/by-sa/4.0/) that allows anyone to copy and redistribute the data in any format and transform and build upon the data for any purpose, even commercially!
