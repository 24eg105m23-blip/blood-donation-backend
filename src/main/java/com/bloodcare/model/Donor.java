package com.bloodcare.model;
import jakarta.persistence.*;
import java.time.*;
@Entity @Table(name="donors", indexes={@Index(columnList="blood_group,available"),@Index(columnList="city,state")})
public class Donor {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @OneToOne(optional=false) @JoinColumn(name="user_id", nullable=false, unique=true) public User user;
    @Column(nullable=false) public int age;
    @Column(nullable=false) public String gender;
    @Enumerated(EnumType.STRING) @Column(name="blood_group", nullable=false) public Models.BloodGroup bloodGroup;
    @Column(nullable=false) public String city;
    @Column(nullable=false) public String state;
    @Column(nullable=false) public String address;
    @Column(nullable=false) public boolean available=true;
    public LocalDate lastDonationDate;
    @Column(name="created_at", nullable=false) public LocalDateTime createdAt=LocalDateTime.now();
    @Column(name="updated_at", nullable=false) public LocalDateTime updatedAt=LocalDateTime.now();
}
