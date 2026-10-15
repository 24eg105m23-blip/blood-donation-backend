package com.bloodcare.repository;
import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface RequestRepo extends JpaRepository<BloodRequest,Long> { List<BloodRequest> findByRequesterIdOrderByCreatedAtDesc(Long id); long countByStatus(RequestStatus status); @Query("select r.status,count(r) from BloodRequest r group by r.status") List<Object[]> countByStatus(); }
