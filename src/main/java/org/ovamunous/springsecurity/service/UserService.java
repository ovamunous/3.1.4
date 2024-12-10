package org.ovamunous.springsecurity.service;



import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void addUser(User user);
    void updateUser(User user, int id, Set<Role> roles);
    void deleteUser(User user);
    List<User> getUsers();
    User getUser(long id);
    User getUserByUsername(String username);
}
