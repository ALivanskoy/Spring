package sh.alex.onlineTesting.model.entities.tests.factory;

import org.springframework.stereotype.Component;
import sh.alex.onlineTesting.model.entities.tests.Question;
import sh.alex.onlineTesting.model.entities.tests.builders.QuestionBuilder;

@Component
public class QuestionFactory {

    public Question createQuestion(String text) {
        return new QuestionBuilder()
                .setText(text)
                .build();
    }
}
