package com.example.service_reminder.service;


import com.example.service_reminder.DTO.ReminderDTO;
import com.example.service_reminder.factory.ReminderFactory;
import com.example.service_reminder.model.Reminder;
import com.example.service_reminder.repository.ReminderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private final ReminderRepository repository;
    private final ReminderFactory reminderFactory;

    @Override
    public ReminderDTO createReminder(ReminderDTO dto) {
        try {
            // Validação de campos obrigatórios
            if (dto.getNameReminder() == null || dto.getNameReminder().isBlank()) {
                throw new IllegalArgumentException("O nome do lembrete é obrigatório.");
            }

            if (dto.getQuantReminder() == null || dto.getQuantReminder().isBlank()) {
                throw new IllegalArgumentException("A quantidade é obrigatória.");
            }

            if (dto.getEmail() == null || dto.getEmail().isBlank()) {
                throw new IllegalArgumentException("Email é obrigatório!");
            }

            if (dto.getDosageReminder() == 0) {
                throw new IllegalArgumentException("A dosagem é obrigatória.");
            }

            if (dto.getDosageUnitReminder() == null || dto.getDosageUnitReminder().isBlank()) {
                throw new IllegalArgumentException("A unidade de dosagem é obrigatória.");
            }

            if (dto.getWeekDayReminder() == null || dto.getWeekDayReminder().isEmpty()) {
                throw new IllegalArgumentException("Os dias da semana são obrigatórios.");
            }

            if (dto.getHourReminder() == null) {
                throw new IllegalArgumentException("O horário é obrigatório.");
            }

            if (dto.getIdUser() == null || Objects.equals(dto.getIdUser().toString(), "")) {
                throw new IllegalArgumentException("O ID do usuário é obrigatório.");
            }

            // ✅ Usando o padrão Factory aqui
            Reminder reminder = reminderFactory.createReminder(dto);

            return toDTO(repository.save(reminder));

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar lembrete: " + e.getMessage(), e);
        }
    }


    @Override
    public ReminderDTO getReminderById(UUID idReminder) {
        Reminder reminder = repository.findByIdReminder(idReminder)
                .orElseThrow(() -> new EntityNotFoundException("Lembrete não encontrado"));
        return toDTO(reminder);
    }

    @Override
    public List<ReminderDTO> getRemindersByUser(UUID idUser) {
        return repository.findByIdUser(idUser)
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReminderDTO updateReminder(UUID idReminder, ReminderDTO dto) {
        Reminder reminder = repository.findByIdReminder(idReminder)
                .orElseThrow(() -> new EntityNotFoundException("Lembrete não encontrado"));

        reminder.setNameReminder(dto.getNameReminder());
        reminder.setQuantReminder(dto.getQuantReminder());
        reminder.setDosageReminder(dto.getDosageReminder());
        reminder.setDosageUnitReminder(dto.getDosageUnitReminder());
        reminder.setWeekDayReminder(dto.getWeekDayReminder());
        reminder.setHourReminder(dto.getHourReminder());

        return toDTO(repository.save(reminder));
    }

    @Override
    public void deleteReminder(UUID idReminder) {
        repository.deleteByIdReminder(idReminder);
    }

    private ReminderDTO toDTO(Reminder entity) {
        ReminderDTO dto = new ReminderDTO();
        dto.setIdReminder(entity.getIdReminder());
        dto.setIdUser(entity.getIdUser());
        dto.setEmail(entity.getEmail());
        dto.setDateCreated(entity.getDateCreated());
        dto.setNameReminder(entity.getNameReminder());
        dto.setQuantReminder(entity.getQuantReminder());
        dto.setDosageReminder(entity.getDosageReminder());
        dto.setDosageUnitReminder(entity.getDosageUnitReminder());
        dto.setWeekDayReminder(entity.getWeekDayReminder());
        dto.setHourReminder(entity.getHourReminder());
        return dto;
    }

    public Reminder toEntity(ReminderDTO dto) {
        Reminder entity = new Reminder();
        entity.setIdReminder(dto.getIdReminder());
        entity.setIdUser(dto.getIdUser());
        entity.setEmail(dto.getEmail());
        entity.setDateCreated(dto.getDateCreated());
        entity.setNameReminder(dto.getNameReminder());
        entity.setQuantReminder(dto.getQuantReminder());
        entity.setDosageReminder(dto.getDosageReminder());
        entity.setDosageUnitReminder(dto.getDosageUnitReminder());
        entity.setWeekDayReminder(dto.getWeekDayReminder());
        entity.setHourReminder(dto.getHourReminder());
        return entity;
    }
}