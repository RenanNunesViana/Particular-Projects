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

	public User getByCpf(String cpf) {
		return userRepository.findByCpf(cpf).orElseThrow(() -> new UserNotFoundException(cpf));

	}

	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public User createUser(User user) {
		Optional<User> userTmp = userRepository.findByCpf(user.getCpf());
		if (userTmp.isEmpty()) {
			User userReturn = userRepository.save(user);
			userRepository.flush();
			return userReturn;
		}
		throw new UserAlreadyExistException(user.getCpf());
	}

	public User deleteUser(String cpf) {
		return userRepository.deleteByCpf(cpf).orElseThrow(() -> new UserNotFoundException(cpf));
	}

	public void deleteUser(Long id) {
		// getById is just to send an exception if needed
		getById(id);
		userRepository.deleteById(id);
	}

	public User editUser(User user) {
		Optional<User> userTmp = userRepository.findByCpf(user.getCpf());
		if (userTmp.isPresent()) {
			if (user.getFirstName() != null)
				userTmp.get().setFirstName(user.getFirstName());
			if (user.getLastName() != null)
				userTmp.get().setLastName(user.getLastName());
			if (user.getPassword() != null)
				userTmp.get().setPassword(user.getPassword());
			if (user.getRole() != null)
				userTmp.get().setRole(user.getRole());
			if (user.getCpf() != null)
				userTmp.get().setCpf(user.getCpf());
			if (user.getCel() != null)
				userTmp.get().setCel(user.getCel());
			if (user.getEmail() != null)
				userTmp.get().setEmail(user.getEmail());
			return userRepository.save(userTmp.get());
		}
		throw new UserNotFoundException(user.getCpf());
	}

	public List<User> listUsers() {
		return userRepository.findAll();
	}
}