package com.cariocar.angular.cariocarangular.repositories;

import com.cariocar.angular.cariocarangular.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    List<User> findAllByFirstNameOrLastName(String firstName, String lastName);
    List<User> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
