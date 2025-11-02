package id.putra.wms.config.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {

    @Bean
    TaskExecutor processExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(25);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("process-thread-");
        executor.initialize();

        return executor;
    }

    @Bean
    TaskExecutor virtualThreadExecutor() {
        ThreadFactory virtualThreadFactory = Thread.ofVirtual().name("virtual-thread-", 0).factory();
        ExecutorService executorService = Executors.newThreadPerTaskExecutor(virtualThreadFactory);
        return new TaskExecutorAdapter(java.util.Objects.requireNonNull(executorService));
    }

}
