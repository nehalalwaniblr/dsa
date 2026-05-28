package some.javatypes.implementations;

import java.util.concurrent.*;

public class MyThreadPool {

    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private volatile boolean isShutdown = false;   // volatile — all threads see it

    public MyThreadPool(int poolSize, int queueCapacity) {
        this.taskQueue = new LinkedBlockingQueue<>(queueCapacity);
        this.workers   = new Thread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread("worker-" + i);
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        if (isShutdown)
            throw new RejectedExecutionException("Pool is shut down");
        if (!taskQueue.offer(task))
            throw new RejectedExecutionException("Task queue is full");
    }

    private class WorkerThread extends Thread {
        WorkerThread(String name) { super(name); }

        @Override
        public void run() {
            while (!isShutdown || !taskQueue.isEmpty()) {
                try {
                    // poll with timeout — re-checks isShutdown periodically
                    // take() would block forever and miss the shutdown signal
                    Runnable task = taskQueue.poll(500, TimeUnit.MILLISECONDS);
                    if (task != null) task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // restore the flag
                    break;
                } catch (Exception e) {
                    // Task threw — log but keep the worker alive
                    System.err.println(getName() + " task failed: " + e);
                }
            }
        }
    }

    // Graceful: drain queue first, then stop
    public void shutdown() throws InterruptedException {
        isShutdown = true;
        for (Thread w : workers) w.join();
    }

    // Hard: drop pending tasks, interrupt immediately
    public void shutdownNow() {
        isShutdown = true;
        taskQueue.clear();
        for (Thread w : workers) w.interrupt();
    }
}