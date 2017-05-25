package com.sh.lmg.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaomengge on 17/5/17.
 */
public class MwThreadExecutorManager implements IThreadExecutorManager {

    private static class MwThreadExecutorManagerHolder {
        private static final MwThreadExecutorManager INSTANCE = new MwThreadExecutorManager();
    }

    public static final MwThreadExecutorManager getInstance() {
        return MwThreadExecutorManagerHolder.INSTANCE;
    }

    private final Map<String, IThreadPoolExecutorWrapper> map;

    private MwThreadExecutorManager() {
        this.map = new HashMap<>();
    }

    public void execute(String poolNamePrefix, Runnable runnable) {
        if (runnable == null) return;
        IThreadPoolExecutorWrapper poolExecutorWrapper = this.getThreadPool(poolNamePrefix);
        poolExecutorWrapper.execute(runnable);
    }

    @Override
    public IThreadPoolExecutorWrapper getThreadPool(String poolNamePrefix) {
        return map.get(poolNamePrefix);
    }

    @Override
    public void register(String poolNamePrefix, IThreadPoolExecutorWrapper poolExecutorWrapper) {
        map.put(poolNamePrefix, poolExecutorWrapper);
    }

    public void shutdownHook() {
        for (Map.Entry<String, IThreadPoolExecutorWrapper> entry : map.entrySet()) {
            IThreadPoolExecutorWrapper poolExecutorWrapper = entry.getValue();
            poolExecutorWrapper.shutdown();
        }
    }
}
