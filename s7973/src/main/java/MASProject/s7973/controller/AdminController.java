package MASProject.s7973.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPlate() {
        return "/admin/plate.html";
    }


}
