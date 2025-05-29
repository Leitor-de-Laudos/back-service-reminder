package com.example.service_reminder.service;

import com.example.service_reminder.model.Reminder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderActionService {

    private final EmailService emailService;

    public void triggerReminder(Reminder reminder) {
        String emailUsuario = reminder.getEmail();

        String subject = "⏰ Lembrete: " + reminder.getNameReminder();
        String content = String.format("É hora de tomar %sx %d %s de %s.",
                reminder.getQuantReminder(),
                reminder.getDosageReminder(),
                reminder.getDosageUnitReminder(),
                reminder.getNameReminder());

        emailService.sendEmail(emailUsuario, subject, content);

        String msg = String.format("Lembrete '%s' enviado para %s", reminder.getNameReminder(), emailUsuario);
        System.out.println(msg);
    }
}

