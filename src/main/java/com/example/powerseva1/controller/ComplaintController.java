package com.example.powerseva1.controller;

import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintRepository complaintRepository;

    // ✅ Create Complaint
    @PostMapping("/create")
    public Complaint createComplaint(@RequestBody Complaint complaint) {

        complaint.setStatus("PENDING");
        complaint.setCreatedAt(LocalDateTime.now());

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