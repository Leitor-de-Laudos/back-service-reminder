package com.example.service_reminder.service;

import com.example.service_reminder.DTO.ReminderDTO;
import java.util.List;
import java.util.UUID;

public interface ReminderService {

    ReminderDTO createReminder(ReminderDTO reminderDTO);

    ReminderDTO getReminderById(UUID idReminder);

    List<ReminderDTO> getRemindersByUser(UUID idUser);

    ReminderDTO updateReminder(UUID idReminder, ReminderDTO reminderDTO);

    void deleteReminder(UUID idReminder);
}