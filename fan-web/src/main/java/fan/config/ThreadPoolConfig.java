package fan.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Thread pool configuration
 *
 * @author Fan
 * @since 2024/4/2 10:10
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    ThreadPoolProperties properties = new ThreadPoolProperties();

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setAllowCoreThreadTimeOut(properties.isAllowCoreThreadTimeOut());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setWaitForTasksToCompleteOnShutdown(properties.isWaitForTasksToCompleteOnShutdown());
        // 设置任务拒绝策略, 可以实现 RejectedExecutionHandler 接口, 自定义处理器
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Data
    static class ThreadPoolProperties {

        /**
         * 核心线程数, 默认为 1
         */
        private int corePoolSize = 8;


        /**
         * 最大线程数, 默认为 Integer.MAX_VALUE
         */
        private int maxPoolSize = 20;

        /**
         * 允许线程空闲时间, 默认为 60s
         */
        private int keepAliveSeconds = 60;

        /**
         * 缓冲队列大小, 默认为 Integer.MAX_VALUE
         */
        private int queueCapacity = 8;

        /**
         * 允许核心线程超时, 默认 false
         */
        private boolean allowCoreThreadTimeOut = false;

        /**
         * 线程池名前缀, 用于监控识别
         */
        private String threadNamePrefix = "TaskExecutor-";

        /**
         * 当任务完成后, 长时间无待处理任务时, 销毁线程池
         */
        private boolean waitForTasksToCompleteOnShutdown = false;
    }
}