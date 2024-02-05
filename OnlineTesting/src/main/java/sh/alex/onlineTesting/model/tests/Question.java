package sh.alex.onlineTesting.model.tests;

import lombok.Getter;

import java.util.List;

@Getter
public class Question {

    //Текст вопроса
    private final String text;

    //Список ответов
    private final List<Answer> answers;

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return text +"\n"+answers+"\n";
    }
}
