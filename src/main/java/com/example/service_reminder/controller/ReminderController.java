package com.example.service_reminder.controller;

import com.example.service_reminder.DTO.ReminderDTO;
import com.example.service_reminder.service.ReminderActionService;
import com.example.service_reminder.service.ReminderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    @Autowired
    private ReminderServiceImpl service;

    @Autowired
    private ReminderActionService reminderActionService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReminderDTO dto) {
        try {
            return ResponseEntity.ok(service.createReminder(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getReminderById(id));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<ReminderDTO>> getByUser(@PathVariable UUID idUser) {
        return ResponseEntity.ok(service.getRemindersByUser(idUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> update(@PathVariable UUID id, @RequestBody ReminderDTO dto) {
        return ResponseEntity.ok(service.updateReminder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }
}
