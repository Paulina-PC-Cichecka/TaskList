package game;

import entity.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GamePanel extends JPanel implements Runnable {

    private Thread gameThread;
    private Game game;
    private int FPS = 60;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public List<Cat> cats = new ArrayList<>();

    private Background background;
    private Player player;
    private AnimatedCat animatedCat;

    private Font font;
    private int points;

    private int catTimer;

    private Random random;

    public GamePanel(Game game) {
        this.game = game;

        setLayout(new BorderLayout());
        background = new Background(this, game);
        player = new Player(this, game);
        animatedCat = new AnimatedCat(this, game);

        try {
            font = Font.createFont(
                    Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Daydream.ttf"))
            );
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        font = font.deriveFont(18f);

        points = 0;

        catTimer = 0;

        random = new Random();

        setBackground(Color.BLACK);
        gameThread = new Thread(this);
        gameThread.start();
        addKeyListener(game);
        setFocusable(true);

    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread.isAlive()) {

            update();

            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1_000_000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
        animatedCat.update();

        List<Cat> catsToDESTROY = new ArrayList<>();

        for (Cat cat : cats) {
            if (cat.getBounds().intersects(player.getBounds())) {
                points++;
                catsToDESTROY.add(cat);
            }

            if (cat.y >= 600) {
                points--;
                catsToDESTROY.add(cat);
            }
        }

        for (Cat cat : catsToDESTROY) {
            cats.remove(cat);
        }
        catsToDESTROY.clear();

        catTimer++;
        if (catTimer >= 75 ) {
            addCat();

            catTimer = 0;
        }
    }

    private void addCat() {

        Cat cat = new Cat(
                random.nextInt(950), -80, 84, 67, random.nextDouble() * 3 + 3
        );
        cats.add(cat);

        Future<String> future = executorService.submit(() -> {

            while (cat.y <= 600 && !cat.getBounds().intersects(player.getBounds())) {
                cat.update();
                try {
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            return "₍^.̫.^₎";
        });

        game.addTask(new FutureHandler(future, cat));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        background.draw(g2d);
        player.draw(g2d);
        g2d.setFont(font);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawString("Points: " + points, 10, 25);


        for (Cat cat : cats) {
            cat.draw(g2d);
        }

        animatedCat.draw(g2d);

        g2d.dispose();
    }
}
