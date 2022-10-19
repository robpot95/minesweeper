import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    Toolkit toolkit;

    Timer timer;

    public TimerDemo(int seconds) {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            toolkit.beep();
            System.exit(0);
        }
    }


}
