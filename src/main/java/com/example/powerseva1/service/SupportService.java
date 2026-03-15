package com.example.powerseva1.service;

import com.example.powerseva1.entity.Support;

public interface SupportService {

    Support supportComplaint(Long userId, Long complaintId);

}