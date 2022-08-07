package com.example.springpractice.taskdecorator.facade;

import com.example.springpractice.taskdecorator.logic.FooLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FooFacade {
    private final FooLogic fooLogic;

    @PostMapping("/foo-action")
    public String fooAction(@RequestHeader("requester-id") String requesterId) {
        fooLogic.fooAction();
        return "Foo";
    }
}
