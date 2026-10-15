package com.bloodcare.repository;
import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepo extends JpaRepository<User,Long> { Optional<User> findByEmailIgnoreCase(String email); long countByRole(Role role); }
