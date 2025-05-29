package com.example.service_reminder.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
public class ReminderDTO {
    private UUID idReminder;
    private UUID idUser;
    private String email;
    private LocalDateTime dateCreated;
    private String nameReminder;
    private String quantReminder;
    private int dosageReminder;
    private String dosageUnitReminder;
    private List<String> weekDayReminder;
    private LocalTime hourReminder;
}
