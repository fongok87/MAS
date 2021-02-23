package MASProject.s7973.controller;

import MASProject.s7973.model.*;
import MASProject.s7973.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class GroupController {

    @Autowired
    private SpecializationService specService;

    @Autowired
    private GroupsService groupService;

    @Autowired
    private TeachingService teachingService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/admin/group/list")
    public String adminGroupList(Model model) {
        var groups = (List<Groups>) groupService.findAll();

        model.addAttribute("groups", groups);

        return "/admin/group/list.html";
    }

    @GetMapping("/admin/group/details/{id}")
    public String getGroup(Model model, @PathVariable int id) {
        var group = groupService.getGroup(id);
        Set<Student> student = group.getStudents();
        Set<Subject> subject = group.getSubjects();

        model.addAttribute("groups", group);
        model.addAttribute("students", student);
        model.addAttribute("subjects", subject);

        return "/admin/group/details.html";
    }

    @GetMapping( "/admin/group/delete/{id}")
    public void deleteGroup(@PathVariable int id, HttpServletResponse response) throws IOException {
        groupService.removeGroup(id);
        response.sendRedirect("/admin/group/list");
    }

    @GetMapping("/admin/group/add")
    public String addGroup(Model model) {
        model.addAttribute("groups", new Groups());
        return "/admin/group/form.html";
    }

    @PostMapping("/admin/group/add")
    public String saveGroup(@Valid Groups group, BindingResult result) {
        List<Groups> gr = groupService.findAll();
        for (Groups g : gr) {
            if(g.getCode().equalsIgnoreCase(group.getCode())) {
                result.addError(
                        new FieldError("code","code","Grupa o danym kodzie znajduje się już w bazie"));
            }
        }
        if (result.hasErrors()) {
            return "admin/group/form.html";
        } else
            groupService.saveGroup(group);
        return "redirect:/admin/group/add/spec/" + group.getId();
    }

    @GetMapping("/admin/group/add/spec/{id}")
    public String addGroupSpec(Model model, @PathVariable int id) {
        var group = groupService.getGroup(id);
        List<Specialization> spece = specService.findAll();
        model.addAttribute("groups", group);
        model.addAttribute("specs", spece);
        return "/admin/group/addSpec.html";
    }

    @PostMapping("/admin/group/add/spec/{id}")
    public String addGroupSpec(@Valid Groups group) {
        groupService.updateGroup(group);
        return "redirect:/admin/group/add/mode/" + group.getId();
    }

    @GetMapping("/admin/group/add/mode/{id}")
    public String addGroupMode(Model model, @PathVariable int id) {
        var group = groupService.getGroup(id);
        List<TeachingMode> mode = teachingService.findAll();
        model.addAttribute("groups", group);
        model.addAttribute("modes", mode);
        return "/admin/group/addMode.html";
    }

    @PostMapping("/admin/group/add/mode/{id}")
    public String addGroupMode(@Valid Groups group) {
        groupService.updateGroup(group);
        return "redirect:/admin/group/askSubject/" + group.getId();
    }

    @GetMapping("/admin/group/askSubject/{id}")
    public String askSubject(Model model, @PathVariable int id) {
        var group = groupService.getGroup(id);
        model.addAttribute("groups", group);
        return "/admin/group/askSubject";
    }

    @GetMapping(value = "/admin/group/addSubject/{id}")
    public String addSubjectGroup(Model model, @PathVariable int id) {
        Groups group = groupService.getGroup(id);
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("groups", group);
        model.addAttribute("subjects", subjects);
        return "/admin/group/addSubject.html";
    }


    @PostMapping(value = "/admin/group/addSubject/{id}")
    public String addSubjectGroup(@Valid Groups group, BindingResult result,@PathVariable int id, Model model) {
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("groups", group);
        model.addAttribute("subjects", subjects);
        if(result.hasErrors()) {
            return "admin/group/addSubject.html";
        } else {
            groupService.updateGroup(group);
            return "/admin/group/askStudent";
        }
    }

    @GetMapping("/admin/group/askStudent/{id}")
    public String askStudent(Model model, @PathVariable int id) {
        var group = groupService.getGroup(id);
        model.addAttribute("groups", group);
        return "/admin/group/askStudent";
    }

    @GetMapping(value = "/admin/group/addStudent/{id}")
    public String editGroup(Model model, @PathVariable int id) {
        Groups group = groupService.getGroup(id);
        List<Student> student = studentService.findAll();
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("groups", group);
        model.addAttribute("students", student);
        model.addAttribute("subjects", subjects);
        return "/admin/group/addStudent.html";
    }

    @PostMapping(value = "/admin/group/addStudent/{id}")
    public String editGroup(@Valid Groups group, BindingResult result, Model model, @PathVariable int id) {
        List<Student> student = studentService.findAll();
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("students", student);
        model.addAttribute("subjects", subjects);
        if(result.hasErrors()) {
            return "admin/group/addStudent.html";
        } else {
            groupService.updateGroup(group);
            return "redirect:/admin/group/list";
        }
    }

    @GetMapping( "/admin/group/delete/student/{id}")
    public String deleteStudentFromGroup(@PathVariable int id) {
        int group = studentService.getStudent(id).getGroup().getId();
        studentService.deleteGroup(id);
        return "redirect:/admin/group/details/" + group;
    }


    @GetMapping(value = "/admin/group/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Groups group = groupService.getGroup(id);
        List<Specialization> spece = specService.findAll();
        List<TeachingMode> mode = teachingService.findAll();
        List<Student> student = studentService.findAll();
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("groups", group);
        model.addAttribute("specs", spece);
        model.addAttribute("modes", mode);
        model.addAttribute("students", student);
        model.addAttribute("subjects", subjects);
        return "/admin/group/editGroup.html";
    }

    @PostMapping(value = "/admin/group/edit/{id}")
    public String edit(@Valid Groups group, BindingResult result, Model model, @PathVariable int id) {
        List<Specialization> spece = specService.findAll();
        List<TeachingMode> mode = teachingService.findAll();
        List<Student> student = studentService.findAll();
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("specs", spece);
        model.addAttribute("modes", mode);
        model.addAttribute("students", student);
        model.addAttribute("subjects", subjects);
        if(result.hasErrors()) {
            return "admin/group/editGroup.html";
        } else {
            groupService.updateGroup(group);
            return "redirect:/admin/group/details/" + id;
        }
    }

}
