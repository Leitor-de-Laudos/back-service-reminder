package com.example.service_reminder.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class ReminderAuditAspect {

    @Before("execution(* com.example.service_reminder.service.ReminderServiceImpl.deleteReminder(..))")
    public void auditBeforeDeleteReminder(JoinPoint joinPoint) {
        UUID id = (UUID) joinPoint.getArgs()[0];
        System.out.println("🗑️ [AUDIT] Remoção solicitada para lembrete: " + id);
    }

    @Before("execution(* com.example.service_reminder.service.ReminderServiceImpl.updateReminder(..))")
    public void auditBeforeUpdateReminder(JoinPoint joinPoint) {
        UUID id = (UUID) joinPoint.getArgs()[0];
        System.out.println("🗑️ [AUDIT] Alteração solicitada para lembrete: " + id);
    }
}