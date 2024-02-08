package sh.alex.onlineTesting.model.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sh.alex.onlineTesting.model.entities.AnswerEntity;
import sh.alex.onlineTesting.model.repository.AnswerRepository;
import sh.alex.onlineTesting.model.services.AnswerService;
import sh.alex.onlineTesting.model.tests.Answer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImp implements AnswerService {

    private final AnswerRepository repository;


    @Override
    public List<Answer> getAll() {

        return repository.findAll().stream().map(AnswerEntity::toAnswer).toList();
    }

    @Override
    public List<Answer> getByQuestionId(Long id) {
        return repository.getByQuestionId(id).stream().map(AnswerEntity::toAnswer).toList();
    }

    @Override
    public Answer create(String text, Boolean correct) {

        Answer answer = new Answer(text, correct);

        repository.saveAndFlush(AnswerEntity.fromAnswer(answer));

        return answer;
    }


    @Override
    public Answer getById(Long id) {
        return repository.findById(id).get().toAnswer();
    }

    @Override
    public Answer update(Long id, Answer answer) {

        AnswerEntity answerEntity = repository.findById(id).get();
        answerEntity.setAnswerText(answer.getText());
        answerEntity.setCorrect(answer.getCorrect());
        return repository.saveAndFlush(answerEntity).toAnswer();
    }

    @Override
    public void delete(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

    }
}
