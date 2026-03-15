package com.example.powerseva1.controller;

import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.entity.Technician;
import com.example.powerseva1.repository.ComplaintRepository;
import com.example.powerseva1.repository.TechnicianRepository;
import com.example.powerseva1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;
    private final TechnicianRepository technicianRepository;

    // ✅ Dashboard
    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {

        Map<String, Object> data = new HashMap<>();

        data.put("totalUsers", userRepository.count());
        data.put("totalTechnicians", technicianRepository.count());
        data.put("totalComplaints", complaintRepository.count());
        data.put("pendingComplaints", complaintRepository.countByStatus("PENDING"));
        data.put("completedComplaints", complaintRepository.countByStatus("COMPLETED"));

        return data;
    }

    // ✅ Get all complaints
    @GetMapping("/complaints")
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
    @PutMapping("/assign")
    public Complaint assignTechnician(@RequestParam Long complaintId,
                                      @RequestParam Long technicianId) {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        complaint.setTechnician(technician);
        complaint.setStatus("IN_PROGRESS");

        return complaintRepository.save(complaint);
    }

}