package ru.ultimatehikari.scrsorter;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.Getter;

@Getter
public class AppExecutorsPool {
    private final Executor poolDiskIO;
    private final Executor poolMainThread;

    private AppExecutorsPool(Executor diskIO, Executor mainThread) {
        this.poolDiskIO = diskIO;
        this.poolMainThread = mainThread;
    }

    public AppExecutorsPool() {
        this(Executors.newSingleThreadExecutor(), new MainThreadExecutor());
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
