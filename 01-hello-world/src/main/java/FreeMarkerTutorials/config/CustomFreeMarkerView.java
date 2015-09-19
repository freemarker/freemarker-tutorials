package FreeMarkerTutorials.config;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

public class CustomFreeMarkerView extends FreeMarkerView {

    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Locale locale = RequestContextUtils.getLocale(request);

        getTemplate(locale).process(model, response.getWriter());
    }
}
