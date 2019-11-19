package de.slx.Vid2Mc;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

import de.slx.Vid2Mc.UI.MainForm;
import de.slx.Vid2Mc.Util.Paths;
import de.slx.Vid2Mc.Util.WorldHandler;
import org.apache.commons.io.FileUtils;

import javax.swing.*;

public class Main {

    public static final String[] protectedKeywords = {"slx", "minecraft", "zdata"};

    private Main() {
        File[] saves = WorldHandler.getWorlds();
        if (saves != null) {
            JComboBox comboBox = new JComboBox(Arrays.stream(saves).map(File::getName).toArray());
            JOptionPane.showMessageDialog(null, comboBox, "Select World", JOptionPane.QUESTION_MESSAGE);
            new MainForm((String) comboBox.getSelectedItem());
        } else
            JOptionPane.showMessageDialog(null, "Minecraft folder not found.");
    }

    public static void main(String[] args) {
        new Main();
    }
}
