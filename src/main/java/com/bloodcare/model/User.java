package com.bloodcare.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="users", indexes=@Index(columnList="email", unique=true))
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @Column(name="full_name", nullable=false) public String fullName;
    @Column(nullable=false, unique=true) public String email;
    @Column(nullable=false) public String phone;
    @Column(name="password_hash", nullable=false) public String passwordHash;
    @Enumerated(EnumType.STRING) @Column(nullable=false) public Models.Role role;
    @Column(nullable=false) public boolean enabled=true;
    @Column(name="created_at", nullable=false) public LocalDateTime createdAt=LocalDateTime.now();
    @Column(name="updated_at", nullable=false) public LocalDateTime updatedAt=LocalDateTime.now();
}
