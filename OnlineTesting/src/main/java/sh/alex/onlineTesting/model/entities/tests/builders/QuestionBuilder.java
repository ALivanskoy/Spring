package sh.alex.onlineTesting.model.entities.tests.builders;

import org.springframework.stereotype.Component;
import sh.alex.onlineTesting.model.entities.tests.Question;

@Component
public class QuestionBuilder {

    private Long id;
    private String text;

    public QuestionBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public Question build() {
        Question question = new Question(text);
        question.setId(id);
        return question;
    }
}
