package com.bloodcare.model;
import jakarta.persistence.*;
import java.time.*;
@Entity @Table(name="donations")
public class Donation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @ManyToOne(optional=false) @JoinColumn(name="donor_id", nullable=false) public Donor donor;
    @ManyToOne @JoinColumn(name="blood_request_id") public BloodRequest bloodRequest;
    @Column(name="donation_date", nullable=false) public LocalDate donationDate;
    @Column(name="units_donated") public Integer unitsDonated;
    @Column(name="hospital_or_center", nullable=false) public String hospitalOrCenter;
    @Column(nullable=false) public String status="RECORDED";
    @Column(length=2000) public String notes;
    @Column(name="created_at", nullable=false) public LocalDateTime createdAt=LocalDateTime.now();
}
