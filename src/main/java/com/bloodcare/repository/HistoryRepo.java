package com.bloodcare.repository;
import com.bloodcare.model.*;
import com.bloodcare.model.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HistoryRepo extends JpaRepository<StatusHistory,Long> {}
