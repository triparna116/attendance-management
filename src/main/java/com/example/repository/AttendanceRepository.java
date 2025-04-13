package com.example.repository;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByUser(User user);
}