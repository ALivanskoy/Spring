package sh.alex.onlineTesting.model.entities;


import jakarta.persistence.*;
import lombok.Data;
import sh.alex.onlineTesting.model.users.User;


@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String firstName;

    @Column (nullable = false)
    private String secondName;

    public static UserEntity fromUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setSecondName(user.getSecondName());
        return userEntity;
    }

    public User toUser () {
        User user = new User(this.id, this.firstName, this.secondName);
        return user;
    }
}
