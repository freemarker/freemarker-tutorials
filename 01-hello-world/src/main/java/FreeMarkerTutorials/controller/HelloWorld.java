package FreeMarkerTutorials.controller;

//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;

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
