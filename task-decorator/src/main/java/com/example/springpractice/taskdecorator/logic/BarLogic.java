package com.example.springpractice.taskdecorator.logic;

import com.example.springpractice.taskdecorator.audit.AuditContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BarLogic {
    @Async
    public void someAsyncLogicWithAudit() {
        String auditId = AuditContextHolder.auditId();
        Thread currentThread = Thread.currentThread();
        log.info("[someAsyncLogicWithAudit] thread : {}, auditId : {}", currentThread.getName(), auditId);
    }
}
