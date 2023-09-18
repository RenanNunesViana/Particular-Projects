package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        user1 = new User("123456654", "123456", "josef", "anything");
        user2 = new User("123321456", "123465", "Antony", "anyOtherThing");
    }
    @Test
    public void testCreateUser() {
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        User savedUser = userService.createUser(user1);
        User saveUser2 = userService.createUser(user2);
        assertEquals(user1, savedUser);
        assertEquals(user2, saveUser2);
        assertNotEquals(user1, user2);
        assertNotEquals(savedUser, saveUser2);
        when(userRepository.save(Mockito.any(User.class))).thenThrow(UserAlreadyExistException.class);
       assertThrows(UserAlreadyExistException.class, () -> userService.createUser(user2));
    }

    @Test
    void testDeleteUser() {
        User newUser = new User();
        newUser.setCpf("123456789");
        Optional<User> optUser = Optional.of(newUser);

        when(userRepository.deleteByCpf(newUser.getCpf())).thenReturn(optUser);
        userService.deleteUser(newUser.getCpf());
        verify(userRepository, times(1)).deleteByCpf(newUser.getCpf());

        when(userRepository.deleteByCpf(newUser.getCpf())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, ()->userService.deleteUser("123456123"));
    }

    @Test
    void testEditUser() {
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(i->i.getArguments()[0]);
        User createdUser = userService.createUser(user1);
        User newObjUserCopyfromUser1 = new User(createdUser.getCpf(), createdUser.getPassword(), createdUser.getFirstName(), createdUser.getLastName());
        user1.setFirstName("George");
        Optional<User> optUser = Optional.of(user1);
        when(userRepository.findByCpf(user1.getCpf())).thenReturn(optUser);
        User editedUser = userService.editUser(user1);
        
        assertNotEquals(newObjUserCopyfromUser1.getFirstName(), editedUser.getFirstName());
        assertEquals("josef", newObjUserCopyfromUser1.getFirstName());
        assertEquals("George", editedUser.getFirstName());
        
        when(userRepository.save(newObjUserCopyfromUser1)).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, ()->userService.editUser(newObjUserCopyfromUser1));
    }

    @Test
    void testGetByCpf() {
        User newUser = new User();
        Optional<User> optUser = Optional.of(newUser);
        when(userRepository.findByCpf(newUser.getCpf())).thenReturn(optUser);
        User returnedUser = userService.getByCpf(newUser.getCpf());
        assertEquals(newUser, returnedUser);

        when(userRepository.findByCpf(user1.getCpf())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, ()->userService.getByCpf(user1.getCpf()));
    }
    
    @Test
    void testGetById() {
        User newUser = new User();
        Optional<User> optUser = Optional.of(newUser);
        when(userRepository.findById(newUser.getId())).thenReturn(optUser);
        User returnedUser = userService.getById(newUser.getId());
        assertEquals(newUser, returnedUser);

        when(userRepository.findById(user1.getId())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, ()->userService.getById(user1.getId()));
    }

    @Test
    void testListUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        List<User> users = userService.listUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
    }
}