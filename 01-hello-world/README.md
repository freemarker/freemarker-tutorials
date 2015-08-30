# FreeMarker Hello World

Here we will set up a very basic FreeMarker webapp using [Spring MVC](http://spring.io/).  We will also be using [Java-based Spring configuration](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java). (Legacy Spring MVC apps may still use xml-based Spring configuration, but this is not recommended for new projects.)

This tutorial assumes you have a Tomcat server running locally (or a server you’re comfortable configuring on your own.)

If you aren’t sure how to run Tomcat locally, please review the [Running a server locally](../00-running-a-server-locally) tutorial.

** Libraries Used**

* [FreeMarker 2.3.23](http://freemarker.org/docs/)
* [Spring MVC 4.2](https://spring.io/blog/2015/07/31/spring-framework-4-2-goes-ga)
* Javax Servlet API 3.1.0

## Tutorial Notes

This project only contains the *bare minimum* dependencies for a FreeMarker webapp. Other staple dependencies like [log4j](http://logging.apache.org/log4j/2.x/) and [Joda time](http://www.joda.org/joda-time/) will be used in subsequent tutorials.

### UTF-8

Everything in this project will be configured using UTF-8 charset. When making a new webapp, it is crucial you set up your project to have consistent UTF-8 file encodings.

If you don’t, you risk your site having embarrassing encoding issues (ever seen a site with words like "donâ€™t" instead of "don’t"? Yeah, awkward.) It’s also a huge pain to go back and fix encoding issues, so just use **UTF-8 for everything from the start**.

Every editor is different and some set different default encodings for different file types. For example if you’re using Eclipse, go through **each file type** and make sure the default encoding is set to UTF-8 for each extension:

![UTF-8 Eclipse](https://raw.githubusercontent.com/freemarker/tutorials/master/01-hello-world/images/eclipse-utf-8.png)

## Step 1: Download or Clone the tutorials project

[Download the FreeMarker Tutorials Github project](https://github.com/freemarker/tutorials/archive/master.zip) or clone it using git (`git clone https://github.com/freemarker/tutorials.git`)

## Step 2: Install the project using Maven

Open up a console window (Command prompt for Windows users or Terminal for OS X users) and navigate to the `01-hello-world` directory.  

```bsh
01-hello-world> mvn install
```

This will download the dependencies and create some various files in a `target` folder in your project directory. 

### Short Maven Overview

If you already know what Maven is you can skip this whole section.

If you don’t know what Maven is, I’ll only give a short explanation here as there are a lot of other Maven resources out there.

Maven ensures developers working on a project together are using the same dependencies. It also handles downloading dependencies and putting them in a consistent location where your Java compiler can find them.

Maven looks for a `pom.xml` file in the root of your project and runs commands based on what is in that file. (There are more elaborate ways to set up Maven builds, but since this is a FreeMarker tutorial and not a Maven tutorial, that topic won’t be covered.)

tl;dr: We’ll be using Maven to download dependencies and build our project.

## Step 3: Point Tomcat at your webapp

In the [previous tutorial](../00-running-a-server-locally) we set up a Tomcat server.
