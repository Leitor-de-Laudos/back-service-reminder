package com.example.service_reminder.service;

import com.example.service_reminder.model.Reminder;
import com.example.service_reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderSchedulerService {

    private final ReminderRepository reminderRepository;
    private final ReminderActionService reminderActionService;

    @Scheduled(cron = "0 * * * * *") // Executa todo minuto
    public void checkAndTriggerReminders() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);

        String diaFormatado = traduzirDiaDaSemana(today);

        List<Reminder> reminders = reminderRepository.findAllWithWeekDays();

        reminders.stream()
                .filter(reminder ->
                        reminder.getWeekDayReminder().contains(diaFormatado) &&
                                reminder.getHourReminder().equals(now)
                )
                .forEach(reminderActionService::triggerReminder);
    }


    private String traduzirDiaDaSemana(DayOfWeek day) {
        return switch (day) {
            case MONDAY -> "Seg";
            case TUESDAY -> "Ter";
            case WEDNESDAY -> "Qua";
            case THURSDAY -> "Qui";
            case FRIDAY -> "Sex";
            case SATURDAY -> "Sáb";
            case SUNDAY -> "Dom";
        };
    }
}

