package MASProject.s7973.converters;

import MASProject.s7973.model.Subject;
import MASProject.s7973.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements Converter<String, Subject> {

    @Autowired
    private SubjectService subjectService;

    @Override
    public Subject convert(String id) {
        int parsedId = Integer.parseInt(id);
        int index = parsedId - 1;
        return subjectService.findAll().get(index);
    }
}
