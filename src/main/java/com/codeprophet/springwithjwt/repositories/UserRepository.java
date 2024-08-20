package com.codeprophet.springwithjwt.repositories;

import com.codeprophet.springwithjwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login); // Changed return type to match your entity
}
