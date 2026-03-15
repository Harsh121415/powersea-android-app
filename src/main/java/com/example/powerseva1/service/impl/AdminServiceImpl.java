package com.example.powerseva1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.entity.Technician;
import com.example.powerseva1.repository.ComplaintRepository;
import com.example.powerseva1.repository.TechnicianRepository;
import com.example.powerseva1.service.AdminService;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ComplaintRepository complaintRepository;
    private final TechnicianRepository technicianRepository;

    @Override
    public long getTotalComplaints() {
        return complaintRepository.count();
    }

    @Override
    public long getPendingCount() {
        return complaintRepository.countByStatus("PENDING");
    }

    @Override
    public long getAssignedCount() {
        return complaintRepository.countByStatus("ASSIGNED");
    }

    @Override
    public long getResolvedCount() {
        return complaintRepository.countByStatus("RESOLVED");
    }

    @Override
    public List<Complaint> getTopPriorityComplaints() {
        return complaintRepository.findAllByOrderBySupportCountDesc();
    }

    @Override
    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll();
    }
}