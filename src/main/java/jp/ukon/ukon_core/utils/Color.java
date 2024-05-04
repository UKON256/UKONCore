package jp.ukon.ukon_core.utils;

public class Color {
    public static final Color White = new Color(255, 255, 255);
    public static final Color Black = new Color(0, 0, 0);

    protected int red, green, blue, alpha;

    public Color(int red, int green, int blue) {
        this(red, green, blue, 0xff);
    }
    public Color(int red, int green, int blue, int alpha) {
        this.red = red & 0xff;
        this.green = green & 0xff;
        this.blue = blue & 0xff;
        this.alpha = alpha & 0xff;
    }

    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }
    public int getAlpha() {
        return alpha;
    }

    public Color setRed(int red) {
        this.red = red;
        return this;
    }
    public Color setGreen(int green) {
        this.green = green;
        return this;
    }
    public Color setBlue(int blue) {
        this.blue = blue;
        return this;
    }
    public Color setAlpha(int alpha) {
        this.alpha = alpha;
        return this;
    }
}
