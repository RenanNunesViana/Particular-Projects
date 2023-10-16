package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.User;

import java.util.List;

public interface UserService {

    public User getById(Long id);
    public User getByCpf(String cpf);
    public User createUser(User user);
    public User deleteUser(String cpf);
    public void deleteUser(Long id);
    public User editUser(Long id, User user);
    public List<User> listUser();
    public List<User> listUsers(String name);
}
