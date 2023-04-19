package orm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {
    @RequestMapping({"/login", "/register", "/dashboard"})
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/index.html";
    }
}
