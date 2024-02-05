package sh.alex.onlineTesting.services;

import org.springframework.stereotype.Service;
import sh.alex.onlineTesting.model.users.User;

@Service
public class NotificationService {

    public void notifyUser (User user) {
        System.out.println("Юзер создан: "+user.toString());
    }

    public void  notify (String s) {
        System.out.println(s);
    }
}
