package sh.alex.onlineTesting.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import sh.alex.onlineTesting.model.tests.Answer;
import sh.alex.onlineTesting.model.tests.Question;

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
        answer.setText(this.answerText);
        answer.setCorrect(this.correct);

        return answer;
    }

}
