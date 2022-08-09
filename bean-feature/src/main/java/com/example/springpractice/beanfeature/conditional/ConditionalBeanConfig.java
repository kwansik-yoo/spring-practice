package com.example.springpractice.beanfeature.conditional;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;
import java.util.function.Supplier;

@Getter
@Setter
@Slf4j
@Configuration
@ConfigurationProperties("foo")
public class ConditionalBeanConfig {
    private boolean active;

    static class FooCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            String fooCondition = context.getEnvironment().getProperty("foo.active");
            return Optional.ofNullable(fooCondition)
                    .map(c -> c.equals(Boolean.toString(true)))
                    .orElse(false);
        }
    }


    @Bean
    @Conditional(FooCondition.class)
    public Supplier<String> fooByConditional() {
        log.debug("fooByConditional is registered....");
        return () -> "fooByConditional";
    }

    @Bean
    @ConditionalOnProperty(prefix = "foo", name = "active", havingValue = "true", matchIfMissing = false)
    public Supplier<String> fooByConditionalProperty() {
        log.debug("fooByConditionalProperty is registered....");
        return () -> "fooByConditionalProperty";
    }
}
