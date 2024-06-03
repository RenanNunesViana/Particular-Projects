package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    public User getById(ObjectId id);
    public User getByCpf(String cpf);
    public User createUser(User user);
    public User deleteUser(String cpf);
    public void deleteUser(ObjectId id);
    public User editUser(User user);
    public List<User> listUser();
    public List<User> listUsers(String name);
}
