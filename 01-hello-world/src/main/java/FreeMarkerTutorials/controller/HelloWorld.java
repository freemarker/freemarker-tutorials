package FreeMarkerTutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
