package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Override
    @Transactional
    Menu save(Menu menu);

    @Transactional
    @Modifying
    @Query(name = Menu.DELETE)
    int delete(@Param("id") int id);
}
