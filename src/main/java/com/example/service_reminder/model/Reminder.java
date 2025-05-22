package com.example.service_reminder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReminder;

    private UUID idUser;
    private LocalDateTime dateCreated;
    private String nameReminder;
    private String quantReminder;
    private int dosageReminder;
    private String dosageUnitReminder;

    @ElementCollection
    private List<String> weekDayReminder;

    private LocalTime hourReminder;
}