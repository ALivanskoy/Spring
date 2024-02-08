package sh.alex.onlineTesting.model.services;

import sh.alex.onlineTesting.model.users.User;

import java.util.List;

public interface UserService {


    public List<User> getAll();

    public User create(String fitstName, String secondName);

    public User getById(Long id);

    public User update(Long id, User user);

    public void delete(Long id);


}
