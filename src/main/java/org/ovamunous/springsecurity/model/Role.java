package org.ovamunous.springsecurity.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yaml.snakeyaml.events.Event;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table
public class Role  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    private String role;

    @Transient
    @Getter
    @Setter
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {

    }
    public Role(Long id) {
        this.id = id;
    }
    public Role(long id, String role) {
        this.role = role;
        this.id = id;
    }
    public Role(String role) {
        this.role = role;
    }

    public String getRoleFullString() {
        return "Role [id=" + id + ", role=" + role + "]";
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    @Override
    public String toString() {
        return role.substring(5, role.length());
    }

}
