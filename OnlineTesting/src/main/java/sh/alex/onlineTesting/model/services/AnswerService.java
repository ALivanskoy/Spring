package sh.alex.onlineTesting.model.services;

import sh.alex.onlineTesting.model.tests.Answer;

import java.util.List;

public interface AnswerService {

    public List<Answer> getAll();

    public List<Answer> getByQuestionId(Long id);

    public Answer create(String text, Boolean correct);

    public Answer getById(Long id);

    public Answer update(Long id, Answer answer);

    public void delete(Long id);
}
