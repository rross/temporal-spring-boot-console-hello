package io.temporal.springboot.example.hello.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.springboot.example.hello.common.AppConfig;
import io.temporal.springboot.example.hello.common.WorkflowParameters;
import io.temporal.springboot.example.hello.common.WorkflowResult;
import io.temporal.springboot.example.hello.activities.HelloActivity;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.time.Duration;
@WorkflowImpl(taskQueues = AppConfig.QUEUE_NAME)
public class HelloWorkflowImpl implements HelloWorkflow {

    private HelloActivity activities =
            Workflow.newActivityStub(HelloActivity.class,
                    ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());
    @Override
    public WorkflowResult startMyWorkflow(WorkflowParameters parameters) {
        Logger log = Workflow.getLogger(HelloWorkflowImpl.class);
        WorkflowResult result = new WorkflowResult();
        String response = activities.sayHello(parameters.getName());
        result.setGreeting(response);
        log.info("Workflow is complete. For name: {} the result is {} ",parameters.getName(), response);
        return result;
    }
}
