package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    
	@Autowired
	private UserRepository userRepository;
    
    public User getByCpf(String cpf){
        return userRepository.findByCpf(cpf)
        		.orElseThrow(() -> new UserNotFoundException(cpf));
        
    }
    
    public User getById(Long id){
    	return userRepository.findById(id)
    			.orElseThrow(() -> new UserNotFoundException(id));
    }
    
    public User createUser(User user){
        Optional<User> userTmp = userRepository.findByCpf(user.getCpf());
        if(userTmp.isEmpty()) {
        	User userReturn = userRepository.save(user);
        	userRepository.flush();
        	return userReturn;
        }
        throw new UserAlreadyExistException(user.getCpf());
    }
    
    public void deleteUser(String cpf) {
    	userRepository.deleteByCpf(cpf);
    }
    
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
    
    public User editUser(User user) {
    	Optional<User> userTmp = userRepository.findByCpf(user.getCpf());
    	if(userTmp.isPresent()) {
    		userTmp.get().setFirstName(user.getFirstName());
    		userTmp.get().setLastName(user.getLastName());
    		userTmp.get().setPassword(user.getPassword());
    		userTmp.get().setRole(user.getRole());
    		return userRepository.save(userTmp.get());
    	}
    	throw new UserNotFoundException(user.getCpf());
    }
    
    public List<User> listUsers(){
    	return userRepository.findAll();
    }
}