import java.util.Timer;
import java.util.TimerTask;

class Counter extends TimerTask {
    private int time = 0;
    private static Timer timer = new Timer();

    public Counter() {
        // Here we schedule how often it should count, we would like 1000 ms = 1 second
        timer.schedule(this, 0, 1000);
    }

    public void run() {
        time++;
    }

    public void stop() {
        timer.cancel();
    }

    public int getTime() {
        return time;
    }
}
