package com.example.springpractice.taskdecorator.audit;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditContextHolder {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String auditId() {
        return threadLocal.get();
    }

    public static void auditId(String auditId) {
        log.info("Audit is set in thread : {}", Thread.currentThread().getName());
        threadLocal.set(auditId);
    }

    public static void clear() {
        log.info("Audit is cleared in thread : {}", Thread.currentThread().getName());
        threadLocal.remove();
    }
}
