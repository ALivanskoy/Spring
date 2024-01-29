package sh.alex.OnlineTesting.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sh.alex.OnlineTesting.Model.Users.User;
import sh.alex.OnlineTesting.Services.UserService;


@Controller
@RequestMapping ("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping (value = "/create", method = RequestMethod.GET)
    public ResponseEntity<User> createUser  (@RequestParam Integer id, @RequestParam String firstName, @RequestParam String secondName) {

        User user = userService.createUser(id, firstName, secondName);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);


    }
}
