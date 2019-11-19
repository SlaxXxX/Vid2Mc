package de.slx.Vid2Mc.Task;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import de.slx.Vid2Mc.Util.Paths;
import de.slx.Vid2Mc.Util.FileHandler;
import org.jcodec.scale.AWTUtil;

public class FrameWorker implements Runnable {
    TaskManager manager;
    Settings settings;
    Frame frame;

    public FrameWorker(TaskManager manager, Settings settings) {
        this.manager = manager;
        this.settings = settings;
    }

    @Override
    public void run() {
        while (null != (frame = manager.nextFrame())) {
            BufferedImage image = AWTUtil.toBufferedImage(frame.picture);
            BufferedImage scaledImage = createResizedCopy(image, (int) (image.getWidth() * settings.scale),
                    (int) (image.getHeight() * settings.scale), true);
            // window.popupImage(scaledImage);
            createFile(scaledImage);
        }
    }

    private void createFile(BufferedImage image) {
        File file = FileHandler.getFile(settings, settings.id, Paths.framesFolder.path + "frame" + frame.index + ".mcfunction");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            int[] rgb;
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    rgb = image.getRaster().getPixel(x, y, new int[3]);
                    int distance = Math.abs(settings.bg.getRed() - rgb[0]) + Math.abs(settings.bg.getGreen() - rgb[1]) + Math.abs(settings.bg.getBlue() - rgb[2]);
                    if (distance > settings.bgThreshold)
                        fw.append(String.format(Locale.ROOT, "particle dust %s %.2f %s 0 0 0 0 1 force @a\n", getColor(rgb), settings.pixelSize * 10, getLoc(x, image.getWidth(), y, image.getHeight())));
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLoc(int x, int w, int y, int h) {
        return String.format(Locale.ROOT, "^%.2f ^%.2f ^", (x - w / 2) * settings.pixelSize, (h - y) * settings.pixelSize);
    }

    private String getColor(int[] rgb) {
        return String.format(Locale.ROOT, "%.2f %.2f %.2f", ((float) rgb[0]) / 255, ((float) rgb[1]) / 255, ((float) rgb[2]) / 255);
    }

    private BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight,
                                            boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
