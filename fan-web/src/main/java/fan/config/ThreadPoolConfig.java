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
        /*
         * 设置任务拒绝策略, 4 种, ThreadPoolExecutor 类提供了几个内部实现类来处理这几种情况
         * 也可以实现 RejectedExecutionHandler 接口, 自定义处理器
         * 1. AbortPolicy(中止策略): 不执行操作直接抛 RejectedExecutionException 异常, 默认
         * 2. CallerRunsPolicy(调用者执行策略): 由调用者的线程运行, 直接调用 Runnable 的 run 方法
         * 3. DiscardPolicy(丢弃策略): 直接丢弃这个新提交的任务, 不会抛异常
         * 4. DiscardOldestPolicy(丢弃最老的任务策略): 从队列中踢出最先进入队列(最后一个执行)的任务
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    @Data
    static class ThreadPoolProperties {

        /**
         * 核心线程数, 默认为 1. 核心线程会一直存活, 即使没有任务需要执行.
         * 但设置 allowCoreThreadTimeout=true(默认 false)时, 核心线程会超时关闭
         * 当线程数小于核心线程数时, 即使有线程空闲, 线程池也会优先创建新线程处理
         */
        private int corePoolSize = 8;


        /**
         * 最大线程数, 默认为 Integer.MAX_VALUE
         */
        private int maxPoolSize = 20;

        /**
         * 允许线程空闲时间, 默认为 60s
         * 当线程空闲时间达到 keepAliveSeconds 时, 线程会退出, 直到 线程数量=corePoolSize
         * 如果 allowCoreThreadTimeout=true, 则会直到 线程数量=0
         */
        private int keepAliveSeconds = 60;

        /**
         * 缓冲队列大小, 默认为 Integer.MAX_VALUE
         * 这个值肯定要改小, 否则任务陡增时, 都堆在队列中(队列值大)
         * 而核心线程数就那几个, 无法很快执行队列中的任务, 就会延长响应时间, 阻塞任务
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