package com.codej.repository;

import com.codej.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwnerId(Long userId);
}
