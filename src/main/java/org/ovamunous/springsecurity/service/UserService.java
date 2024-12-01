package org.ovamunous.springsecurity.service;



import org.ovamunous.springsecurity.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> getUsers();
    User getUser(long id);
    User getUserByUsername(String username);
}
