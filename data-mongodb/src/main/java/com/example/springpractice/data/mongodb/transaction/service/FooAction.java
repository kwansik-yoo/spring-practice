package com.example.springpractice.data.mongodb.transaction.service;

import com.example.springpractice.data.mongodb.transaction.document.Foo;
import com.example.springpractice.data.mongodb.transaction.repository.FooRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FooAction {
    private final FooRepo fooRepo;




    private void action(String jobId) {
        fooRepo.save(new Foo(jobId, System.currentTimeMillis()));
    }
}
