package sh.alex.onlineTesting.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sh.alex.onlineTesting.model.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity getUserById(Long id);

}
