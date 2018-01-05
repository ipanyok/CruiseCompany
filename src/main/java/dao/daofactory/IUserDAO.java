package dao.daofactory;

import entity.User;
import java.util.List;

public interface IUserDAO {
    List<User> findAll();
    User findById(int id);
    User findByLogin(String login);
    void deleteUserById(int id);
    void deleteUser(String name);
    boolean createUser(User user);
}
