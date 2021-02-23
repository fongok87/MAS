package MASProject.s7973.converters;

import MASProject.s7973.model.Student;
import MASProject.s7973.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter implements Converter<String, Student> {

    @Autowired
    private StudentService studentService;

    @Override
    public Student convert(String id) {
        int parsedId = Integer.parseInt(id);
        int index = parsedId - 1;
        return studentService.findAll().get(index);
    }
}