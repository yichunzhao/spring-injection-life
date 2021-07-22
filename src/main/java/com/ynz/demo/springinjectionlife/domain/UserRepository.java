package com.ynz.demo.springinjectionlife.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface UserRepository extends CrudRepository<SecurityUser, Long> {
    @Query("SELECT su FROM SecurityUser su JOIN FETCH su.authorities WHERE su.userName = :userName")
    Optional<SecurityUser> findByUserName(@Param("userName") String userName);

}
