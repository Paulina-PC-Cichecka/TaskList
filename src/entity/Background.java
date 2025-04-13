package entity;

import game.Game;
import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Background extends Entity {

    public Game game;
    public GamePanel gamePanel;

    public Background(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
    }

    @Override
    public void getImage() {
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Background.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {}

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }

}
