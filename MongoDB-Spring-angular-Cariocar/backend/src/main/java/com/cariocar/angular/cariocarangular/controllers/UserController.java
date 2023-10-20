package com.cariocar.angular.cariocarangular.controllers;

import com.cariocar.angular.cariocarangular.models.User;
import com.cariocar.angular.cariocarangular.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cariocar")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> showUsers(){
        return userService.listUser();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User showUser(@PathVariable Long id){return userService.getById(id);}

    @GetMapping(value = "/find/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> showUsers(@PathVariable String name){
        return userService.listUsers(name);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable Long id){userService.deleteUser(id);}

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@RequestParam String cpf){userService.deleteUser(cpf);}

    @PutMapping(value = "/edit/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User editUser(@PathVariable Long id, @RequestBody User user){return userService.editUser(id, user);}
}
