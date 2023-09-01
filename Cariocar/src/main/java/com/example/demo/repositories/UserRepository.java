package com.example.demo.repositories;

import com.example.demo.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
	public Optional<User> findByCpf(String cpf);
	public Optional<User> deleteByCpf(String cpf);
}