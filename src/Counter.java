import java.util.Timer;
import java.util.TimerTask;

class Counter {
    private int time = 0;
    private Timer timer;

    public void start() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                time++;
            }
        };

        // Here we schedule how often it should count, we would like 1000 ms = 1 second
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void stop() {
        timer.cancel();
    }

    public void reset() {
        time = 0;
    }

    public int getTime() {
        return time;
    }
}
