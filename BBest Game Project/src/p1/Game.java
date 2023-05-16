package p1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {
    private int x, y, dx, dy, angle;
    private ArrayList<Circle> circles;
    private Timer timer;

    public Game() {
        x = 100;
        y = 100;
        dx = 0;
        dy = 0;
        angle = 0;
        circles = new ArrayList<Circle>();

        setPreferredSize(new Dimension(400, 400));
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    dy = -2;
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    dx = -2;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    dy = 2;
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    dx = 2;
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    dy = 0;
                } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                    dx = 0;
                }
            }
        });

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cx = x + 10;
                int cy = y + 10;
                int r = 5;
                int vx = (int) (10 * Math.cos(Math.toRadians(angle)));
                int vy = (int) (10 * Math.sin(Math.toRadians(angle)));
                circles.add(new Circle(cx, cy, r, vx + dx, vy + dy));
            }
        });
        timer.start();

        new Timer(10, this).start();
    }

    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;
        angle = (int) Math.toDegrees(Math.atan2(getMousePosition().getY() - y - 10, getMousePosition().getX() - x - 10));
        repaint();

        for (int i = 0; i < circles.size(); i++) {
            Circle c = circles.get(i);
            c.update(dx, dy);
            if (getMousePosition() != null) {
                angle = (int) Math.toDegrees(Math.atan2(getMousePosition().getY() - y - 10, getMousePosition().getX() - x - 10));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angle), 10, 10);
        g2d.fillRect(0, 0, 20, 20);

        for (Circle c : circles) {
            g2d.fillOval(c.getX() - c.getR(), c.getY() - c.getR(), 2 * c.getR(), 2 * c.getR());
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Game());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        }
        }

        class Circle {
        private int x, y, r, vx, vy;
        public Circle(int x, int y, int r, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.vx = vx;
            this.vy = vy;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getR() {
            return r;
        }

        public void update(int dx, int dy) {
            x += vx + dx;
            y += vy + dy;
        }
        }