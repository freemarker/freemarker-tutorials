# FreeMarker Tutorials

This is a collection of tutorials to help you get started with FreeMarker web development.

## Target audience

These tutorials are aimed at frontend and backend engineers who are familiar with some programming concepts and have a basic understanding of what [MVC](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) is. Even if you have never worked on an MVC project before, these tutorials should be enough to get you started.

FreeMarker is a Java templating engine and can be used as a [view resolver](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/ViewResolver.html) with the popular [Spring MVC](http://spring.io/) framework. FreeMarker is as an alternative to other view resolvers like JSP, XSL, or Velocity and is an [actively maintained, open-source project](https://github.com/freemarker/freemarker/commits/).

## Tutorials

1. [Installing Java and Maven](00-installing-java-and-maven/) <br>You can skip this if you already have Java 7+ and Maven 3.1+ installed.

2. [Running a server locally](00-running-a-server-locally/) <br>You can skip this if you are already comfortable starting up and configuring a server like Tomcat, but other tutorials will reference the settings in this tutorial.

3. [FreeMarker “Hello World”](01-hello-world/) <br>A basic overview of starting up and configuring a FreeMarker webapp with Spring MVC. Topics include:
    * Java-based Spring configuration
    * Configuring FreeMarker settings
    * Ensuring your app has proper UTF-8 charset configuration
    * Deploying to different contexts using Tomcat
