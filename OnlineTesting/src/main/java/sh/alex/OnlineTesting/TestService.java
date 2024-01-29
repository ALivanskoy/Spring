package sh.alex.OnlineTesting;

import org.springframework.stereotype.Service;
import sh.alex.OnlineTesting.Model.Test;

@Service
public class TestService {

    private Test test;

    public TestService() {
        this.test = new Test();
    }

    public Test getTest() {
        return test;
    }
}
