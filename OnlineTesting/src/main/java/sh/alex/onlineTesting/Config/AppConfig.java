package sh.alex.onlineTesting.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import sh.alex.onlineTesting.model.entities.tests.factory.AnswerFactory;
import sh.alex.onlineTesting.model.entities.tests.factory.QuestionFactory;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public AnswerFactory answerFactory() {
        return new AnswerFactory();
    }

    @Bean
    public QuestionFactory questionFactory() {
        return new QuestionFactory();
    }

}

