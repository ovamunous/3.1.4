package org.ovamunous.springsecurity.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;

public interface SecurityService extends UserDetailsService {
    void getAuthentication(ModelMap model);
}
