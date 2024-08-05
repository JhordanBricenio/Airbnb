package com.codej.service;

import com.codej.model.Property;
import com.codej.model.User;

import java.util.List;

public interface IPropertyService {

    Property save(Property property, User user);
    List<Property> getAll();
    Property getById(Long id);
    Property update (Property property, Long id, User user);
    public List<Property> findPropertiesByUser(Long userId);
    void delete(Long id);
}
