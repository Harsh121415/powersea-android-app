package com.example.powerseva1.service;

import java.util.List;
import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.entity.Technician;

public interface AdminService {

    long getTotalComplaints();

    long getPendingCount();

    long getAssignedCount();

    long getResolvedCount();

    List<Complaint> getTopPriorityComplaints();

    List<Technician> getAllTechnicians();
}