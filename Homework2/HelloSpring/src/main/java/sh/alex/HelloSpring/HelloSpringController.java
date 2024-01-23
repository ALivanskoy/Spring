package sh.alex.HelloSpring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringController {

    private final HelloSpringTimeService helloSpringTimeService;

    @Autowired
    public HelloSpringController(HelloSpringTimeService helloSpringTimeService) {
        this.helloSpringTimeService = helloSpringTimeService;
    }


    @GetMapping("/")
    public String helloPage () {
        return this.helloSpringTimeService.getTime();
    }

}
