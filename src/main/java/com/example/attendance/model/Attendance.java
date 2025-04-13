package com.example.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private boolean present;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Attendance() {}

    public Attendance(LocalDate date, boolean present, User user) {
        this.date = date;
        this.present = present;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public boolean isPresent() { return present; }

    public void setPresent(boolean present) { this.present = present; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
