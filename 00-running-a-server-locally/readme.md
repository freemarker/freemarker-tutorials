# Setting Up and Running Tomcat

Before you can run your [FreeMarker](http://freemarker.org) webapp, you’ll need to start a server locally. This tutorial will go over a basic [Apache Tomcat](http://tomcat.apache.org/) configuration for your local machine.

## Prerequisites

This tutorial assumes you have some basic knowledge of programming concepts and problem solving skills.

You will also need a computer with admin/root access.

## Disclaimer

This tutorial is simply a guide to get a server up and running for *local development*. This tutorial should **by no means** be used as a reference for setting up an enterprise webapp. This tutorial is not being written from the perspective of a security expert and should not be treated as such. Again: This information is only for the purpose of local development.

Alright. Now that we have that out of the way—let’s move on.

## Why Tomcat?

Many different servers will run JEE webapps and they all have their various pros and cons. I’ve found Tomcat easy to configure and I like how quickly it starts up.

You are more than welcome to use another sever, such as [JBoss](http://www.jboss.org/) or [GlassFish](https://glassfish.java.net/), but all these tutorials will be referencing Tomcat paths.

## Step 1: Download and Install Tomcat

At the time of this writing, the latest stable version of Tomcat is **8.0.26**.


### Windows Users

Note: Based on Windows 7

1. On the [Tomcat download page](http://tomcat.apache.org/download-80.cgi), under Binary Distributions > Core, download the either the 32-bit or 64-bit Windows zip. (If you aren’t sure if your system is 32-bit or 64-bit, download the 32-bit version.)

2. Extract the zip somewhere you’ll remember, like `C:\tools\apache-tomcat-8.0.26`

3. Set `CATALINA_HOME` to your Tomcat directory
  1. Open up your Environment Variables
    1. Start > Computer
    2. Right-click Computer > Properties
    3. Advanced system settings > Environment variables
  2. Under System variables, click "New"
  3. In `Variable name` write `CATALINA_HOME`, in `Variable value` write `C:\tools\apache-tomcat-8.0.26` (or wherever you extracted your Tomcat folder to)
  4. 
