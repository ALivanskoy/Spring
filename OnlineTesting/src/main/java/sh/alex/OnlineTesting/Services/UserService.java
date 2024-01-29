package sh.alex.OnlineTesting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.alex.OnlineTesting.Model.Users.User;

@Service
public class UserService {

    private final NotificationService notificationService;


    @Autowired
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public User createUser (int id, String fitstName, String secondName) {

        User user = new User(id, fitstName, secondName);

        notificationService.notifyUser(user);

        return user;
    }
}
