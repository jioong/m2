package example;

import java.awt.*;

public class BallRunnable implements Runnable {

    private Ball ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    public BallRunnable(Ball ball, Component component) {
        this.ball = ball;
        this.component = component;
    }
    public void run() {
        try {
            for(int i = 0; i < STEPS; i ++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
