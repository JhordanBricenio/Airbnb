package com.codej.controller;

import com.codej.model.Property;
import com.codej.model.User;
import com.codej.service.IPropertyService;
import com.codej.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@AllArgsConstructor
public class PropertyController {

    private  final IPropertyService propertyService;
    private final IUserService userService;

    @PostMapping
    ResponseEntity<Property> save( @RequestHeader("Authorization") String jwt,@RequestBody Property property){
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(propertyService.save(property, user), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Property>> findAll(){
        return new ResponseEntity<>(propertyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Property> findById(@PathVariable Long id){
        return  new ResponseEntity<>(propertyService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Property> update( @RequestHeader("Authorization") String jwt
            ,@RequestBody Property property, @PathVariable Long id){
        User user = userService.findUserProfileByJwt(jwt);
        return  new ResponseEntity<>(propertyService.update(property, id, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id){
        propertyService.delete(id);
        return "Property deleted";
    }



}
