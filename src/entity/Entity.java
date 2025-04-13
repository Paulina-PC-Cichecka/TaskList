package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

     public int x, y;
     public double speed;

     public BufferedImage img;
     public int spriteCounter = 0;
     public int spriteNumber = 0;


     public abstract void getImage();

     public abstract void update();

     public abstract void draw(Graphics2D g2d);
}
