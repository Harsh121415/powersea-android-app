package com.example.powerseva1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.entity.Technician;
import com.example.powerseva1.repository.ComplaintRepository;
import com.example.powerseva1.repository.TechnicianRepository;
import com.example.powerseva1.service.ComplaintService;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final TechnicianRepository technicianRepository;

    @Override
    public Complaint createComplaint(Complaint complaint) {
        complaint.setStatus("PENDING");
        complaint.setCreatedAt(LocalDateTime.now());
        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> getByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }

    @Override
    public List<Complaint> getByArea(Long areaId) {
        return complaintRepository.findByAreaId(areaId);
    }

    @Override
    public Complaint assignTechnician(Long complaintId, Long technicianId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        complaint.setTechnician(technician);
        complaint.setStatus("ASSIGNED");

        return complaintRepository.save(complaint);
    }

    @Override
    public Complaint updateStatus(Long complaintId, String status) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }
}