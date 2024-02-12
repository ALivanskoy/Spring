package sh.alex.onlineTesting.model.entities.users;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor

public class User {

    private Long id;

    private String firstName, secondName, username;

    private Set<Role> roles;

    public enum Role {
        SUPERADMIN,
        ADMIN,
        USER
    }


    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.roles = new HashSet<>();
    }

    @Override
    public String toString() {
        return firstName +' '+secondName;
    }

}
