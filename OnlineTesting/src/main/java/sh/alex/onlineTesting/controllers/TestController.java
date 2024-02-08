package sh.alex.onlineTesting.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sh.alex.onlineTesting.model.entities.tests.Test;
import sh.alex.onlineTesting.model.services.implementation.TestService;


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

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Test> addQuestion(@RequestBody Question question) {
//        testService.getTest().addQuestion(question);
//        return new ResponseEntity<Test>(HttpStatus.CREATED);
//}

//    @RequestMapping(value = "/table")
//    public String showTest(Model model) {
//
//        model.addAttribute("questions", testService.getTest().getQuestions());
//
//        return "tests/testTable";
//    }

}
