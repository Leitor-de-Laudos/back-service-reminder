package com.example.service_reminder.AOP;

import com.example.service_reminder.DTO.ReminderDTO;
import com.example.service_reminder.model.Reminder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class ReminderLoggingAspect {


    // Trigger do email
    @Before("execution(* com.example.service_reminder.service.ReminderActionService.triggerReminder(..))")
    public void logBeforeReminderTriggered(JoinPoint joinPoint) {
        Reminder reminder = (Reminder) joinPoint.getArgs()[0];
        System.out.println("➡️  [LOG] Lembrete acionado: " + reminder.getNameReminder());
    }

    @AfterReturning(
            pointcut = "execution(* com.example.service_reminder.service.ReminderActionService.triggerReminder(..))",
            returning = "result"
    )
    public void logAfterReminderTriggered(JoinPoint joinPoint, Object result) {
        Reminder reminder = (Reminder) joinPoint.getArgs()[0];
        System.out.println("✅ [LOG] E-mail enviado com sucesso para: " + reminder.getEmail() +
                " | Mensagem: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.service_reminder.service.ReminderActionService.triggerReminder(..))",
            throwing = "ex"
    )
    public void logErrorOnTrigger(JoinPoint joinPoint, Throwable ex) {
        Reminder reminder = (Reminder) joinPoint.getArgs()[0];
        System.err.println("❌ [ERRO] Falha ao acionar lembrete: " + reminder.getNameReminder());
        ex.printStackTrace();
    }


    // Criação de lembretes
    @Before("execution(* com.example.service_reminder.service.ReminderServiceImpl.createReminder(..))")
    public void logBeforeCreateReminder(JoinPoint joinPoint) {
        ReminderDTO dto = (ReminderDTO) joinPoint.getArgs()[0];
        System.out.println("📝 [LOG] Criando lembrete: " + dto.getNameReminder());
    }

    @AfterReturning(pointcut = "execution(* com.example.service_reminder.service.ReminderServiceImpl.createReminder(..))", returning = "result")
    public void logAfterCreateReminder(JoinPoint joinPoint, Object result) {
        ReminderDTO dto = (ReminderDTO) result;
        System.out.println("✅ [LOG] Lembrete criado com sucesso: " + dto.getIdReminder());
    }

    @AfterThrowing(pointcut = "execution(* com.example.service_reminder.service.ReminderServiceImpl.createReminder(..))", throwing = "ex")
    public void logCreateReminderError(JoinPoint joinPoint, Throwable ex) {
        System.err.println("❌ [ERRO] Falha ao criar lembrete: " + ex.getMessage());
    }


    // Exclusão de lembretes
    @Before("execution(* com.example.service_reminder.service.ReminderServiceImpl.deleteReminder(..))")
    public void logBeforeDeleteReminder(JoinPoint joinPoint) {
        UUID idReminder = (UUID) joinPoint.getArgs()[0];
        System.out.println("📝 [LOG] Removendo lembrete com ID: " + idReminder);
    }

    @AfterReturning("execution(* com.example.service_reminder.service.ReminderServiceImpl.deleteReminder(..))")
    public void logAfterDeleteReminder(JoinPoint joinPoint) {
        UUID idReminder = (UUID) joinPoint.getArgs()[0];
        System.out.println("✅ [LOG] Lembrete removido com sucesso. ID: " + idReminder);
    }

    @AfterThrowing(pointcut = "execution(* com.example.service_reminder.service.ReminderServiceImpl.deleteReminder(..))", throwing = "ex")
    public void logDeleteReminderError(JoinPoint joinPoint, Throwable ex) {
        UUID idReminder = (UUID) joinPoint.getArgs()[0];
        System.err.println("❌ [ERRO] Falha ao remover lembrete com ID: " + idReminder + ". Erro: " + ex.getMessage());
    }
}
