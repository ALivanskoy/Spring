package sh.alex.onlineTesting.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import sh.alex.onlineTesting.model.tests.Question;
import sh.alex.onlineTesting.model.users.User;

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

        questionEntity.setText(questionEntity.getText());

        return questionEntity;
    }

    public Question toQuestion () {

        Question question = new Question();
        question.setText(this.text);

        return question;
    }
}
