package io.mend.sast.controller.cwe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
        TimeUnit.MILLISECONDS.sleep(Math.min(time, 1000)); // SANITIZED SINK

        logger.info("Request stopped");
    }

    final AtomicInteger attempt = new AtomicInteger(1);
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    Runnable task = () -> {
        logger.info("Running task...");
        attempt.incrementAndGet();
        if(attempt.get() > 3) {
            executor.shutdown();
            logger.info("Stopped");
        }
    };

    @GetMapping(value = "/hello1/{time}")
    public void hello1(@PathVariable("time") long time) {
        executor.scheduleAtFixedRate(task, 0, time, TimeUnit.MILLISECONDS); // SINK
    }

    @GetMapping(value = "/hello2/{time}")
    public void hello2(@PathVariable("time") long time) {
        executor.scheduleAtFixedRate(task, time, 0, TimeUnit.MILLISECONDS); // SINK
    }

    @GetMapping(value = "/hey1/{time}")
    public void hey1(@PathVariable("time") long time) {
        executor.scheduleWithFixedDelay(task, 0, time, TimeUnit.MILLISECONDS); // SINK
    }

    @GetMapping(value = "/hey2/{time}")
    public void hey2(@PathVariable("time") long time) {
        executor.scheduleWithFixedDelay(task, time, 0, TimeUnit.MILLISECONDS); // SINK
    }

    @GetMapping(value = "/salute/{time}")
    public void salute(@PathVariable("time") long time) {
        executor.schedule(task, time, TimeUnit.MILLISECONDS); // SINK
    }

    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            logger.info("Running task...");
            attempt.incrementAndGet();
            if(attempt.get() > 3) {
                timer.cancel();
                logger.info("Stopped");
            }
        }
    };

    @GetMapping(value = "/schedule1/{time}")
    public void schedule1(@PathVariable("time") long time) {
        timer.schedule(timerTask, time); // SINK
    }

    @GetMapping(value = "/schedule2/{time}")
    public void schedule2(@PathVariable("time") long time) {
        timer.schedule(timerTask, 0, time); // SINK
    }

    @GetMapping(value = "/schedule3/{time}")
    public void schedule3(@PathVariable("time") long time) {
        timer.schedule(timerTask, time, 0); // SINK
    }

    @GetMapping(value = "/schedule4/{time}")
    public void schedule4(@PathVariable("time") long time) {
        timer.schedule(timerTask, 0, time); // SINK
    }

    @GetMapping(value = "/schedule5/{time}")
    public void schedule5(@PathVariable("time") long time) {
        timer.schedule(timerTask, new Date(), time); // SINK
    }

    @GetMapping(value = "/schedule6/{time}")
    public void schedule6(@PathVariable("time") long time) {
        timer.scheduleAtFixedRate(timerTask, new Date(), time); // SINK
    }

    @GetMapping(value = "/schedule7/{time}")
    public void schedule7(@PathVariable("time") long time) {
        timer.scheduleAtFixedRate(timerTask, 0, time); // SINK
    }

    @GetMapping(value = "/schedule8/{time}")
    public void schedule8(@PathVariable("time") long time) {
        timer.scheduleAtFixedRate(timerTask, time, 0); // SINK
    }

    @GetMapping(value = "/threadSleep/{time}")
    public void threadSleep(@PathVariable("time") long time) throws InterruptedException {
        Thread.sleep(time); // SINK
    }

    @GetMapping(value = "/stringInput/{time}")
    public void stringInput(@PathVariable("time") String timeString) throws InterruptedException {
        long time = Long.parseLong(timeString);
        TimeUnit.MILLISECONDS.sleep(time); // SINK
    }

    @GetMapping(value = "/stringInput2/{time}")
    public void stringInput2(@PathVariable("time") String timeString) throws InterruptedException {
        double time = Double.parseDouble(timeString);
        TimeUnit.MILLISECONDS.sleep((long) time); // SINK
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

