package com.example.powerseva1.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String email;
    private String password;
    private String role;
//    private String ProfileImage;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
}