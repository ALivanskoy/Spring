package sh.alex.HelloSpring;

import org.springframework.stereotype.Service;


@Service
public class HelloSpringTimeService {

    private String prepareTime () {
        StringBuilder sb = new StringBuilder();
        sb.append("Today is: ").append(java.time.LocalDate.now().toString()).append("\n");
        sb.append("Now: ").append(java.time.LocalTime.now().toString()).append("\n");
        return sb.toString();
    }

    public String getTime(){
        return prepareTime();
    }
}
