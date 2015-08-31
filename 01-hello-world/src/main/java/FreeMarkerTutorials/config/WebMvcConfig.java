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
    public CustomFreeMarkerViewResolver freeMarkerViewResolver() {
        CustomFreeMarkerViewResolver resolver = new CustomFreeMarkerViewResolver();
        resolver.setPrefix("/private/");
        resolver.setSuffix(".ftl");
        resolver.setCache(false); // don't disable the cache in production!

        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setRequestContextAttribute("requestContext");

        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(WebApplicationContext applicationContext)
            throws IOException, TemplateException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        configurer.setServletContext(applicationContext.getServletContext());

        freemarker.template.Configuration configuration = configurer.createConfiguration();

        configuration.addAutoInclude("/templates/include-common.ftl");
        configuration.setServletContextForTemplateLoading(applicationContext.getServletContext(), "/WEB-INF/ftl/");
        configuration.setIncompatibleImprovements(freemarker.template.Configuration.VERSION_2_3_23);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER); // use this for local development

        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setURLEscapingCharset("UTF-8");

        configurer.setConfiguration(configuration);

        return configurer;
    }

    @Bean
    SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);

        return localeResolver;
    }

}
