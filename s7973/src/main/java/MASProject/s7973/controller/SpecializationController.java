package MASProject.s7973.controller;

import MASProject.s7973.model.Groups;
import MASProject.s7973.model.Specialization;
import MASProject.s7973.model.Student;
import MASProject.s7973.services.SpecializationService;
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
public class SpecializationController {

    @Autowired
    private SpecializationService specService;


    @GetMapping("/admin/spec/list")
    public String specList(Model model) {
        var specs = (List<Specialization>) specService.findAll();

        model.addAttribute("specs", specs);

        return "/admin/spec/list.html";
    }

    @GetMapping("/admin/spec/details/{id}")
    public String getSpec(Model model, @PathVariable int id) {
        var spec = specService.getSpec(id);
        List<Groups> groups = spec.getGroups();

        model.addAttribute("specs", spec);
        model.addAttribute("groups", groups);

        return "/admin/spec/details.html";
    }

    @RequestMapping("/admin/spec/add")
    public String addSpec(Model model) {
        model.addAttribute("specialization", new Specialization());
        return "/admin/spec/form.html";
    }

    @PostMapping("/admin/spec/list")
    public String saveSpec(@Valid Specialization spec, BindingResult result) {
        List<Specialization> specs = specService.findAll();
        for (Specialization s : specs) {
            if(spec.getCode().equals(s.getCode())) {
                result.addError(
                        new FieldError("code","code","Dany kod znajduje się już w bazie"));
            }
        }
        if(result.hasErrors()) {
            result.getAllErrors();
            return "admin/spec/form.html";
        } else {
            specService.saveSpec(spec);
            return "redirect:/admin/spec/list";
        }
    }

    @GetMapping(value = "/admin/spec/edit/{id}")
    public String editSpec(Model model, @PathVariable int id) {
        Specialization spec = specService.getSpec(id);
        model.addAttribute("specs", spec);
        return "/admin/spec/edit.html";
    }


    @PostMapping(value = "/admin/spec/edit/{id}")
    public String editSpec(@Valid Specialization spec, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/spec/edit.html";
        } else {
            specService.updateSpec(spec);
            return "redirect:/admin/spec/list";
        }
    }

    @GetMapping( "/admin/spec/delete/{id}")
    public void deleteSpec(@PathVariable int id, HttpServletResponse response) throws IOException {
        specService.removeSpec(id);
        response.sendRedirect("/admin/spec/list");

    }
}
