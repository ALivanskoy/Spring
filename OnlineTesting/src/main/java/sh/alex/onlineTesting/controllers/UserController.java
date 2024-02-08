package sh.alex.onlineTesting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sh.alex.onlineTesting.model.entities.users.User;
import sh.alex.onlineTesting.model.services.UserService;

import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPage(Model model) {

        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "users/users";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUserPage() {
        return "users/createUser";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createUser(@RequestParam String firstName, @RequestParam String secondName) {

        userService.create(firstName, secondName);

        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUser (@PathVariable Long id, Model model) {

        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "users/editUser";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser (@RequestParam Long id, @RequestParam String firstName, @RequestParam String secondName) {

        User user = new User(firstName, secondName);

        userService.update(id, user);
        return "redirect:/users";
    }


}
