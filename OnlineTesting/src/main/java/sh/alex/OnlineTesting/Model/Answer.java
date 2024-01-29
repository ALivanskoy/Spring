package sh.alex.OnlineTesting.Model;


import java.util.Objects;

public class Answer {

    //Текст ответа
    final private String answer;

    //Является ли ответ верным
    final private Boolean isCorrect;

    public Answer(String answer, Boolean isCorrect) {
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return answer;
    }
}
