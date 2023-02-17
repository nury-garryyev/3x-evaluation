package io.mend.sast.controller.cwe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cwe676")
public class cwe676 {

    @GetMapping(value = "/unsafeFinalize")
    public void unsafeFinalize() {
          //System.runFinalizersOnExit(true);
          //Runtime.runFinalizersOnExit(true);
    }

    @GetMapping(value = "/safeThread")
    public void safeThread() throws InterruptedException {
        Task task = new Task(5000);
        Thread taskThread = new Thread(task, "TaskThread");

        taskThread.start();
        TimeUnit.MILLISECONDS.sleep(3000);
        task.stop();
    }

    @GetMapping(value = "/unsafeThread")
    public void unsafeThread() throws InterruptedException {
        Task task = new Task(5000);
        Thread taskThread = new Thread(task, "TaskThread");

        taskThread.start();
        TimeUnit.MILLISECONDS.sleep(3000);
        taskThread.stop(); // SINK
    }
}


class Task implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private volatile boolean isStopped = false;
    private final long interval;

    public Task(long interval) {
        this.interval = interval;
    }

    public void run() {

        logger.info("Task thread is started.");

        while (!isStopped) {
            try {
                logger.info("Task thread is running ...");
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }

        logger.info("Task thread is stopped.");
    }

    public void stop() {
        logger.info("Stopping Task thread ...");
        isStopped = true;
    }
}