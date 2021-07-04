package com.spring_boot.springbootproj.dao;


import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
    public Role findByNameRole(String roleName);
}
