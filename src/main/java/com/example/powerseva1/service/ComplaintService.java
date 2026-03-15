package com.example.powerseva1.service;

import com.example.powerseva1.entity.Complaint;
import java.util.List;

public interface ComplaintService {

    Complaint createComplaint(Complaint complaint);

    List<Complaint> getAllComplaints();

    List<Complaint> getByStatus(String status);

    List<Complaint> getByArea(Long areaId);

    Complaint assignTechnician(Long complaintId, Long technicianId);

    Complaint updateStatus(Long complaintId, String status);
}