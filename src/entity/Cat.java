package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Cat extends Entity {

    public int width;
    public int height;

    public Cat(int xForCat, int yForCat, int width, int height, double speedForCat) {
        super.x = xForCat;
        super.y = yForCat;
        this.width = width;
        this.height = height;
        super.speed = speedForCat;

        getImage();
    }

    @Override
    public void getImage() {
        Random random = new Random();
        int randomCat = random.nextInt(4) + 1;

        try {
            img = ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/catsImages/cat" + randomCat + ".png"))
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }


    @Override
    public synchronized void update() {
        y += speed;
    }

    @Override
    public synchronized void draw(Graphics2D g2d) {
        g2d.drawImage(img, x, y, width, height, null);
    }
}
