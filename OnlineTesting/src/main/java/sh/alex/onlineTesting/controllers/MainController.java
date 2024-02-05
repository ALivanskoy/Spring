package sh.alex.onlineTesting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/")
    public String getAboutPage () {

    return "forward:/index.html";
    }


}
