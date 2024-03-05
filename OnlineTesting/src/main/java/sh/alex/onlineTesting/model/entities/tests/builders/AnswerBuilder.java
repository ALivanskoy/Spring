package sh.alex.onlineTesting.model.entities.tests.builders;

import org.springframework.stereotype.Component;
import sh.alex.onlineTesting.model.entities.tests.Answer;

@Component
public class AnswerBuilder {

    private Long id;
    private String text;
    private Boolean correct;
    private Long questionId;

    public AnswerBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public AnswerBuilder setCorrect(Boolean correct) {
        this.correct = correct;
        return this;
    }

    public AnswerBuilder setQuestionId(Long questionId) {
        this.questionId = questionId;
        return this;
    }

    public Answer build() {
        Answer answer = new Answer(text, correct);
        answer.setId(id);
        answer.setQuestionId(questionId);
        return answer;
    }
}
