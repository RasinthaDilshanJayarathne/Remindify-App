/**
 * Author : rasintha_j
 * Date : 6/28/2024
 * Time : 3:10 PM
 * Project Name : remindifyapp
 */

package com.remindifyapp.controller;

import com.remindifyapp.bean.ReminderDTO;
import com.remindifyapp.bean.ResponseDTO;
import com.remindifyapp.entity.AuthUser;
import com.remindifyapp.entity.Reminder;
import com.remindifyapp.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ManageReminderController {

    private final ReminderRepository reminderRepository;

    @Autowired
    public ManageReminderController(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @PostMapping("/addReminder")
    public ResponseDTO addReminder(@RequestBody ReminderDTO reminderDTO) {
        ResponseDTO responseDTO = new ResponseDTO(); // Initialize responseDTO here

        try {
            Reminder reminder = new Reminder();
            reminder.setUsername(reminderDTO.getUsername());
            reminder.setMessage(reminderDTO.getMessage());

            // Save reminder to database
            reminderRepository.save(reminder);

            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Reminder added successfully");
        } catch (Exception e) {
            responseDTO.setStatusCode(400);
            responseDTO.setMessage("Failed to add reminder: " + e.getMessage());
        }

        return responseDTO;
    }

    @GetMapping("/getReminderByUsername")
    public ResponseDTO getReminderByUsername(@RequestParam String username) {
        ResponseDTO responseDTO = new ResponseDTO();

        List<Reminder> reminders = reminderRepository.findByUsername(username);
        if (!reminders.isEmpty()) {
            responseDTO.setStatusCode(200);
            responseDTO.setMessage("Successfully");
            responseDTO.setData(reminders);
        } else {
            responseDTO.setStatusCode(404);
            responseDTO.setMessage("User not found");
            responseDTO.setData(null);
        }
        return responseDTO;
    }

}
