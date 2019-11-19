package de.slx.Vid2Mc.UI;

import de.slx.Vid2Mc.Util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorldPanel extends JPanel {
    String world;

    public WorldPanel(String world) {
        this.world = world;
        setLayout(new GridLayout(0, 2));
        //setAlignmentX(JPanel.RIGHT_ALIGNMENT);

        List<String> vids = FileHandler.getInstalledVids(world);
        if (vids == null)
            return;

        for (String vid : vids) {
            String[] vidData = vid.split("_");
            final String vidName = vidData[vidData.length - 1];

            add(new JLabel(vidName));
            JButton del = new JButton("delete");
            add(del);
            del.addActionListener(e -> delete(vid));
        }
    }

    private void delete(String vidId) {
        FileHandler.deleteInstalledVid(world, vidId);
    }
}
