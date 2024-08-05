package com.codej.service.impl;

import com.codej.model.Property;
import com.codej.model.User;
import com.codej.repository.IPropertyRepository;
import com.codej.service.IPropertyService;
import com.codej.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements IPropertyService {

    private final IPropertyRepository propertyRepository;
    private final IUserService userService;

    @Override
    public Property save( Property property,User user) {
        property.setOwner(user);
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getById(Long id) {
        return propertyRepository.findById(id).orElseThrow();
    }

    @Override
    public Property update(Property property, Long id, User user) {
        Property property1= getById(id);
        property1.setOwner(user);
        property1.setName(property.getName());
        property1.setDescription(property.getDescription());
        property1.setAddress(property.getAddress());
        property1.setPricePerNight(property.getPricePerNight());
        property1.setNumberOfBedrooms(property.getNumberOfBedrooms());
        property1.setNumberOfBathrooms(property.getNumberOfBathrooms());
        property1.setAvailable(property.isAvailable());
        property1.setDrinkAllowed(property.isDrinkAllowed());
        property1.setPetAllowed(property.isPetAllowed());
        property1.setMaxCheckoutTimeInNights(property.getMaxCheckoutTimeInNights());
        property1.setExtraCharges(property.getExtraCharges());
        return propertyRepository.save(property1);
    }

    @Override
    public List<Property> findPropertiesByUser(Long userId) {
        return propertyRepository.findByOwnerId(userId);
    }

    @Override
    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }
}
