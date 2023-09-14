package com.example.demo.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private UserService userService;

    
    @Test
    void testCreateNewUser() throws Exception {
        /* this.mockMvc
            .perform(MockMvcRequestBuilders.get("/customer/register", "123456789", "fulano","de tal","123456","99999999","jorge@gmail.com"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("userRegister"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("cpf", "firstName", "lastName","password","cel","email")); */
    }

    @Test
    void testEditingCustomer() {

    }

    @Test
    void testEditingCustomer2() {

    }

    @Test
    void testGetCustomer() {

    }

    @Test
    void testListCustomers() {

    }

    @Test
    void testRegister() {

    }

    @Test
    void testRmvCustomer() {

    }
}
