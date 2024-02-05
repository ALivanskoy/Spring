package sh.alex.onlineTesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.alex.onlineTesting.model.UserRepository;
import sh.alex.onlineTesting.model.entities.UserEntity;
import sh.alex.onlineTesting.model.users.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final NotificationService notificationService;

    private final UserRepository repository;


    @Autowired
    public UserServiceImp(NotificationService notificationService, UserRepository repository) {
        this.notificationService = notificationService;
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream().map(UserEntity::toUser).toList();
    }

    @Transactional
    public User createUser(String firstName, String secondName) {

        User user = new User(firstName, secondName);

        notificationService.notifyUser(user);

        return repository.saveAndFlush(UserEntity.fromUser(user)).toUser();
    }

    @Override
    public User getUserById(Long id) {
        try {
            return repository.getUserById(id).toUser();
        } catch (Exception e) {
            notificationService.notify(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public User updateUser(Long id, User refreshUser) {

        UserEntity userEntity = repository.findById(id).get();

        userEntity.setFirstName(refreshUser.getFirstName());
        userEntity.setSecondName(refreshUser.getSecondName());
        return repository.saveAndFlush(userEntity).toUser();
    }

    @Override
    public void deleteUser(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
