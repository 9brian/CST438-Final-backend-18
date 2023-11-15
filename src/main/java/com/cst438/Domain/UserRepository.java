package com.cst438.Domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer>{
    @Query("select a from Assignment a where a.course.instructor= :email order by a.id")
    List<Assignment> findByEmail(@Param("email") String email);
}
