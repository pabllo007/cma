package br.com.cma.cmaimportador.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@Configuration
public class TaskConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(TasksProperties tasksProperties) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(tasksProperties.getThreadsCount());
        return threadPoolTaskScheduler;
    }
}
