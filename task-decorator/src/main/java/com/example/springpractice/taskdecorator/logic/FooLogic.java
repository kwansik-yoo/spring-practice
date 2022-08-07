package com.example.springpractice.taskdecorator.logic;

import com.example.springpractice.taskdecorator.audit.AuditContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
@Service
public class FooLogic {
    private final Executor executor;
    private final BarLogic asyncLogic;

    public void fooAction() {
        String auditId = AuditContextHolder.auditId();
        Thread currentThread = Thread.currentThread();
        log.info("[fooAction] thread : {}, auditId : {}", currentThread.getName(), auditId);

        asyncLogic.someAsyncLogicWithAudit();
        someInternalAsyncLogicWithAudit();
    }

    private CompletableFuture<Void> someInternalAsyncLogicWithAudit() {
        return CompletableFuture.supplyAsync(
                () -> {
                    String auditId = AuditContextHolder.auditId();
                    Thread currentThread = Thread.currentThread();
                    log.info("[someInternalAsyncLogicWithAudit] thread : {}, auditId : {}", currentThread.getName(), auditId);
                    return null;
                },
                executor
        );
    }



}
