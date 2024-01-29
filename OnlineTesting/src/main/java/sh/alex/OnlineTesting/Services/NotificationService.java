package sh.alex.OnlineTesting.Services;

import org.springframework.stereotype.Service;
import sh.alex.OnlineTesting.Model.Users.User;

@Service
public class NotificationService {

    public void notifyUser (User user) {
        System.out.println("Юзер создан: "+user.toString());
    }
}
