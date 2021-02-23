package MASProject.s7973.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String startIndex(){ // aby metoda mogła obsłużyć adres potrzebny jest RequestMapping
        return "index.html";
    }
}