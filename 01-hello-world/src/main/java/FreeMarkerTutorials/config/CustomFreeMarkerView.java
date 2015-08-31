package FreeMarkerTutorials.config;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestParametersHashModel;
import freemarker.ext.servlet.IncludePage;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;

public class CustomFreeMarkerView extends FreeMarkerView {
    private ServletContextHashModel servletContextHashModel;

    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Expose all standard FreeMarker hash models.
        model.put(FreemarkerServlet.KEY_APPLICATION, this.servletContextHashModel);
        model.put(FreemarkerServlet.KEY_SESSION, buildSessionModel(request, response));
        model.put(FreemarkerServlet.KEY_REQUEST, new HttpRequestHashModel(request, response, getObjectWrapper()));
        model.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, new HttpRequestParametersHashModel(request));
        model.put(FreemarkerServlet.KEY_INCLUDE, new IncludePage(request, response));

        // if (logger.isDebugEnabled()) {
        logger.info("Rendering FreeMarker template [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
        logger.debug("Rendering FreeMarker template [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
        // }
        // Grab the locale-specific version of the template.
        Locale locale = RequestContextUtils.getLocale(request);

        getTemplate(locale).process(model, response.getWriter());
    }

    private HttpSessionHashModel buildSessionModel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            return new HttpSessionHashModel(session, getObjectWrapper());
        } else {
            return new HttpSessionHashModel(null, request, response, getObjectWrapper());
        }
    }

}
