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

    Optional<Reminder> findByIdReminder(UUID idReminder); // Buscar lembrete por ID

    List<Reminder> findByIdUser(UUID idUser); // Buscar lembretes de um usuário específico

    void deleteByIdReminder(UUID idReminder); // Excluir lembrete por ID

//    List<Reminder> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate); // (opcional) para filtro por data
//    Page<Reminder> findAll(Pageable pageable); // Paginação (opcional)
}
