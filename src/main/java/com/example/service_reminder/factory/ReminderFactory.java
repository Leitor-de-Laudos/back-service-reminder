package com.example.service_reminder.factory;

import com.example.service_reminder.DTO.ReminderDTO;
import com.example.service_reminder.model.Reminder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReminderFactory {

    public Reminder createReminder(ReminderDTO dto) {
        Reminder entity = new Reminder();
        entity.setIdReminder(dto.getIdReminder());
        entity.setIdUser(dto.getIdUser());
        entity.setEmail(dto.getEmail());
        entity.setDateCreated(dto.getDateCreated() != null ? dto.getDateCreated() : LocalDateTime.now());
        entity.setNameReminder(dto.getNameReminder());
        entity.setQuantReminder(dto.getQuantReminder());
        entity.setDosageReminder(dto.getDosageReminder());
        entity.setDosageUnitReminder(dto.getDosageUnitReminder());
        entity.setWeekDayReminder(dto.getWeekDayReminder());
        entity.setHourReminder(dto.getHourReminder());
        return entity;
    }
}