package FreeMarkerTutorials.config;

import java.io.IOException;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

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

}
