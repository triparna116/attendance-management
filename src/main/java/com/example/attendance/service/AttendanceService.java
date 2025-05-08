package com.example.attendance.service;

import com.example.attendance.model.Attendance;
import com.example.attendance.model.Student;
import com.example.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void markAttendance(Student student, boolean present, String subject) {
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setPresent(present);
        attendance.setDate(LocalDate.now().toString());
      
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }
}