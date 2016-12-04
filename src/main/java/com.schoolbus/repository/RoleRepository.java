package com.schoolbus.repository;

import com.schoolbus.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by t on 2016/12/3.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
