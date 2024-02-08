package sh.alex.onlineTesting.model.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.alex.onlineTesting.model.entities.UserEntity;
import sh.alex.onlineTesting.model.repository.UserRepository;
import sh.alex.onlineTesting.model.services.UserService;
import sh.alex.onlineTesting.model.entities.users.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository repository;


    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll().stream().map(UserEntity::toUser).toList();
    }

    @Transactional
    public User create(String firstName, String secondName) {

        User user = new User(firstName, secondName);

        return repository.saveAndFlush(UserEntity.fromUser(user)).toUser();
    }

    @Override
    public User getById(Long id) {

        Optional<UserEntity> userEntityOptional = repository.findById(id);

        return userEntityOptional.map(UserEntity::toUser).orElse(null);
    }

    @Override
    @Transactional
    public User update(Long id, User refreshUser) {

        Optional<UserEntity> userEntityOptional = repository.findById(id);

        if(userEntityOptional.isPresent()) {

            UserEntity userEntity = userEntityOptional.get();
            userEntity.setFirstName(refreshUser.getFirstName());
            userEntity.setSecondName(refreshUser.getSecondName());
            return repository.saveAndFlush(userEntity).toUser();

        } else return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
