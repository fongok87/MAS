package MASProject.s7973.converters;

import MASProject.s7973.model.Teacher;
import MASProject.s7973.services.StudentService;
import MASProject.s7973.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TeacherConverter implements Converter<String, Teacher> {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Override
    public Teacher convert(String id) {
        int parsedId = Integer.parseInt(id);
        int index = parsedId - studentService.findAll().size();
        return teacherService.findAll().get(index);
    }
}
