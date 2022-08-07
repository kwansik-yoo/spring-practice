package com.example.springpractice.taskdecorator.facade;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

public class FooFacadeTest {
    //
    @Test
    public void fooActionTest() {
        String requesterId = "foo@gmail.com";

        WebClient.create("http://localhost:8080/foo-action")
                .post()
                .header("requester-id", requesterId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        /*
        // without decorator
        2022-08-07 17:22:38.842  INFO 6043 --- [           main] c.e.s.t.config.ThreadExecutorConfig      : task Executor without decorator set...

        2022-08-07 17:22:51.579 DEBUG 6043 --- [nio-8080-exec-1] c.e.s.t.audit.AuditContextFilter         : requester Id : foo@gmail.com
        2022-08-07 17:22:51.614  INFO 6043 --- [nio-8080-exec-1] c.e.s.t.audit.AuditContextHolder         : Audit is set in thread : http-nio-8080-exec-1
        2022-08-07 17:22:51.660  INFO 6043 --- [nio-8080-exec-1] c.e.s.taskdecorator.logic.FooLogic       : [fooAction] thread : http-nio-8080-exec-1, auditId : foo@gmail.com
        2022-08-07 17:22:51.672  INFO 6043 --- [ustom-Executor2] c.e.s.taskdecorator.logic.FooLogic       : [someInternalAsyncLogicWithAudit] thread : Custom-Executor2, auditId : null
        2022-08-07 17:22:51.700  INFO 6043 --- [ustom-Executor1] c.e.s.taskdecorator.logic.BarLogic       : [someAsyncLogicWithAudit] thread : Custom-Executor1, auditId : null
        2022-08-07 17:22:51.706  INFO 6043 --- [nio-8080-exec-1] c.e.s.t.audit.AuditContextHolder         : Audit is cleared in thread : http-nio-8080-exec-1

        // with decorator
        2022-08-07 17:21:10.597  INFO 6022 --- [           main] c.e.s.t.config.ThreadExecutorConfig      : task Executor with decorator set...

        2022-08-07 17:21:19.079 DEBUG 6022 --- [nio-8080-exec-1] c.e.s.t.audit.AuditContextFilter         : requester Id : foo@gmail.com
        2022-08-07 17:21:19.111  INFO 6022 --- [nio-8080-exec-1] c.e.s.t.audit.AuditContextHolder         : Audit is set in thread : http-nio-8080-exec-1
        2022-08-07 17:21:19.167  INFO 6022 --- [nio-8080-exec-1] c.e.s.taskdecorator.logic.FooLogic       : [fooAction] thread : http-nio-8080-exec-1, auditId : foo@gmail.com
        2022-08-07 17:21:19.175  INFO 6022 --- [ustom-Executor1] c.e.s.t.audit.AuditContextHolder         : Audit is set in thread : Custom-Executor1
        2022-08-07 17:21:19.181  INFO 6022 --- [ustom-Executor2] c.e.s.t.audit.AuditContextHolder         : Audit is set in thread : Custom-Executor2
        2022-08-07 17:21:19.186  INFO 6022 --- [ustom-Executor2] c.e.s.taskdecorator.logic.FooLogic       : [someInternalAsyncLogicWithAudit] thread : Custom-Executor2, auditId : foo@gmail.com
        2022-08-07 17:21:19.187  INFO 6022 --- [ustom-Executor2] c.e.s.t.audit.AuditContextHolder         : Audit is cleared in thread : Custom-Executor2
        2022-08-07 17:21:19.198  INFO 6022 --- [ustom-Executor1] c.e.s.taskdecorator.logic.BarLogic       : [someAsyncLogicWithAudit] thread : Custom-Executor1, auditId : foo@gmail.com
        2022-08-07 17:21:19.199  INFO 6022 --- [ustom-Executor1] c.e.s.t.audit.AuditContextHolder         : Audit is cleared in thread : Custom-Executor1
        2022-08-07 17:21:19.209  INFO 6022 --- [nio-8080-exec-1] c.e.s.t.audit.AuditContextHolder         : Audit is cleared in thread : http-nio-8080-exec-1

         */
    }
}
