package sh.alex.onlineTesting.model.tests;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Answer {

    //Текст ответа
    private String text;

    //Является ли ответ верным
    private Boolean correct;


    public Answer(String text, Boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return text;
    }
}
