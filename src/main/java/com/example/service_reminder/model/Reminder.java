package com.example.service_reminder.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reminder")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idReminder;

    @ManyToOne
    private UUID idUser;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String targetReminder;
    private String titleReminder;
    private String descriptionReminder;

    public Reminder(UUID idUser, UUID idReminder, LocalDateTime dateCreated, LocalDateTime dateUpdated,
                    String targetReminder, String titleReminder, String descriptionReminder) {
        this.idUser = idUser;
        this.idReminder = idReminder;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.targetReminder = targetReminder;
        this.titleReminder = titleReminder;
        this.descriptionReminder = descriptionReminder;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public UUID getIdReminder() {
        return idReminder;
    }

    public void setIdReminder(UUID idReminder) {
        this.idReminder = idReminder;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getTargetReminder() {
        return targetReminder;
    }

    public void setTargetReminder(String targetReminder) {
        this.targetReminder = targetReminder;
    }

    public String getTitleReminder() {
        return titleReminder;
    }

    public void setTitleReminder(String titleReminder) {
        this.titleReminder = titleReminder;
    }

    public String getDescriptionReminder() {
        return descriptionReminder;
    }

    public void setDescriptionReminder(String descriptionReminder) {
        this.descriptionReminder = descriptionReminder;
    }
}