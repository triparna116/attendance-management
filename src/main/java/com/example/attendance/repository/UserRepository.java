package com.example.attendance.repository;

import java.util.Optional;  // <-- Corrected line
import org.springframework.data.repository.CrudRepository;
import com.example.attendance.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}