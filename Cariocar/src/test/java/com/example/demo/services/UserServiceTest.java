package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private static UserService userService;
    
    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;

    @BeforeAll
    public static void setUp(){
        user1 = new User("123456654", "123456", "josef", "anything");
        user2 = new User("123321456", "123465", "Antony", "anyOtherThing");
        user3 = userService.createUser(new User("123456123", "123456", "joca", "moreAnotherThig"));
        user4 = userService.createUser(new User("123456456", "123456", "john", "AgainAnything"));
    }

    @Test
    public void testCreateUser() {
        User savedUser = userService.createUser(user1);
        User saveUser2 = userService.createUser(user2);
        assertEquals(user1, savedUser);
        assertEquals(user2, saveUser2);
        assertNotEquals("antony", "antony");
        assertNotEquals(savedUser, saveUser2);
        assertNotNull(user3);
        assertNotNull(user4);
        assertThrows(UserAlreadyExistException.class, () -> userService.createUser(user2));
    }

    @Test
    void testDeleteUser() {
        assertEquals(user3, userService.deleteUser("123456123"));
        assertThrows(UserNotFoundException.class, ()->userService.deleteUser("123456123"));
    }

    @Test
    void testEditUser() {
        user3.setFirstName("Lucas");
        userService.editUser(user3);
        assertEquals("Lucasss", userService.getByCpf("123456123"));
    }

    @Test
    void testGetByCpf() {

    }

    @Test
    void testGetById() {

    }

    @Test
    void testListUsers() {

    }
}