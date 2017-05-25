package com.sh.lmg.thread;

import com.sh.lmg.log.MwLogger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by liaomengge on 17/5/17.
 */
public class MwThreadPoolExecutorWrapper implements IThreadPoolExecutorWrapper, InitializingBean {

    private static final Logger logger = new MwLogger(MwThreadPoolExecutorWrapper.class);

    private final Object poolSizeMonitor = new Object();

    private int corePoolSize = 1;

    private int maxPoolSize = 128;

    private int keepAliveSeconds = 60;

    private int queueCapacity = 1024;

    private String poolNamePrefix = "mwee";

    private boolean allowCoreThreadTimeOut = false;

    private ThreadPoolExecutor threadPoolExecutor;

    private IThreadExecutorManager iThreadExecutorManager;

    public MwThreadPoolExecutorWrapper() {
        this.corePoolSize = Runtime.getRuntime().availableProcessors();
        this.maxPoolSize = 16;
        this.queueCapacity = 512;
    }

    public void setCorePoolSize(int corePoolSize) {
        synchronized (this.poolSizeMonitor) {
            this.corePoolSize = corePoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setCorePoolSize(corePoolSize);
            }
        }
    }

    public int getCorePoolSize() {
        synchronized (this.poolSizeMonitor) {
            return this.corePoolSize;
        }
    }

    public void setMaxPoolSize(int maxPoolSize) {
        synchronized (this.poolSizeMonitor) {
            this.maxPoolSize = maxPoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
            }
        }
    }

    public int getMaxPoolSize() {
        synchronized (this.poolSizeMonitor) {
            return this.maxPoolSize;
        }
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        synchronized (this.poolSizeMonitor) {
            this.keepAliveSeconds = keepAliveSeconds;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
            }
        }
    }

    public int getKeepAliveSeconds() {
        synchronized (this.poolSizeMonitor) {
            return this.keepAliveSeconds;
        }
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public String getPoolNamePrefix() {
        return poolNamePrefix;
    }

    public void setPoolNamePrefix(String poolNamePrefix) {
        this.poolNamePrefix = poolNamePrefix;
    }

    public int getPoolSize() {
        if (this.threadPoolExecutor == null) {
            return this.corePoolSize;
        }
        return this.threadPoolExecutor.getPoolSize();
    }

    public int getActiveCount() {
        if (this.threadPoolExecutor == null) {
            return 0;
        }
        return this.threadPoolExecutor.getActiveCount();
    }

    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        if (queueCapacity > 0) {
            return new LinkedBlockingQueue<>(queueCapacity);
        }
        return new SynchronousQueue<>();
    }

    protected ThreadFactory createThread(String poolNamePrefix) {
        return new MwDefaultThreadFactory(StringUtils.defaultString(poolNamePrefix));
    }

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
        return this.threadPoolExecutor;
    }

    @Override
    public void execute(Runnable task) {
        Executor executor = this.getThreadPoolExecutor();
        try {
            executor.execute(task);
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
    public Future<?> submit(Runnable task) {
        ExecutorService executor = this.getThreadPoolExecutor();
        try {
            return executor.submit(task);
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executor = this.getThreadPoolExecutor();
        try {
            return executor.submit(task);
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }

    @Override
    public void shutdown() {
        this.getThreadPoolExecutor().shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return this.getThreadPoolExecutor().shutdownNow();
    }

    @Override
    public boolean isTerminated() {
        return this.getThreadPoolExecutor().isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.getThreadPoolExecutor().awaitTermination(timeout, unit);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        iThreadExecutorManager = MwThreadExecutorManager.getInstance();
        IThreadPoolExecutorWrapper poolExecutorWrapper = iThreadExecutorManager.getThreadPool(this.poolNamePrefix);
        if (poolExecutorWrapper != null) {
            threadPoolExecutor = poolExecutorWrapper.getThreadPoolExecutor();
            return;
        }
        BlockingQueue<Runnable> queue = createQueue(this.queueCapacity);
        ThreadFactory threadFactory = createThread(this.poolNamePrefix);
        threadPoolExecutor = new MwThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, this.keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        if (this.allowCoreThreadTimeOut) {
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        iThreadExecutorManager.register(this.poolNamePrefix, this);
        logger.info("注册[{}]线程池成功...", this.poolNamePrefix);
    }
}
