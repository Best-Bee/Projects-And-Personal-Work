package p1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, KeyListener {

    public static FlappyBird flappyBird;

    public final int WIDTH = 800, HEIGHT = 800;

    public Renderer renderer;

    public int ticks, yMotion, score;

    public boolean gameOver, started;

    public List<Rectangle> pipes;

    public FlappyBird() {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        renderer = new Renderer();

        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        pipes = new ArrayList<Rectangle>();

        start();
    }

    public void start() {
        gameOver = false;
        started = false;
        score = 0;
        yMotion = 0;
        ticks = 0;
        pipes.clear();
        pipes.add(new Rectangle(WIDTH + 100, HEIGHT / 2 - 100, 50, 200));
    }

    public void jump() {
        if (gameOver) {
            start();
        }
        started = true;
        yMotion = -10;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;

        if (started) {

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);
                pipe.x -= 5;
            }

            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for (int i = 0; i < pipes.size(); i++) {
                Rectangle pipe = pipes.get(i);

                if (pipe.x + pipe.width < 0) {
                    pipes.remove(pipe);

                    if (pipe.y == 0) {
                        pipes.add(new Rectangle(WIDTH + 200, HEIGHT / 2 - 100, 50, 200));
                    }
                }
            }

            Rectangle bird = new Rectangle(200, HEIGHT / 2 - 10, 20, 20);

            if (bird.y < 0 || bird.y > HEIGHT) {
                gameOver = true;
            }

            for (Rectangle pipe : pipes) {
                if (pipe.intersects(bird)) {
                    gameOver = true;
                }
            }

            if (gameOver) {
                yMotion = 0;
            } else {
                for (int i = 0; i < pipes.size(); i++) {
                    Rectangle pipe = pipes.get(i);
                    if (pipe.x == 200) {
                        score++;
                    }
                }
            }

            bird.y += yMotion;

            renderer.repaint();
        }
    }

    public static void main(String[] args) {
        flappyBird = new FlappyBird();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    		jump();
    		}
    		}
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public class Renderer extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.cyan);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.orange);
            g.fillRect(0, HEIGHT - 150, WIDTH, 150);

            g.setColor(Color.green);
            g.fillRect(0, HEIGHT - 150, WIDTH, 20);

            g.setColor(Color.red);
            g.fillRect(200, HEIGHT / 2 - 10, 20, 20);

            for (Rectangle pipe : pipes) {
                g.setColor(Color.green.darker());
                g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
            }

            g.setColor(Color.white);
            g.drawString("Score: " + String.valueOf(score), 20, 20);

            if (!started) {
                g.drawString("Press SPACE to start", 275, HEIGHT / 2 - 50);
            }

            if (gameOver) {
                g.drawString("Game Over!", 350, HEIGHT / 2 - 50);
            }
        }
    }
}