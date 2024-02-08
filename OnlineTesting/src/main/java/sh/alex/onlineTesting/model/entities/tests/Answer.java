package sh.alex.onlineTesting.model.entities.tests;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Answer {

    private Long id;
    //Текст ответа
    private String text;

    //Является ли ответ верным
    private Boolean correct;

    private Long questionId;


    public Answer(String text, Boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return text;
    }
}
