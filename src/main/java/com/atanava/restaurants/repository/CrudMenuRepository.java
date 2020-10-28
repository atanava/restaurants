package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
}
