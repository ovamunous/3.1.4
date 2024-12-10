package org.ovamunous.springsecurity.service;

import org.ovamunous.springsecurity.dao.UserDao;
import org.ovamunous.springsecurity.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.stream.Collectors;

@Service
public class SecurityServiceImp implements SecurityService {

    private final UserDao userDao;

    SecurityServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getAuthentication(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String currentUser = authentication.getName();
            String currentRole = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(", "));

            model.addAttribute("currentUser", currentUser);
            model.addAttribute("currentRole", currentRole);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
