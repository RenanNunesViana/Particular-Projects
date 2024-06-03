package com.cariocar.angular.cariocarangular.repositories;

import com.cariocar.angular.cariocarangular.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByCpf(String cpf);
    List<User> findAllByFirstNameOrLastName(String firstName, String lastName);
    List<User> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
