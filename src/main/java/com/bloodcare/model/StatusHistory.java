package com.bloodcare.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="request_status_history")
public class StatusHistory {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
    @ManyToOne(optional=false) @JoinColumn(name="blood_request_id", nullable=false) public BloodRequest bloodRequest;
    @Enumerated(EnumType.STRING) @Column(name="previous_status", nullable=false) public Models.RequestStatus previousStatus;
    @Enumerated(EnumType.STRING) @Column(name="new_status", nullable=false) public Models.RequestStatus newStatus;
    @ManyToOne(optional=false) @JoinColumn(name="changed_by", nullable=false) public User changedBy;
    @Column(name="changed_at", nullable=false) public LocalDateTime changedAt=LocalDateTime.now();
    public String remarks;
}
