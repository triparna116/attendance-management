package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.User;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void markAttendance(User user, boolean present) {
        Attendance attendance = new Attendance();
        attendance.setDate(LocalDate.now());
        attendance.setPresent(present);
        attendance.setUser(user);
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForUser(User user) {
        return attendanceRepository.findByUser(user);
    }
}
