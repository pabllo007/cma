package br.com.cma.cmaimportador.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "cma.tasks")
public class TasksProperties {
    private Integer threadsCount;

}
