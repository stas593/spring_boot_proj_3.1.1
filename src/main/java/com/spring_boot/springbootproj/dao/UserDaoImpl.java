package com.spring_boot.springbootproj.dao;

import com.spring_boot.springbootproj.models.Role;
import com.spring_boot.springbootproj.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        this.entityManager.persist(user);
    }


    @Override
    public void deleteUser(long id) {
        entityManager.createQuery("delete from User where id= :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public User getUser(long id) {
        User user = entityManager.find(User.class, id);
        return user;

    }

    @Override
    public User findByLogin(String userName) {
        return (User) entityManager.createQuery("FROM User where mail = :mail").setParameter("mail", userName).getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.createQuery("update User set name = :name, surname = :surname, mail = :mail, age = :age, password = :pass where id = :id")
                .setParameter("pass", user.getPassword())
                .setParameter("name", user.getName())
                .setParameter("surname", user.getSurname())
                .setParameter("mail", user.getMail())
                .setParameter("age", user.getAge())
                .setParameter("id",  user.getId())
                .executeUpdate();
        entityManager.find(User.class, user.getId()).setRoles(user.getRoles());
    }

    @Override
    public List<User> getAllUsers(){
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void addRoleToUser(long userId, Role role) {
        User user = entityManager.find(User.class, userId);
        user.addRoleToUser(role);
    }

    @Override
    public Set<Role> getAllRolesFromUser(long userId) {
        return entityManager.find(User.class, userId).getRoles();
    }
}
