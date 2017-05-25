package com.sh.lmg.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaomengge on 17/5/17.
 */
public interface IThreadPoolExecutorWrapper {

    ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException;

    void execute(Runnable task);

    Future<?> submit(Runnable task);

    <T> Future<T> submit(Callable<T> task);

    void shutdown();

    List<Runnable> shutdownNow();

    boolean isTerminated();

    boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
}
