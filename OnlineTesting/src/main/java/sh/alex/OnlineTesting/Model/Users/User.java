package sh.alex.OnlineTesting.Model.Users;

import java.util.Objects;

public class User {

    private int id;

    private String fitstName, secondName;

    public User(int id, String fitstName, String secondName) {
        this.id = id;
        this.fitstName = fitstName;
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFitstName() {
        return fitstName;
    }

    public void setFitstName(String fitstName) {
        this.fitstName = fitstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(fitstName, user.fitstName) && Objects.equals(secondName, user.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fitstName, secondName);
    }

    @Override
    public String toString() {
        return fitstName+' '+secondName;
    }
}
