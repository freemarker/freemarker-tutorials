package FreeMarkerTutorials.config;

import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

public class CustomFreeMarkerViewResolver extends FreeMarkerViewResolver {
    public CustomFreeMarkerViewResolver() {
        setViewClass(requiredViewClass());
    }

    @Override
    protected Class<CustomFreeMarkerView> requiredViewClass() {
        return CustomFreeMarkerView.class;
    }
}
