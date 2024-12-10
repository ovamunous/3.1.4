package org.ovamunous.springsecurity.service;

import jakarta.transaction.Transactional;

import org.ovamunous.springsecurity.dao.UserDao;
import org.ovamunous.springsecurity.model.Role;
import org.ovamunous.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        if (userDao.getUserByUsername(user.getUsername()) != null) {
            return;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User newUser, int id, Set<Role> roles) {
        User oldUser = this.getUser(id);
        oldUser.setRoles(roles);
        if (!(oldUser.getPassword().equals(newUser.getPassword()))) {
            oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
        oldUser.setEmail(newUser.getEmail());
        oldUser.setUsername(newUser.getUsername());
        userDao.updateUser(oldUser);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

}
