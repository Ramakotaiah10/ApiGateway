package com.tibil.apigateway.repository;

import com.tibil.apigateway.entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByUsername(String username);  // check existence
    AppUser findByUsername(String username);    // fetch user by username
}
