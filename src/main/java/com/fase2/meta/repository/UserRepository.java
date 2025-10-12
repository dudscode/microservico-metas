package com.fase2.meta.repository;

import com.fase2.meta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
