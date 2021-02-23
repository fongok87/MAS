package MASProject.s7973.controller;

import MASProject.s7973.model.Groups;
import MASProject.s7973.model.Specialization;
import MASProject.s7973.model.Student;
import MASProject.s7973.model.TeachingMode;
import MASProject.s7973.services.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class ModeController {

    @Autowired
    private TeachingService teachingService;

    @GetMapping("/admin/mode/list")
    public String modeList(Model model) {
        var modes = (List<TeachingMode>) teachingService.findAll();

        model.addAttribute("modes", modes);

        return "/admin/mode/list.html";
    }

    @GetMapping("/admin/mode/details/{id}")
    public String getMpec(Model model, @PathVariable int id) {
        var mode = teachingService.getMode(id);
        List<Groups> groups = mode.getGroups();

        model.addAttribute("modes", mode);
        model.addAttribute("groups", groups);

        return "/admin/mode/details.html";
    }

    @RequestMapping("/admin/mode/add")
    public String addMode(Model model) {
        model.addAttribute("teachingMode", new TeachingMode());
        return "/admin/mode/form.html";
    }

    @PostMapping("/admin/mode/list")
    public String saveMode(@Valid TeachingMode teachingMode, BindingResult result) {
        List<TeachingMode> modes = teachingService.findAll();
        for (TeachingMode s : modes) {
            if(teachingMode.getMode().equals(s.getMode())) {
                result.addError(
                        new FieldError("mode","mode","Dany typ znajduje się już w bazie"));
            }
        }
        if(result.hasErrors()) {
            return "/admin/mode/form.html";
        } else {
            teachingService.saveMode(teachingMode);
            return "redirect:/admin/mode/list";
        }
    }

    @GetMapping(value = "/admin/mode/edit/{id}")
    public String editMode(Model model, @PathVariable int id) {
        TeachingMode mode = teachingService.getMode(id);
        model.addAttribute("modes", mode);
        return "/admin/mode/edit.html";
    }


    @PostMapping(value = "/admin/mode/edit/{id}")
    public String editMode(@Valid TeachingMode mode, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/mode/edit.html";
        } else {
            teachingService.updateMode(mode);
            return "redirect:/admin/mode/list";
        }
    }

    @GetMapping( "/admin/mode/delete/{id}")
    public void deleteMode(@PathVariable int id, HttpServletResponse response) throws IOException {
        teachingService.removeMode(id);
        response.sendRedirect("/admin/mode/list");

    }
}