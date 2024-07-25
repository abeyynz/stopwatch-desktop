
package stopwatch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class sw extends JFrame implements ActionListener{
    JLabel lbTime = new JLabel();
    JButton btnStart = new JButton("Start") {
        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(new Color(0xf5e4b5));
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, getSize().width, getSize().height );
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, getSize().width, getSize().height);
        }
    };
    
    
    JButton btnReset = new JButton("Reset"){
        @Override
        protected void paintComponent(Graphics h) {
            if (getModel().isArmed()) {
                h.setColor(new Color(0xf5e4b5));
            } else {
                h.setColor(getBackground());
            }
            h.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, getSize().width, getSize().height);
            super.paintComponent(h);
        }

        @Override
        protected void paintBorder(Graphics h) {
            h.setColor(getForeground());
            h.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, getSize().width, getSize().height);
        }
    };
    Timer timer;

    int seconds;
    int minutes;
    int hours;
    boolean running;

    public sw() {
        setFrame();
        createComponents();
    }

    public void createComponents() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        running = false;

        timer = new Timer(10, this);

        lbTime.setText("00:00.00");
        lbTime.setBounds(75, 60, 130, 70);
        lbTime.setHorizontalAlignment(JLabel.CENTER);
        lbTime.setFont(new Font("arial", Font.BOLD, 30));
        add(lbTime);

        btnStart.setBounds(50, 160, 80, 80);
        btnStart.setBackground(Color.green);
        btnStart.setContentAreaFilled(false);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    stopStopwatch();
                    btnStart.setBackground(Color.green);
                } else {
                    startStopwatch();
                    btnStart.setBackground(Color.red);
                }
            }
        });
        add(btnStart);

        btnReset.setBounds(155, 160, 80, 80);
        btnReset.setContentAreaFilled(false);
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetStopwatch();
                stopStopwatch();
            }
        });
        btnReset.setBackground(Color.orange);
        add(btnReset);
        
        btnStart.setFocusPainted(false);
        btnReset.setFocusPainted(false);
//        btnStart.setBorderPainted(false);
    }

    public void setFrame() {
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        getContentPane().setBackground(new Color(0xf5e4b5));
        setTitle("Stopwatch");
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            if (running) {
                seconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                    if (minutes == 60) {
                        minutes = 0;
                        hours++;
                    }
                }
                updateDisplay();
            }
        }
    }

    public void updateDisplay() {
        lbTime.setText(String.format("%02d:%02d.%02d", hours, minutes, seconds));
    }

    public void startStopwatch() {
        running = true;
        btnStart.setText("Stop");
        timer.start();
    }

    public void stopStopwatch() {
        running = false;
        btnStart.setText("Start");
        timer.stop();
    }

    public void resetStopwatch() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        updateDisplay();
    }
}
