package example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }


        });
    }
}

class BounceFrame extends JFrame {

    private BallComponent ball;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    public BounceFrame() {
        setTitle("Bounce");
        ball = new BallComponent();
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "start", new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addBall();
            }
        });

        addButton(buttonPanel, "Close", new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
        try {
            Ball b = new Ball();
            ball.addBall(b);
            for(int i = 0; i < STEPS; i ++) {
                b.move(ball.getBounds());
                ball.paint(ball.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

