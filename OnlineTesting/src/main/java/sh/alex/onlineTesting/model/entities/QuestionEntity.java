package sh.alex.onlineTesting.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import sh.alex.onlineTesting.model.entities.tests.Question;

@Data
@Entity
@Table(name = "questions")
@NoArgsConstructor
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String text;



    public static QuestionEntity fromQuestion(Question question) {

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setText(question.getText());
        return questionEntity;
    }

    public Question toQuestion () {

        Question question = new Question();
        question.setText(this.text);
        question.setId(this.id);

        return question;
    }
}
