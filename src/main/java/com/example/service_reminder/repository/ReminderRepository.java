package com.example.service_reminder.repository;

import com.example.service_reminder.model.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    List<Reminder> findByTitleReminderContainingIgnoreCase(String titleReminder);

    List<Reminder> findByTargetReminderContainingIgnoreCase(String targetReminder);

    List<Reminder> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Reminder> findByIdUser(UUID idUser);

    List<Reminder> findByTitleReminderContainingIgnoreCaseAndDescriptionReminderContainingIgnoreCase(
            String titleReminder, String descriptionReminder);

    Optional<Reminder> findByIdReminder(UUID idReminder);

    Page<Reminder> findAll(Pageable pageable);

    void deleteByIdReminder(UUID idReminder);
}
