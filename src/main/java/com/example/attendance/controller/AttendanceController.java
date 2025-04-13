package com.example.attendance.controller;

import com.example.attendance.model.User;
import com.example.attendance.model.Attendance;
import com.example.attendance.service.AttendanceService;
import com.example.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserRepository userRepository;

    // Method for marking attendance
    @PostMapping("/mark-attendance")
    public String markAttendance(@RequestParam("username") String username,
                                 @RequestParam("present") boolean present) {
        User user = userRepository.findByUsername(username)
                        .orElse(null);  // Check if user exists
        if (user != null) {
            attendanceService.markAttendance(user, present);  // Save attendance for user
        }
        return "redirect:/dashboard";  // Redirect to dashboard after marking attendance
    }

    // Method for viewing attendance history
    @GetMapping("/view-attendance")
    public String viewAttendance(@RequestParam("username") String username, Model model) {
        User user = userRepository.findByUsername(username)
                        .orElse(null);  // Fetch user by username
        if (user != null) {
            // Fetch attendance list for the user and pass it to the model
            List<Attendance> attendanceList = attendanceService.getAttendanceForUser(user);
            model.addAttribute("attendanceList", attendanceList);
            return "attendance-history";  // Return to the attendance history page
        }
        return "redirect:/dashboard";  // Redirect to dashboard if user is not found
    }
}



