package com.example.powerseva1.controller;

import com.example.powerseva1.repository.ComplaintRepository;
import com.example.powerseva1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.service.ComplaintService;
import com.example.powerseva1.security.JwtUtil;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintRepository complaintRepository;

    // ✅ Create Complaint
    @PostMapping("/create")
    public Complaint createComplaint(@RequestBody Complaint complaint) {

        complaint.setStatus("PENDING");

        return complaintRepository.save(complaint);
    }

    // ✅ User Complaints
    @GetMapping("/user/{userId}")
    public List<Complaint> getUserComplaints(@PathVariable Long userId) {
        return complaintRepository.findByUserId(userId);
    }

    // ✅ Technician Complaints
    @GetMapping("/technician/{techId}")
    public List<Complaint> getTechnicianComplaints(@PathVariable Long techId) {
        return complaintRepository.findByTechnicianId(techId);
    }

    // ✅ Update Status
    @PutMapping("/update-status/{complaintId}")
    public Complaint updateStatus(@PathVariable Long complaintId,
                                  @RequestParam String status) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }
}