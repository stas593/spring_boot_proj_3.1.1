package com.spring_boot.springbootproj.dao;

import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface UserDao extends JpaRepository<User, Long> {
    public User findByMail(String mail);
}
