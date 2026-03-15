package com.example.powerseva1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.powerseva1.entity.Support;
import com.example.powerseva1.entity.User;
import com.example.powerseva1.entity.Complaint;
import com.example.powerseva1.repository.SupportRepository;
import com.example.powerseva1.repository.UserRepository;
import com.example.powerseva1.repository.ComplaintRepository;
import com.example.powerseva1.service.SupportService;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {

    private final SupportRepository supportRepository;
    private final UserRepository userRepository;
    private final ComplaintRepository complaintRepository;

    @Override
    public Support supportComplaint(Long userId, Long complaintId) {

        if (supportRepository.existsByUserIdAndComplaintId(userId, complaintId)) {
            throw new RuntimeException("User already supported this complaint");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setSupportCount(complaint.getSupportCount() + 1);
        complaintRepository.save(complaint);

        Support support = new Support();
        support.setUser(user);
        support.setComplaint(complaint);

        return supportRepository.save(support);
    }
}