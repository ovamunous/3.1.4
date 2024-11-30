package org.ovamunous.springsecurity.dao;

import org.ovamunous.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getUsers();
    User getUserById(long id);
    User getUserByUsername(String username);
}
