package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.User;
import com.cariocar.angular.cariocarangular.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public User getByCpf(String cpf) {
        return userRepository.findByCpf(cpf).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public User createUser(User user) {
        Optional<User> userTmp = userRepository.findById(user.getId());
        if(userTmp.isEmpty())
            return userRepository.save(user);
        throw new IllegalArgumentException("this user already exist");
    }

    @Override
    public User deleteUser(String cpf) {
        User user = userRepository.findByCpf(cpf).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("user not found"));
        userRepository.delete(user);
    }

    @Override
    public User editUser(User user) {

        Optional<User> userTmp = userRepository.findById(user.getId());
        if(userTmp.isPresent()) {
            User userToUpdate = userTmp.get();
            if (user.getCpf()!=null)
                userToUpdate.setCpf(user.getCpf());
            if (user.getFirstName()!=null)
                userToUpdate.setFirstName(user.getFirstName());
            if(user.getLastName()!=null)
                userToUpdate.setLastName(user.getLastName());
            if (user.getCel()!=null)
                userToUpdate.setCel(user.getCel());
            if(user.getEmail()!=null)
                userToUpdate.setEmail(user.getEmail());
            if(user.getPassword()!=null)
                userToUpdate.setPassword(user.getPassword());
            userRepository.save(userToUpdate);
            return userToUpdate;
        }else
            throw new IllegalArgumentException("User Not Found");
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listUsers(String name){
        /*return userRepository.findAllByFirstNameOrLastName(name, name);*/
        return userRepository.findAllByFirstNameContainingOrLastNameContaining(name, name);
    }
}
