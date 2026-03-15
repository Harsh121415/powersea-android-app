package com.example.powerseva1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "supports")
@Data
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;
}