package com.cariocar.angular.cariocarangular.services;

import com.cariocar.angular.cariocarangular.models.User;
import com.cariocar.angular.cariocarangular.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

        // getById returns user by id
        @Test
        public void test_getById_returns_user_by_id() {
            // Arrange
            ObjectId id = new ObjectId();
            User user = new User();
            user.setId(id);
            Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

            // Act
            User result = userService.getById(id);

            // Assert
            assertEquals(user, result);
        }

        // getByCpf returns user by cpf
        @Test
        public void test_getByCpf_returns_user_by_cpf() {
            // Arrange
            String cpf = "123456789";
            User user = new User();
            user.setCpf(cpf);
            Mockito.when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(user));

            // Act
            User result = userService.getByCpf(cpf);

            // Assert
            assertEquals(user, result);
        }

        // createUser saves new user
        @Test
        public void test_createUser_saves_new_user() {
            // Arrange
            User user = new User();
            Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
            Mockito.when(userRepository.save(user)).thenReturn(user);

            // Act
            User result = userService.createUser(user);

            // Assert
            assertEquals(user, result);
        }

        // getById throws IllegalArgumentException when user not found
        @Test
        public void test_getById_throws_IllegalArgumentException_when_user_not_found() {
            // Arrange
            ObjectId id = new ObjectId();
            Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> userService.getById(id));
        }

        // getByCpf throws IllegalArgumentException when user not found
        @Test
        public void test_getByCpf_throws_IllegalArgumentException_when_user_not_found() {
            // Arrange
            String cpf = "123456789";
            Mockito.when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> userService.getByCpf(cpf));
        }

        // createUser throws IllegalArgumentException when user already exists
        @Test
        public void test_createUser_throws_IllegalArgumentException_when_user_already_exists() {
            // Arrange
            User user = new User();
            Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
        }

    }
