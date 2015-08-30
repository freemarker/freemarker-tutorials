package FreeMarkerTutorials.config;

import java.io.IOException;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.context.WebApplicationContext;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.TemplateException;

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
        resolver.setCache(false);

        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setRequestContextAttribute("rc");
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setExposeRequestAttributes(true);
        resolver.setExposePathVariables(true);
        resolver.setExposeSessionAttributes(true);
        resolver.setExposeContextBeansAsAttributes(true);

        resolver.setOrder(0);

        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(WebApplicationContext applicationContext)
            throws IOException, TemplateException {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

        configurer.setServletContext(applicationContext.getServletContext());
        freemarker.template.Configuration configuration = configurer.createConfiguration();
        configuration.addAutoInclude("/templates/include-common.ftl");
        configuration.setNumberFormat("0.######");
        configuration.setWhitespaceStripping(true);

        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setURLEscapingCharset("UTF-8");
        configuration.setServletContextForTemplateLoading(applicationContext.getServletContext(), "/WEB-INF/ftl/");
        configuration.setTemplateUpdateDelay(0);

        configuration.setLocale(Locale.ENGLISH);
        configuration.setTagSyntax(1); // ensure tags are angle brackets
        configuration.setBooleanFormat("TRUE,FALSE");
        configuration.setWhitespaceStripping(true);
        configuration.setStrictBeanModels(false);

        configurer.setConfiguration(configuration);

        return configurer;
    }
}
