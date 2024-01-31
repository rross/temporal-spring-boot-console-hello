package io.temporal.springboot.example.hello.activities;

import io.temporal.spring.boot.ActivityImpl;
import io.temporal.springboot.example.hello.common.AppConfig;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(taskQueues = AppConfig.QUEUE_NAME)
public class HelloActivityImpl implements HelloActivity {
    @Override
    public String sayHello(String name) {
        return "Hey there " + name + ". Welcome to Temporal!";
    }
}
