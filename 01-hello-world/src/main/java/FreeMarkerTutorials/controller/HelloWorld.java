package FreeMarkerTutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorld {

    @RequestMapping("/")
    public String loadExample(Model model) {
        model.addAttribute("pageTitle", "Example Freemarker Page");

        return "hello-world";
    }
}
