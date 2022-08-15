package com.example.springpractice.data.mongodb.transaction.service;

import com.example.springpractice.data.mongodb.transaction.document.Tx;
import com.example.springpractice.data.mongodb.transaction.repository.TxRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.reactive.TransactionContext;

@Slf4j
@Service
@RequiredArgsConstructor
public class TxAction {
    private final TxRepo txRepo;

    @Transactional
    public void txRequiredSuccess(String jobId) {
        log.debug("txRequiredSuccess... job: {}, tx: {}", jobId, TransactionAspectSupport.currentTransactionStatus());

        success(jobId);
        txRequiredNewSuccess(jobId.concat("-requireNew"));
    }

    @Transactional
    public void txRequiredFail(String jobId, Throwable throwable) throws Throwable {
        log.debug("txRequiredFail... job: {}, tx: {}", jobId, TransactionAspectSupport.currentTransactionStatus());
        throw throwable;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void txRequiredNewSuccess(String jobId) {
        log.debug("txRequiredNewSuccess... job: {}, tx: {}", jobId, TransactionAspectSupport.currentTransactionStatus());
        success(jobId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void txRequiredNewFail(String jobId, Throwable throwable) throws Throwable {
        log.debug("txRequiredNewFail... job: {}, tx: {}", jobId, TransactionAspectSupport.currentTransactionStatus());
        throw throwable;
    }

    private void success(String jobId) {
        txRepo.save(new Tx(jobId, System.currentTimeMillis()));
    }
}
