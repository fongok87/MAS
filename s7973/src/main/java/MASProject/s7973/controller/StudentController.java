package MASProject.s7973.controller;

import MASProject.s7973.services.GradeService;
import MASProject.s7973.services.PaymentService;
import MASProject.s7973.services.StudentService;
import MASProject.s7973.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import MASProject.s7973.model.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GradeService gradeService;


    @GetMapping("/students")
    public String findStudents(Model model) {

        var students = (List<Student>) studentService.findAll();

        model.addAttribute("students", students);

        return "/studentList.html";
    }

    @GetMapping("/admin/student/list")
    public String getStudents(Model model) {

        var students = (List<Student>) studentService.findAll();

        model.addAttribute("students", students);

        return "/admin/student/list.html";
    }

    @GetMapping("/admin/student/details/{id}")
    public String getStudent(Model model, @PathVariable int id) {
        var student = studentService.getStudent(id);
        var group = student.getGroup();
        List<Grade> grade = student.getGrades();
        model.addAttribute("students", student);
        model.addAttribute("groups", group);
        model.addAttribute("grades", grade);

        return "/admin/student/details.html";
    }

    @GetMapping("/admin/student/group/details/{id}")
    public String groupDetails(Model model, @PathVariable int id) {
        var student = studentService.getStudent(id);
        var group = student.getGroup();
        Set<Subject> subjects = group.getSubjects();
        model.addAttribute("groups", group);
        model.addAttribute("subjects", subjects);

        return "/admin/student/group/details.html";
    }

    @GetMapping(value = "/admin/student/edit/{id}")
    public String editStudent(Model model, @PathVariable int id) {
        Student student = studentService.getStudent(id);

        model.addAttribute("student", student);

        return "/admin/student/edit.html";
    }


    @PostMapping(value = "/admin/student/edit/{id}")
    public String editStudent(@Valid Student student, BindingResult result) {
        if(result.hasErrors()) {
            return "admin/student/edit.html";
        } else {
            studentService.updateStudent(student);
            return "redirect:/admin/student/list";
        }
    }

    @RequestMapping("/admin/student/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "/admin/student/form.html";
    }

    @PostMapping("/admin/student/list")
    public String saveStudent(@Valid Student student, BindingResult result) {
        List<Student> students = studentService.findAll();
        for (Student s : students) {
            if(s.getAlias().equals(student.getAlias())) {
                result.addError(
                        new FieldError("alias","alias","Alias znajduje się już w bazie"));
            }
        }
        if(result.hasErrors()) {
            return "admin/student/form.html";
        } else {
            studentService.saveStudent(student);
            return "redirect:/admin/student/list";
        }
    }


    @GetMapping( "/admin/student/delete/{id}")
    public void deleteStudent(@PathVariable int id, HttpServletResponse response) throws IOException {
        studentService.removeStudent(id);
        response.sendRedirect("/admin/student/list");

    }

    @GetMapping("/admin/student/addGrade/{id}")
    public String addGrade(Model model, Student student, @PathVariable int id) {
        student = studentService.getStudent(id);
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("student", student);
        model.addAttribute("subject", subjects);
        model.addAttribute("grade", new Grade());
        return "admin/student/grade/addGrade.html";
    }


    @PostMapping("/admin/student/addGrade/{id}")
    public String saveGrade(@Valid Grade grade, @PathVariable int id,  BindingResult result) {
        if(result.hasErrors()) {
            return "admin/student/addGrade.html";
        } else {
            gradeService.saveGrade(grade);
            return "redirect:/admin/student/details/" + studentService.getStudent(id).getId();
        }
    }

    @GetMapping( "/admin/student/grade/delete/{id}")
    public void deleteGrade(@PathVariable int id, HttpServletResponse response) throws IOException {
        int student_id = gradeService.getGrade(id).getStudent().getId();
        gradeService.removeGrade(id);
        response.sendRedirect("/admin/student/details/" + student_id);

    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }



}
