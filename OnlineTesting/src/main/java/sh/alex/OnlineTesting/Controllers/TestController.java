package sh.alex.OnlineTesting.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sh.alex.OnlineTesting.Model.Tests.Question;
import sh.alex.OnlineTesting.Model.Tests.Test;
import sh.alex.OnlineTesting.Services.TestService;


@Controller
@RequestMapping("/")
public class TestController {

    private TestService testService;

    public TestController(@Autowired TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/", method =RequestMethod.GET)
    public ResponseEntity<String> hello () {
        return new ResponseEntity<>("qwerty", HttpStatus.OK);
    }

    @RequestMapping (value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Test> getTestByPost () {

        return new ResponseEntity<>(testService.getTest(), HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<Test> addQuestion(@RequestBody Question question) {

        if (question.getQuestion() == null ) throw new RuntimeException("Введённый вопрос пуст");
        if (question.getAnswers() == null) throw new RuntimeException("Ответы не предоставлены");
        if (question.getAnswers().isEmpty() ) throw new RuntimeException("Список ответов пуст");

        testService.getTest().addQuestion(question);

        return new ResponseEntity<Test>(HttpStatus.CREATED);
    }

}
