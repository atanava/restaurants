package com.atanava.restaurants.repository.user;

import com.atanava.restaurants.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

//    @Override
//    @Transactional
//    User save(User user);

    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

//    @Query(name = User.BY_EMAIL)
    User getByEmail(String email);

//    @Query(name = User.ALL_SORTED)
//    List<User> getAll();

//    //    https://stackoverflow.com/a/46013654/548473
//    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
////    @Query(name = User.BY_ID)
//    @Query("SELECT u FROM User u WHERE u.id=?1")
//    User getWithVotes(int id);
//
}
