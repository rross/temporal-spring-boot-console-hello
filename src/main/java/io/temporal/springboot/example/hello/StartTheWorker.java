package io.temporal.springboot.example.hello;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class StartTheWorker implements CommandLineRunner {

    public static void main(String[] args) {
        // NOTE that ${random.uuid} in properties will not work since
        // you receive a new random value per-component under SpringBoot's creation
        System.setProperty("APP_UUID", UUID.randomUUID().toString());
        SpringApplication.run(StartTheWorker.class, args);
    }

    @Autowired
    private WorkerFactory workerFactory;

    @Override
    public void run(String... args) throws Exception {
        Logger log = Workflow.getLogger(StartTheWorker.class);
        log.info("Starting the workers...");
        workerFactory.start();
    }
}
