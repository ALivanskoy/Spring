package sh.alex.OnlineTesting.Services;

import org.springframework.stereotype.Service;
import sh.alex.OnlineTesting.Model.Tests.Test;

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
