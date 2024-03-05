package sh.alex.onlineTesting.model.entities.tests.factory;

import org.springframework.stereotype.Component;
import sh.alex.onlineTesting.model.entities.tests.Answer;
import sh.alex.onlineTesting.model.entities.tests.builders.AnswerBuilder;

@Component
public class AnswerFactory {

    public Answer createAnswer(String text, Boolean correct) {
        return new AnswerBuilder()
                .setText(text)
                .setCorrect(correct)
                .build();
    }
}
