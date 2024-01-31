package io.temporal.springboot.example.hello.common;

import lombok.Getter;
import lombok.Setter;

public class WorkflowResult {
    @Getter @Setter
    private String greeting;
}
