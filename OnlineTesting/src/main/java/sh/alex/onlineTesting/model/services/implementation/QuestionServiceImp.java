package sh.alex.onlineTesting.model.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.alex.onlineTesting.model.entities.QuestionEntity;
import sh.alex.onlineTesting.model.repository.QuestionRepository;
import sh.alex.onlineTesting.model.services.QuestionService;
import sh.alex.onlineTesting.model.entities.tests.Question;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private final QuestionRepository repository;

    @Override
    public List<Question> getAll() {
        return repository.findAll().stream().map(QuestionEntity::toQuestion).toList();
    }

    @Override
    @Transactional
    public Question create(String text) {

        Question question = new Question(text);

        return repository.saveAndFlush(QuestionEntity.fromQuestion(question)).toQuestion();
    }

    @Override
    public Question create(Question question) {
        return repository.saveAndFlush(QuestionEntity.fromQuestion(question)).toQuestion();
    }


    @Override
    public Question getById(Long id) {

        Optional<QuestionEntity> questionEntityOptional = repository.findById(id);

        return questionEntityOptional.map(QuestionEntity::toQuestion).orElse(null);
    }

    @Override
    @Transactional
    public Question update(Long id, Question question) {

        Optional<QuestionEntity> questionEntityOptional = repository.findById(id);

        if (questionEntityOptional.isPresent()) {
            QuestionEntity questionEntity = questionEntityOptional.get();
            questionEntity.setText(question.getText());

            return repository.saveAndFlush(questionEntity).toQuestion();
        } else return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (repository.existsById(id)) repository.deleteById(id);

    }
}
