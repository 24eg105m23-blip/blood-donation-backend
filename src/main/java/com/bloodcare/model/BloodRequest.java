package com.bloodcare.model;
import jakarta.persistence.*;
import java.time.*;
@Entity @Table(name="blood_requests", indexes=@Index(columnList="status,created_at"))
public class BloodRequest {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @ManyToOne(optional=false) @JoinColumn(name="requester_id", nullable=false) public User requester;
    @Column(name="patient_name", nullable=false) public String patientName;
    @Enumerated(EnumType.STRING) @Column(name="blood_group", nullable=false) public Models.BloodGroup bloodGroup;
    @Column(name="units_required", nullable=false) public int unitsRequired;
    @Column(name="hospital_name", nullable=false) public String hospitalName;
    @Column(name="hospital_location", nullable=false) public String hospitalLocation;
    @Column(name="contact_number", nullable=false) public String contactNumber;
    @Enumerated(EnumType.STRING) @Column(nullable=false) public Models.Urgency urgency;
    @Column(name="required_date", nullable=false) public LocalDate requiredDate;
    @Enumerated(EnumType.STRING) @Column(nullable=false) public Models.RequestStatus status=Models.RequestStatus.PENDING;
    @Column(length=2000) public String notes;
    @Column(name="created_at", nullable=false) public LocalDateTime createdAt=LocalDateTime.now();
    @Column(name="updated_at", nullable=false) public LocalDateTime updatedAt=LocalDateTime.now();
}
