package com.sh.lmg.thread;

/**
 * Created by liaomengge on 17/5/17.
 */
public interface IThreadExecutorManager {

    void register(String name, IThreadPoolExecutorWrapper poolExecutorWrapper);

    IThreadPoolExecutorWrapper getThreadPool(String name);
}
