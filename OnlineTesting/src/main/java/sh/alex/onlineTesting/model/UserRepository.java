package sh.alex.onlineTesting.model;


import org.springframework.data.jpa.repository.JpaRepository;
import sh.alex.onlineTesting.model.entities.UserEntity;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity getUserById (Long id);

    public List<UserEntity> findAll();
}
