package com.example.springpractice.taskdecorator.config;

import com.example.springpractice.taskdecorator.audit.AuditContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Optional;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
public class ThreadExecutorConfig {
    public static final String THREAD_NAME_PREFIX = "Custom-Executor";

    @Profile("!with-decorator")
    @Bean
    public Executor taskExecutor() {
        log.info("task Executor without decorator set...");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();
        return executor;
    }

    @Profile("with-decorator")
    @Bean
    public Executor taskExecutorWithDecorator(TaskDecorator taskDecorator) {
        log.info("task Executor with decorator set...");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setTaskDecorator(taskDecorator);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();
        return executor;
    }

    @Profile("with-decorator")
    @Bean
    public TaskDecorator taskDecorator() {
        return runnable -> {
            String auditId = AuditContextHolder.auditId();
            return () -> {
                Optional.ofNullable(auditId)
                        .ifPresent(audit -> AuditContextHolder.auditId(auditId));
                runnable.run();

                AuditContextHolder.clear();
            };
        };
    }
}
