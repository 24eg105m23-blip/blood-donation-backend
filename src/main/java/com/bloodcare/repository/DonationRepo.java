package com.bloodcare.repository;
import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface DonationRepo extends JpaRepository<Donation,Long> { List<Donation> findByDonorUserIdOrderByDonationDateDesc(Long id); }
