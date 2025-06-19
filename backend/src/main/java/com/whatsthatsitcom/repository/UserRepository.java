package com.whatsthatsitcom.repository;

import com.whatsthatsitcom.model.User;
import org.springframework.data.jpa.repository.JpaRepository; //This import is **critical**. `JpaRepository` gives you dozens of ready-made methods that you can use to query the database.
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // In Java, when you fetch something from a database that might not exist, you wrap the result in Optional<T> to avoid null pointer errors.
}

