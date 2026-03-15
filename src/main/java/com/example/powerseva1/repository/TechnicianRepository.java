package com.example.powerseva1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.powerseva1.entity.Technician;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    List<Technician> findByAreaId(Long areaId);

}