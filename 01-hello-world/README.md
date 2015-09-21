# FreeMarker Hello World

Here we will set up a very basic FreeMarker webapp using [Spring MVC](http://spring.io/).  We will also be using [Java-based Spring configuration](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-java). (Legacy Spring MVC apps may still use xml-based Spring configuration, but this is not recommended for new projects.)

This tutorial assumes you have a Tomcat server running locally (or a server you’re comfortable configuring on your own.)

If you aren’t sure how to run Tomcat locally, please review the tutorial on [running a Tomcat server locally](../00-running-a-server-locally).

**Libraries Used**

* [FreeMarker 2.3.23](http://freemarker.org/docs/)
* [Spring MVC 4.2](https://spring.io/blog/2015/07/31/spring-framework-4-2-goes-ga)
* Javax Servlet API 3.1.0

## A Note About UTF-8

Everything in this project will be configured using UTF-8 charset. When making a new webapp, it is crucial you set up your project to have consistent UTF-8 file encodings.

If you don’t consistently use UTF-8, you risk embarrassing encoding issues (ever seen a site with words like "donâ€™t" instead of "don’t"? Yeah, awkward.) It’s also a huge pain to go back and fix encoding issues, so just use **UTF-8 for everything from the start**.

Every editor is different and some set different default encodings for different file types. For example if you’re using Eclipse, go through **each file type** and make sure the default encoding is set to UTF-8 for each extension.

## Step 1: Download or clone the FreeMarker Tutorials project

[Download and extract the FreeMarker Tutorials Github project](https://github.com/freemarker/freemarker-tutorials/archive/master.zip) or clone it using git (`git clone https://github.com/freemarker/freemarker-tutorials.git`)

## Step 2: Compile the Hello World project

Open up a console window (Command prompt for Windows users or Terminal for OS X users) and navigate to the `tutorials/01-hello-world` directory. Run `mvn compile war:inplace`.

```bsh
mvn compile war:inplace
```

(If you don’t have Maven, follow the installation instructions here: [Installing Java and Maven](../00-installing-java-and-maven/))

This will download the dependencies and compile the Java files.

## Step 3: Point Tomcat at your webapp directory

1. In the [previous tutorial](../00-running-a-server-locally) we set up a Tomcat server. Make sure your server is still running!

    * Windows users: Open up Command Prompt, type `catalina start` and press enter
    * OS X users: Open up Terminal, type `catalina start` and press enter

2. Navigate to your Tomcat installation folder, and find the `conf/Catalina/localhost` directory and create a context file called **hello-world.xml**.

    * Windows users: If you followed the previous tutorial’s instructions, Windows users can navigate directly to the folder by copying and pasting `%CATALINA_HOME%\conf\Catalina\localhost` into a Windows explorer window.

        ![Windows 7 XML file](images/win7-catalina-home.png)

    * OS X users: In Terminal you can do this (Replace **8.0.26** with your version of Tomcat):

        ```bsh
        cd /usr/local/Cellar/tomcat/8.0.26/libexec/conf/Catalina/localhost
        vi hello-world.xml
        ```

3. Inside `hello-world.xml`, add the following (Replace **PATH_TO_FREEMARKER_TUTORIALS** with
    wherever you have the FreeMarker tutorials project.):

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <Context docBase="PATH_TO_FREEMARKER_TUTORIALS/01-hello-world/src/main/webapp"
        path="" reloadable="true" />
    ```

4. You should now be able to access the FreeMarker Hello World webapp at [http://localhost:8080/hello-world/](http://localhost:8080/hello-world/).

    ![Running FreeMarker site](images/hello-world.png)


### A note about context files

It is important to note that the **/hello-world/** part of the URL is **based on the name of the XML file we created**. If we renamed `hello-world.xml` to `banana-bunnies.xml`, then our webapp would be accessible at http://localhost:8080/banana-bunnies/.  If you wanted to deploy the webapp to http://localhost:8080, you would rename the xml file to `ROOT.xml` (case-sensitive).

Context files are convenient because you can deploy different webapps to the same domain without needing to redeploy the whole domain.

For example, if you had a website with a “Special Offers” section, you could separate it into it’s own webapp and deploy the `special-offers` app without affecting the main website.

* http://example.com - Deloyed using `ROOT.xml`
* http://example.com/special-offers/ - Deployed using `special-offers.xml`

## Step 4: Digging into the files

First, a summary of the files and directories:

```
01-hello-world/
  src/
    java/
      FreeMarkerTutorials/
        config/
          AppConfig.java
          WebMvcConfig.java
        controller/
          HelloWorld.java
        AppInitializer.java
    main/
      webapp/
        WEB-INF/
          ftl/
            views/
              hello-world.ftl
          web.xml
```

All JEE webapps should follow the `/src/main/java` and `/src/main/webapp` directory structure. (Normally there is another folder named `src/main/resources` but we don’t need that for this example.)

### web.xml

Web.xml ([source](src/main/webapp/WEB-INF/web.xml)) is [required by Tomcat](http://wiki.metawerx.net/wiki/Web.xml). We’ll use web.xml in subsequent tutorials. For now the inside of `<web-app></web-app>` can be be empty.


```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">
</web-app>
```


### AppInitializer.java

AppInitializer.java ([source](src/main/java/FreeMarkerTutorials/AppInitializer.java)) tells Spring where to look for the webapp configuration. Historically this was handled by a `servlet.xml` file. Pretty standard stuff—not much to see here. (You would have this file for any Spring MVC project)

```java
public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // Look for configuration in /src/main/java/FreeMarkerTutorials/config/
        context.setConfigLocation("FreeMarkerTutorials.config");
        return context;
    }
}
```

### AppConfig.java

AppConfig.java ([source](src/main/java/FreeMarkerTutorials/config/AppConfig.java)) is pretty boring. It just tells Spring where to look for the `@Configuration` classes.

```java
@Configuration
@ComponentScan(basePackages = "FreeMarkerTutorials")
public class AppConfig {

}
```

### WebMvcConfig.java

WebMvcConfig.java ([source](src/main/java/FreeMarkerTutorials/config/WebMvcConfig.java))

Relevant code:

```java
@Bean
public FreeMarkerViewResolver freeMarkerViewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();

    // Make sure all our views are in /WEB-INF/ftl/views/ and end with *.ftl
    // This helps keep the views in one place
    // Note: This is used in conjuction with
    //       `configuration.setServletContextForTemplateLoading` below
    resolver.setPrefix("/views/");
    resolver.setSuffix(".ftl");

    // Disable the cache when doing local development
    // This means that you will see any updates to an FTL file
    // immediately after refreshing the browser
    // (don't disable the cache in production!)
    resolver.setCache(false);

    // When returning a freemarker view, set the charset to UTF-8
    // and the content-type to text/html
    // Note: This should always be set by the backend. Don't set this in the view layer!
    resolver.setContentType("text/html;charset=UTF-8");

    return resolver;
}

@Bean
public FreeMarkerConfigurer freeMarkerConfigurer(WebApplicationContext applicationContext)
        throws IOException, TemplateException {
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

    configurer.setServletContext(applicationContext.getServletContext());

    freemarker.template.Configuration configuration = configurer.createConfiguration();

    // Make sure all freemarker files go in /WEB-INF/ftl/
    // This helps keep the code organized
    configuration.setServletContextForTemplateLoading(applicationContext.getServletContext(), "/WEB-INF/ftl/");

    // When starting a new FreeMarker project, always set the incompatible improvements to the version
    // you are using.
    configuration.setIncompatibleImprovements(freemarker.template.Configuration.VERSION_2_3_23);

    // Use this for local development. When a template exception occurs,
    // it will format the error using HTML so it can be easily read
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);

    // Makre sure everything is UTF-8 from the beginning to avoid headaches
    configuration.setDefaultEncoding("UTF-8");
    configuration.setOutputEncoding("UTF-8");
    configuration.setURLEscapingCharset("UTF-8");

    // Apply the configuration settings to the configurer
    configurer.setConfiguration(configuration);

    return configurer;
}

@Bean
SessionLocaleResolver localeResolver() {
    // Enable the SessionLocaleResolver
    // Even if you don't localize your webapp you should still specify this
    // so that things like numbers, dates, and currencies are formatted properly
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(Locale.US);

    return localeResolver;
}
```

### HelloWorld.java

HelloWorld.java ([source](src/main/java/FreeMarkerTutorials/controller/HelloWorld.java)) is a controller whose job is to send model data to the view based on the request.

Relevant code:

```java
@Controller
public class HelloWorld {

    @RequestMapping("/")
    public String loadExample(Model model) {
        // Send the variable "pageTitle" to the view.
        // This can be accessed by ${pageTitle} in the FreeMarker file "hello-world.ftl"
        model.addAttribute("pageTitle", "Example Freemarker Page");

        // When the user navigates to http://<deploy-url>/<context>/, tell the server to use
        // `/WEB-INF/ftl/views/hello-world.ftl` to render the view
        return "hello-world";
    }
}
```

### hello-world.ftl

hello-world.ftl ([source](src/main/webapp/WEB-INF/ftl/views/hello-world.ftl)) is our view file.

Relevant code:

```html
<!doctype html>
<html lang="en">
    <head>
        <#-- this comes from HelloWorld.java, when we added
              model.addAttribute("pageTitle", "Example Freemarker Page"); -->
        <title>${pageTitle}</title>
    </head>
    <body>
        <div class="example-page">
            <h1>Hello, world!</h1>
            <p>If your UTF-8 encoding is set up properly, these elements should be the same:</p>
            <ul>
                <li>© | &copy; | &#169;</li>
                <li>® | &reg; | &#174;</li>
                <li>¥ | &yen; | &#165;</li>
            </ul>
        </div>
    </body>
</html>
```
