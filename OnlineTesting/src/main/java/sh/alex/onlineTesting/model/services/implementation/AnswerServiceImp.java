package sh.alex.onlineTesting.model.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.alex.onlineTesting.model.entities.AnswerEntity;
import sh.alex.onlineTesting.model.repository.AnswerRepository;
import sh.alex.onlineTesting.model.tests.Answer;
import sh.alex.onlineTesting.model.services.AnswerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImp implements AnswerService {

    private final AnswerRepository answerRepository;


    @Override
    public List<Answer> getAll() {

        return answerRepository.findAll().stream().map(AnswerEntity::toAnswer).toList();
    }

    @Override
    public List<Answer> getByQuestionId(Long id) {
        return answerRepository.getByQuestionId(id).stream().map(AnswerEntity::toAnswer).toList();
    }

    @Override
    public Answer create(String text, Boolean correct) {
        return null;
    }


    @Override
    public Answer getById(Long id) {
        return null;
    }

    @Override
    public Answer update(Long id, Answer answer) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
