package MASProject.s7973.controller;

import MASProject.s7973.services.EmploymentService;
import MASProject.s7973.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import MASProject.s7973.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EmploymentService employmentService;

    @GetMapping("/teachers")
    public String getTeacher(Model model) {
        var teachers = (List<Teacher>) teacherService.findAll();
        System.out.println("test");
        model.addAttribute("teachers", teachers);

        return "/teacherList.html";
    }

    @GetMapping("/admin/teacher/list")
    public String getTeachers(Model model) {
        var teachers = (List<Teacher>) teacherService.findAll();
        Integer bonus = 0;
        model.addAttribute("teachers", teachers);
        model.addAttribute("bonus", bonus);

        return "/admin/teacher/list.html";
    }

    @GetMapping("/admin/teacher/bonus/{id}")
    public void bonus(@Valid Integer bonus,@PathVariable Integer id, HttpServletResponse response) throws IOException {
        var teachers = (List<Teacher>) teacherService.findAll();
        for (Teacher t : teachers) {
            t.modifyBonus(bonus);
            teacherService.updateTeacher(t);
        }
        response.sendRedirect("redirect:/admin/teacher/list");
    }

    @GetMapping("/admin/teacher/details/{id}")
    public String getTeacher(Model model, @PathVariable int id) {
        var teacher = teacherService.getTeacher(id);
        List<Subject> subject = teacher.getSubject();
        List<Employment> employment = teacher.getEmployments();

        model.addAttribute("teachers", teacher);
        model.addAttribute("subjects", subject);
        model.addAttribute("employments", employment);

        return "/admin/teacher/details.html";
    }

    @GetMapping("/admin/teacher/edit/{id}")
    public String editTeacher(Model model, @PathVariable int id) {
        var teacher = teacherService.getTeacher(id);
        List<Subject> subject = teacher.getSubject();
        List<Employment> employment = teacher.getEmployments();

        model.addAttribute("teachers", teacher);
        model.addAttribute("subjects", subject);
        model.addAttribute("employments", employment);

        return "/admin/teacher/edit.html";
    }

    @PostMapping(value = "/admin/teacher/edit/{id}")
    public String editTeacher(@Valid Teacher teacher, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/teacher/edit.html";
        } else {
            teacherService.updateTeacher(teacher);
            return "redirect:/admin/teacher/list";
        }
    }

    @GetMapping("admin/teacher/bonus")
    public String editBonus(Model model) {

        return "admin/teacher/list.html";
    }

    @GetMapping("/admin/teacher/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "/admin/teacher/form.html";
    }

    @PostMapping("/admin/teacher/list")
    public String saveTeacher(@Valid Teacher teacher, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/teacher/form.html";
        } else {
            teacherService.saveTeacher(teacher);
            return "redirect:/admin/teacher/list";
        }
    }

    @GetMapping( "/admin/teacher/delete/{id}")
    public void deleteTeacher(@PathVariable int id, HttpServletResponse response) throws IOException {
        teacherService.removeTeacher(id);
        response.sendRedirect("/admin/teacher/list");
    }

    @GetMapping( "/admin/teacher/employment/{id}")
    public String addEmployment(Model model, @PathVariable int id) {
        Teacher teacher = teacherService.getTeacher(id);
        model.addAttribute("teacher", teacher);
        model.addAttribute("employment", new Employment());
        return "admin/teacher/employment/addEmployment.html";
    }

    @PostMapping("/admin/teacher/employment/{id}")
    public String saveEmployment(@Valid Employment employment, @PathVariable int id,  BindingResult result) {
        if(result.hasErrors()) {
            return "admin/teacher/addEmployment.html";
        } else {
            employmentService.saveEmployment(employment);
            return "redirect:/admin/teacher/details/" + teacherService.getTeacher(id).getId();
        }
    }

}

