package src.Paint;

import java.awt.*;

public class Button {
    //coordinates for button placement
    private int x;
    private int y;
    //Area for button interaction
    private Rectangle rect;
    //image for button
    private Image image;
    private boolean isPressed;

    public Button(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.image = image;
        this.rect = new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Image getImage() {
        return image;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
