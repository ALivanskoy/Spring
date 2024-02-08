package sh.alex.onlineTesting.model.entities.tests;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Question {

    private Long id;

    //Текст вопроса
    private String text;

    public Question(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
