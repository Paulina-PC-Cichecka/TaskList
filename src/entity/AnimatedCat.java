package entity;

import game.Game;
import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AnimatedCat extends Entity {

    public GamePanel gamePanel;
    public Game game;

    public BufferedImage image1, image2, image3, image4;
    private int frameDelay = 15;

    public AnimatedCat(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        this.game = game;

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = 900;
        y = 630;
    }

    @Override
    public void getImage() {
        try {
            image1 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/animatedCat/catInTheBox1.png"))
            );
            image2 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/animatedCat/catInTheBox2.png"))
            );
            image3 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/animatedCat/catInTheBox3.png"))
            );
            image4 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/animatedCat/catInTheBox4.png"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        spriteCounter++;
        if (spriteCounter > frameDelay) {
            spriteCounter = 0;

            spriteNumber++;
            if (spriteNumber > 4) {
                spriteNumber = 1;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {

        switch (spriteNumber) {
            case 1: {
                img = image1;
                break;
            }
            case 2: {
                img = image2;
                break;
            }
            case 3: {
                img = image3;
                break;
            }
            case 4: {
                img = image4;
                break;
            }

        }
        g2d.drawImage(img, x, y, 30 * 2, 31 * 2, null);
    }

}
