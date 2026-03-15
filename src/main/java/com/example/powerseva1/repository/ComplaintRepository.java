package com.example.powerseva1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.powerseva1.entity.Complaint;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // 🔍 Get complaints by area
    List<Complaint> findByAreaId(Long areaId);

    // 🔍 Get complaints by status
    List<Complaint> findByStatus(String status);

    // 🔍 Get complaints by user
    List<Complaint> findByUserId(Long userId);

    // 🔍 Get complaints by technician
    List<Complaint> findByTechnicianId(Long technicianId);

    // 📊 Count by status (for admin dashboard)
    long countByStatus(String status);

    // 🔥 Sort by support count (high priority first)
    List<Complaint> findAllByOrderBySupportCountDesc();
}