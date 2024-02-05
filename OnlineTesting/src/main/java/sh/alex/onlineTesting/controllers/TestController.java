package sh.alex.onlineTesting.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sh.alex.onlineTesting.model.tests.Question;
import sh.alex.onlineTesting.model.tests.Test;
import sh.alex.onlineTesting.services.TestService;


@Controller
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    public TestController(@Autowired TestService testService) {
        this.testService = testService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Test> getTestByPost() {

        return new ResponseEntity<>(testService.getTest(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Test> addQuestion(@RequestBody Question question) {

        if (question.getText() == null) throw new RuntimeException("Введённый вопрос пуст");
        if (question.getAnswers() == null) throw new RuntimeException("Ответы не предоставлены");
        if (question.getAnswers().isEmpty()) throw new RuntimeException("Список ответов пуст");

        testService.getTest().addQuestion(question);
        return new ResponseEntity<Test>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/table")
    public String showTest(Model model) {

        model.addAttribute("questions", testService.getTest().getQuestions());

        return "tests/testTable";
    }

}
