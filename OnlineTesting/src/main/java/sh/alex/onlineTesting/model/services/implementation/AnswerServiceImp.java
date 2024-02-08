package sh.alex.onlineTesting.model.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.alex.onlineTesting.model.entities.AnswerEntity;
import sh.alex.onlineTesting.model.entities.tests.Answer;
import sh.alex.onlineTesting.model.repository.AnswerRepository;
import sh.alex.onlineTesting.model.services.AnswerService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImp implements AnswerService {

    @Autowired
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
    public Answer create(Answer answer, Long questionId) {

        AnswerEntity answerEntity = AnswerEntity.fromAnswer(answer);
        answerEntity.setQuestionId(questionId);

        return repository.saveAndFlush(answerEntity).toAnswer();
    }


    @Override
    public Answer getById(Long id) {
        Optional<AnswerEntity> optionalAnswer = repository.findById(id);
        return optionalAnswer.map(AnswerEntity::toAnswer).orElse(null);
    }

    @Override
    @Transactional
    public Answer update(Long id, Answer answer) {

        Optional<AnswerEntity> answerEntityOptional = repository.findById(id);

        if (answerEntityOptional.isPresent()) {
            AnswerEntity answerEntity = answerEntityOptional.get();
            answerEntity.setAnswerText(answer.getText());
            answerEntity.setCorrect(answer.getCorrect());
            return repository.saveAndFlush(answerEntity).toAnswer();
        } else return null;

    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

    }
}
