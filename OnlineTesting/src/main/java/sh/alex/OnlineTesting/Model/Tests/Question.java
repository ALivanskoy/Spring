package sh.alex.OnlineTesting.Model.Tests;

import java.util.List;

public class Question {

    //Текст вопроса
    private final String question;

    //Список ответов
    private final List<Answer> answers;


    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return question+"\n"+answers+"\n";
    }
}
