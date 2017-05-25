package com.sh.lmg.thread;

import lombok.Getter;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liaomengge on 17/5/17.
 */
public class MwDefaultThreadFactory implements ThreadFactory {

    private final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    @Getter
    private final String poolNamePrefix;
    @Getter
    private final String namePrefix;

    public MwDefaultThreadFactory() {
        this("");
    }

    public MwDefaultThreadFactory(String poolNamePrefix) {
        this.poolNamePrefix = poolNamePrefix;
        this.namePrefix = "pool-" + this.poolNamePrefix + "-" + this.poolNumber.getAndIncrement() + "#thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, this.namePrefix + threadNumber.getAndIncrement());
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
