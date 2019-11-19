package de.slx.Vid2Mc.Task;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import de.slx.Vid2Mc.Util.Paths;
import de.slx.Vid2Mc.UI.MainForm;
import de.slx.Vid2Mc.Util.FileHandler;
import org.apache.commons.io.FileUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.DemuxerTrack;
import org.jcodec.common.io.NIOUtils;

import org.jcodec.common.model.Picture;

import javax.swing.*;

public class TaskManager {
    private File file;
    private Settings settings;
    private MainForm form;
    private Thread[] threads;

    private FrameGrab grab;
    private double videoFps;
    private int frameIndex = 0;
    private double frameOffset = 0;

    public TaskManager(File f, MainForm m, Settings s) {
        file = f;
        settings = s;
        form = m;

        FileHandler.generateStructure(settings);

        try {
            grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
            DemuxerTrack videoTrack = grab.getVideoTrack();
            videoFps = videoTrack.getMeta().getTotalFrames() / videoTrack.getMeta().getTotalDuration();
            form.setMaximumProgress((int) (videoTrack.getMeta().getTotalFrames() * ((20 / settings.frameDelay) / videoFps)));
        } catch (IOException | JCodecException e1) {
            e1.printStackTrace();
        }

        int processors = Runtime.getRuntime().availableProcessors();

        threads = new Thread[processors];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FrameWorker(this, settings));
            threads[i].start();
        }
    }

    synchronized Frame nextFrame() {
        Frame frame = null;
        frameIndex++;
        form.incrementProgress();
        try {
            Picture picture = grab.getNativeFrame();

            if (picture != null) {
                frame = new Frame(picture, frameIndex * settings.frameDelay);
                String command = "execute as @s[scores={slx_frame=" + frameIndex * settings.frameDelay + "}] run function "
                        + settings.id + ":" + Paths.framesFolder.path + "frame" + frameIndex * settings.frameDelay + "\n";

                FileUtils.writeStringToFile(
                        FileHandler.getFile(settings, settings.id, Paths.tick.path), command,
                        StandardCharsets.UTF_8, true);
            } else {
                if (frameIndex > 0) {
                    FileUtils.writeStringToFile(
                            FileHandler.getFile(settings, settings.id, Paths.tick.path),
                            "scoreboard players set @s[scores={slx_frame=" + frameIndex * settings.frameDelay + "..}] slx_frame 0\n",
                            StandardCharsets.UTF_8, true);
                    JOptionPane.showMessageDialog(null, "Donezo!");
                    frameIndex = -1000;
                }
            }

            int delay = settings.frameDelay * (int) (videoFps / 20);
            frameOffset += settings.frameDelay * (videoFps / 20) - delay;
            for (int skip = (int) frameOffset + delay; skip > 1; skip--)
                grab.getNativeFrame();
            frameOffset -= (int) frameOffset;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frame;
    }
}
