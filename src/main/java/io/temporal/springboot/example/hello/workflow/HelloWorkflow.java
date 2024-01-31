package io.temporal.springboot.example.hello.workflow;

import io.temporal.springboot.example.hello.common.WorkflowParameters;
import io.temporal.springboot.example.hello.common.WorkflowResult;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorkflow {
    @WorkflowMethod
    WorkflowResult startMyWorkflow(WorkflowParameters parameters);
}
