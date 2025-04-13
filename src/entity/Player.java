package entity;

import game.Game;
import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    public GamePanel gamePanel;
    public Game game;

    public Player(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = 1080 / 2 - 40;
        y = 520;
        speed = 8;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32 * 4, 40 * 4);
    }

    @Override
    public void getImage() {

        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Kwiatkowski.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        if (game.leftPressed && x - speed >= 0) {
            x -= speed;
        }

        if (game.rightPressed && x + speed <= game.getWidth() - 441) {
            x += speed;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, 32 * 4, 40 * 4, null);
    }

}
