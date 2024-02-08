package sh.alex.onlineTesting.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import sh.alex.onlineTesting.model.entities.tests.Answer;

@Data
@Entity
@Table(name = "answers")
@NoArgsConstructor
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String answerText;

    @Column(nullable = false)
    private Boolean correct;

    @Column(nullable = false)
    private Long questionId;

    public static AnswerEntity fromAnswer(Answer answer) {

        AnswerEntity answerEntity = new AnswerEntity();

        answerEntity.setAnswerText(answer.getText());
        answerEntity.setCorrect(answer.getCorrect());

        return answerEntity;
    }

    public Answer toAnswer () {

        Answer answer = new Answer();
        answer.setId(this.id);
        answer.setText(this.answerText);
        answer.setCorrect(this.correct);
        answer.setQuestionId(this.questionId);

        return answer;
    }

}
