# FreeMarker Hello World

Here we will set up a very basic FreeMarker webapp using [Spring MVC](http://spring.io/).  We will also be using [Java-based Spring configuration](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java). (Legacy Spring MVC apps may still use xml-based Spring configuration, but this is not recommended for new projects.)

This tutorial assumes you have a Tomcat server running locally (or a server you’re comfortable configuring on your own.)

If you aren’t sure how to run Tomcat locally, please review the [Running a server locally](../00-running-a-server-locally) tutorial.

## Tutorial Notes

* This project only contains the *bare minimum* dependencies for a FreeMarker webapp. Other staple dependencies like [log4j](http://logging.apache.org/log4j/2.x/) and [Joda time](http://www.joda.org/joda-time/) will be used in subsequent tutorials.
* Everything in this project will be configured using UTF-8. When making a new webapp, it is crucial that you set up your project to have consistent encodings. If you don’t, you risk ending up with sites having

### Libraries Used

* FreeMarker 2.3.23
* Spring MVC 4.2


## Step 1: Download or Clone the tutorials project

[Download the FreeMarker Tutorials Github project](https://github.com/freemarker/tutorials/archive/master.zip) or clone it using git (`git clone https://github.com/freemarker/tutorials.git`)
