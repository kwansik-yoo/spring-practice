package com.example.springpractice.data.mongodb.transaction;

import com.example.springpractice.data.mongodb.transaction.document.Tx;
import com.example.springpractice.data.mongodb.transaction.repository.TxRepo;
import com.example.springpractice.data.mongodb.transaction.service.TxAction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TxActionTest {
    @Autowired
    private TxAction txAction;
    @Autowired
    private TxRepo txRepo;

    @BeforeAll
    public void clear() {
        txRepo.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void txRequiredSuccessTest() {
        String jobId = "test-required-success";
//        log.debug("txRequiredSuccessTest... job: {}, tx: {}", jobId, TransactionAspectSupport.currentTransactionStatus());

        Tx tx = new Tx(jobId, System.currentTimeMillis());
        txRepo.save(tx);
        txAction.txRequiredSuccess(jobId.concat("-1"));
        txAction.txRequiredNewSuccess(jobId.concat("-2"));
    }

    @Test
    @Transactional
    public void txRequiredFailTest() throws Throwable {
        String jobId = "test-required-fail";
//        log.debug("txRequiredFailTest... job: {}, tx: {}", jobId, TransactionAspectSupport.currentTransactionStatus());

        Tx tx = new Tx(jobId, System.currentTimeMillis());
        txRepo.save(tx);
        txAction.txRequiredSuccess(jobId.concat("-1"));
        txAction.txRequiredNewFail(jobId.concat("-2"), new RuntimeException());
    }
}
