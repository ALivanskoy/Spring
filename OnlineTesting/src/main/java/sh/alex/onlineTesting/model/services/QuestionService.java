package sh.alex.onlineTesting.model.services;

import sh.alex.onlineTesting.model.entities.QuestionEntity;
import sh.alex.onlineTesting.model.entities.tests.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> getAll();

    public Question create(String text);

    public Question create(Question question);

    public Question getById(Long id);

    public Question update(Long id, Question question);

    public void delete(Long id);
}
