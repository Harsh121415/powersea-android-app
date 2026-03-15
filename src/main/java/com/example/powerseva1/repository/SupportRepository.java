package com.example.powerseva1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.powerseva1.entity.Support;

import java.util.List;

public interface SupportRepository extends JpaRepository<Support, Long> {

    List<Support> findByComplaintId(Long complaintId);

    boolean existsByUserIdAndComplaintId(Long userId, Long complaintId);

}