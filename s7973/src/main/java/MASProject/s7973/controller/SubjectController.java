package MASProject.s7973.controller;

import MASProject.s7973.services.SubjectService;
import MASProject.s7973.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import MASProject.s7973.model.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/subjects")
    public String findSubjects(Model model) {

        var subjects = (List<Subject>) subjectService.findAll();

        model.addAttribute("subjects", subjects);

        return "/subjectList.html";
    }

    @GetMapping("/admin/subject/list")
    public String getSubjects(Model model) {

        var subjects = (List<Subject>) subjectService.findAll();

        model.addAttribute("subjects", subjects);

        return "/admin/subject/list.html";
    }

    @GetMapping("/admin/subject/details/{id}")
    public String getSubject(Model model, @PathVariable int id) {
        var subject = subjectService.getSubject(id);
        List<Grade> grade = subject.getGrades();
        Set<Groups> groups = subject.getGroups();
        List<Teacher> teachers = teacherService.findAll();

        model.addAttribute("subjects", subject);
        model.addAttribute("grades", grade);
        model.addAttribute("groups", groups);
        model.addAttribute("teachers", teachers);

        return "/admin/subject/details.html";
    }

    @GetMapping("/admin/subject/edit/{id}")
    public String editSubject(Model model, @PathVariable int id) {
        var subject = subjectService.getSubject(id);
        List<Grade> grade = subject.getGrades();
        List<Teacher> teachers = teacherService.findAll();

        model.addAttribute("subjects", subject);
        model.addAttribute("grades", grade);
        model.addAttribute("teachers", teachers);

        return "/admin/subject/edit.html";
    }

    @PostMapping(value = "/admin/subject/edit/{id}")
    public String editStudent(@Valid Subject subject, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/subject/edit.html";
        } else {
            subjectService.updateSubject(subject);
            return "redirect:/admin/subject/list";
        }
    }

    @GetMapping("/admin/subject/add")
    public String addSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "/admin/subject/form.html";
    }

    @PostMapping("/admin/subject/list")
    public String saveStudent(@Valid Subject subject, BindingResult result) {
        List<Subject> subjects = subjectService.findAll();
        for (Subject s : subjects) {
            if(s.getAlias().equals(subject.getAlias())) {
                result.addError(
                        new FieldError("alias","alias","Przedmiot o danym kodzie znajduje się już w bazie"));
            }
        }
        if(result.hasErrors()) {
            return "admin/subject/form.html";
        } else {
            subjectService.saveSubject(subject);
            return "redirect:/admin/subject/list";
        }
    }

    @GetMapping("admin/subject/delete/{id}")
    public void deleteSubject(@PathVariable int id, HttpServletResponse response) throws IOException {
        subjectService.removeSubject(id);
        response.sendRedirect("/admin/subject/list");
    }


}
