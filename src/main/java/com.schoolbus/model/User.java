package com.schoolbus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by t on 2016/11/23.
 */
@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;//手机号
    private String lName;
    private String password;

    private Integer status;
    private Timestamp birthday;
    @Transient
    private String code;
    @ManyToMany()
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id") )
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Role> roles=new ArrayList<Role>();
    @ManyToMany()
    @JoinTable(name = "user_tickets_paper",joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "tickets_paper_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TicketsPaper> ticketsPapers= new ArrayList<TicketsPaper>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<Role> roles=this.getRoles();
        for (Role role:roles)
        {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
