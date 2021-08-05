package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(Long id);

    void delete(Long id);

    void update(User user);

    void create(User user);
}
