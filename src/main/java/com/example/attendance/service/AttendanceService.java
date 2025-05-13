package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.Student;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Mark attendance with current date
    public void markAttendance(Student student, boolean present, String subject) {
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setPresent(present);
        attendance.setDate(LocalDate.now()); // Set current date
        attendance.setSubject(subject); // Ensure Attendance model has subject field
        attendanceRepository.save(attendance);
    }

    // Optional: If you get date as String
    public void markAttendanceWithCustomDate(Student student, boolean present, String subject, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter); // Convert String to LocalDate
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setPresent(present);
        attendance.setDate(date);
        attendance.setSubject(subject);
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }
}

