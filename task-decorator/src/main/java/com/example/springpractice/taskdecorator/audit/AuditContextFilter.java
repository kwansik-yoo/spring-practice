package com.example.springpractice.taskdecorator.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuditContextFilter extends OncePerRequestFilter {
    private final String AUDIT_HEADER_KEY = "requester-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requesterId = request.getHeader(AUDIT_HEADER_KEY);
        log.debug("requester Id : {}", requesterId);

        Optional.ofNullable(requesterId)
                .ifPresent(rId -> AuditContextHolder.auditId(requesterId));

        filterChain.doFilter(request, response);

        AuditContextHolder.clear();
    }
}
