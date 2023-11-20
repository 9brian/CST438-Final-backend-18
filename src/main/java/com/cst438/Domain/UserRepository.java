package com.cst438.Domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Integer>{
//    @Query("select a from User a where a.email = :email order by a.id")
//    List<User> findByEmail(@Param("email") String email);
    User findByEmail(String email);
}
