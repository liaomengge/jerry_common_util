package com.sh.lmg.thread;

import com.sh.lmg.log.MwLogger;
import org.slf4j.Logger;

import java.util.concurrent.*;

/**
 * Created by liaomengge on 17/5/17.
 */
public class MwThreadPoolExecutor extends ThreadPoolExecutor {

    protected static final Logger logger = new MwLogger(MwThreadPoolExecutor.class);

    public MwThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        ThreadFactory threadFactory = this.getThreadFactory();
        if (threadFactory instanceof MwDefaultThreadFactory) {
            //统计线程池的走势
            String poolNamePrefix = ((MwDefaultThreadFactory) threadFactory).getPoolNamePrefix();
            System.out.println("poolNamePrefix[" + poolNamePrefix + "] ===> " + this.getPoolSize());
        }
    }

    @Override
    protected void terminated() {
        super.terminated();
        ThreadFactory threadFactory = this.getThreadFactory();
        if (threadFactory instanceof MwDefaultThreadFactory) {
            String poolNamePrefix = ((MwDefaultThreadFactory) threadFactory).getPoolNamePrefix();
            logger.info("thread pool[{}], create largest pool size: {}, completed task count: {}", poolNamePrefix, this.getLargestPoolSize(), this.getCompletedTaskCount());
        }
    }

}
