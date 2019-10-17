package com.neo.http.sample.hello.server.controller;

import com.neo.http.common.NeoHttpException;
import com.neo.http.sample.hello.server.bean.Greeting;
import com.neo.http.sample.hello.server.common.HelloError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private static final String template = "Hello5, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam String name) {
//        throw new NeoHttpException(HelloError.TEST_ERROR);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/test")
    public Greeting test(@RequestBody Greeting greeting) {
        logger.warn("this is test.");
        throw new NeoHttpException(HelloError.TEST_ERROR);
//        return greeting;
    }
}
