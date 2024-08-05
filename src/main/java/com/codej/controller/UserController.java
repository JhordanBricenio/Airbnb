package com.codej.controller;


import com.codej.model.Property;
import com.codej.model.User;
import com.codej.service.IPropertyService;
import com.codej.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private  final IPropertyService propertyService;
    private final IUserService userService;


    @GetMapping("/{userId}/properties")
    ResponseEntity<List<Property>> findAllPropertiesForUser(@PathVariable Long userId){
        User user = userService.finUserById(userId);
        return  new ResponseEntity<>(propertyService.findPropertiesByUser(user.getId()), HttpStatus.OK);
    }
}
