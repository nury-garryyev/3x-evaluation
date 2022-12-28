package io.mend.sast.controller.cwe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/cwe400")
public class cwe400_sleep {

    private static final Logger logger = LoggerFactory.getLogger(cwe400_sleep.class);

    @GetMapping(value = "/sleep/{time}")
    public void sleep(@PathVariable("time") long time) throws InterruptedException {
        logger.info("Request started");

        logger.info(String.format("Sleep for %d milliseconds", time));
        TimeUnit.MILLISECONDS.sleep(time); // SINK

        logger.info("Request stopped");
    }

    @GetMapping(value = "/safeSleep/{time}")
    public void safeSleep(@PathVariable("time") long time) throws InterruptedException {
        logger.info("Request started");

        logger.info(String.format("Sleep for %d milliseconds", time));
        TimeUnit.MILLISECONDS.sleep(Math.min(time, 1000)); // SINK

        logger.info("Request stopped");
    }

    @GetMapping(value = "/hello/{time}")
    public void hello(@PathVariable("time") long time) {
        final AtomicInteger attempt = new AtomicInteger(1);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {

            logger.info("Hello");
            attempt.incrementAndGet();

            if(attempt.get() > 3) {
                executor.shutdown();
                logger.info("Stopped");
            }

        }, 0, time, TimeUnit.MILLISECONDS); // SINK
    }

    @GetMapping(value = "/hi/{time}")
    public void hi(@PathVariable("time") long time) throws InterruptedException {
        final AtomicInteger attempt = new AtomicInteger(1);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("Hi");
                attempt.incrementAndGet();

                if(attempt.get() > 3) {
                    timer.cancel();
                    logger.info("Stopped");
                }
            }
        }, 0, time); // SINK
    }

    Game game = new Game();
    Thread t1 = new Thread(game, "T1");

    @GetMapping(value = "/thread/run")
    public void run() throws InterruptedException {
        t1.start();
    }

    @GetMapping(value = "/thread/speed/{time}")
    public void speed(@PathVariable("time") long time) {
        game.speed(time);
    }

    @GetMapping(value = "/thread/stop")
    public void stop() {
        game.stop();
    }
}

class Game implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private volatile boolean isStopped = false;
    private volatile long speed = 1000;

    public void run() {

        logger.info("Game thread is started.");

        while (!isStopped) {
            try {
                logger.info("Hey");
                Thread.sleep(speed); // SINK
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }

        logger.info("Game thread is stopped.");
    }

    public void speed(long speed) {
        logger.info("Changing logging speed: " + speed);
        this.speed = speed;
    }

    public void stop() {
        logger.info("Stopping game thread...");
        isStopped = true;
    }
}

