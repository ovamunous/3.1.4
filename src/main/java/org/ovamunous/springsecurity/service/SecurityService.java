package org.ovamunous.springsecurity.service;


import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {
    String getAuthenticationName();
    String getAuthenticationRoles();
}
