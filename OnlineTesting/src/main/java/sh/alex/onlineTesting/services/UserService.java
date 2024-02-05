package sh.alex.onlineTesting.services;

import sh.alex.onlineTesting.model.users.User;

import java.util.List;

public interface UserService {



    public List<User> getAllUsers();

    public User createUser(String fitstName, String secondName);

    public User getUserById(Long id);

    public User updateUser(Long id, User user);

    public void deleteUser(Long id);


}
