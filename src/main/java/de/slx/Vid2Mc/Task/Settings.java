package de.slx.Vid2Mc.Task;

import java.awt.*;

public class Settings {
    public double scale, pixelSize;
    public int frameDelay, bgThreshold;
    public String name, id, basePath;
    public Color bg;

    public Settings(double scale, double size, int delay) {
        this.scale = scale;
        this.pixelSize = size;
        this.frameDelay = delay;
    }

    public Settings fill(String name, String basePath, Color color, int thresh) {
        this.name = name.toLowerCase();
        this.id = "zdata_mp4_" + name.toLowerCase();
        this.basePath = basePath;
        bg = color;
        bgThreshold = thresh;
        return this;
    }
}
