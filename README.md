# FreeMarker Tutorials

This is a collection of tutorials to help you get started with FreeMarker web development.

## Prerequisites

Before you start on these tutorials you should have the following installed *and configured*:

* Java 7+
* [Maven v3.2+](http://maven.apache.org/install.html) 
* [NodeJS v0.12.0+](http://nodejs.org/)

## Target audience

These tutorials are aimed at frontend and backend engineers who are familiar with some programming concepts and have a basic understanding of what [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) is. Even if you have never worked on an MVC project before, these tutorials should be enough to get you started.

FreeMarker is a Java templating engine and can be used as a [view resolver](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/ViewResolver.html) with [SpringMVC](http://spring.io/). FreeMarker is an alternative to JSP, XSL, or Velocity and is an [actively maintained, open-source project](https://github.com/freemarker/freemarker/commits/). Any webapp using Spring MVC can easily migrate to FreeMarker.

## Tutorials

[Installing Java and Maven](00-installing-java-and-maven/)

You can skip this if you already have Java and Maven installed.

[Running a server locally](00-running-a-server-locally/)

You can skip this if you are already comfortable starting up and configuring Tomcat, but other tutorials will reference these settings in this tutorial.

[FreeMarker “Hello World”](01-hello-world/)

A basic overview of starting up and configuring a FreeMarker webapp with Spring MVC. Topics include:
* Java-based Spring configuration
* Configuring FreeMarker settings
* Ensuring your app has proper UTF-8 charset configuration
* Deploying to different contexts using Tomcat
* Changing locale and time zones using FreeMarker
