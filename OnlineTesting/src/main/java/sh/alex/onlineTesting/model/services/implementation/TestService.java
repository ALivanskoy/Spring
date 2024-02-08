package sh.alex.onlineTesting.model.services.implementation;

import org.springframework.stereotype.Service;
import sh.alex.onlineTesting.model.tests.Test;

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
