package de.slx.Vid2Mc.UI;

import de.slx.Vid2Mc.Main;
import de.slx.Vid2Mc.Task.TaskManager;
import de.slx.Vid2Mc.Util.WorldHandler;
import org.apache.commons.io.FilenameUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.io.NIOUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private File file;
    private JTextField filePath;
    private JTextField vidName;
    private JButton colorChoose;
    private SettingsPanel settingsPanel;
    private JButton fileChoose;
    private JButton startButton;
    private JTextField bgThreshold;
    private JProgressBar progressBar;
    private WorldPanel worldPanel;
    private JButton changeWorldButton;
    private Color background = Color.black;
    private String world;

    public MainForm(String world) {
        super("Vid2Mc");
        this.world = world;
        this.setSize(550, 300);
        this.setResizable(false);
        this.setContentPane(mainPanel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        fileChoose.addActionListener((e) -> {
            FileFilter filter = new FileNameExtensionFilter("Video", "mp4");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(filter);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                filePath.setText(file.getAbsolutePath());
                loadFile();
            }
        });
        setBGButtonColor(background);
        colorChoose.addActionListener((e) -> {
            setBGButtonColor(JColorChooser.showDialog(this, "Choose Background Color", background));
        });
        startButton.addActionListener(this::runConvert);
    }

    private void loadFile() {
        try {
            FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(file));
            settingsPanel.setVideoInformation(grab.getMediaInfo().getDim());
            settingsPanel.setEnabled(true);
            if (vidName.getText().equals(""))
                vidName.setText(FilenameUtils.removeExtension(file.getName()));
        } catch (Exception e) {
            settingsPanel.setEnabled(false);
        }
    }

    private void setBGButtonColor(Color bg) {
        background = bg;
        colorChoose.setBackground(bg);
        colorChoose.setForeground(new Color(255 - bg.getRed(), 255 - bg.getGreen(), 255 - bg.getBlue()));
    }

    private void runConvert(ActionEvent e) {
        if (vidName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name can't be empty");
            return;
        }
        for (String keyword : Main.protectedKeywords) {
            if (vidName.getText().startsWith(keyword)) {
                JOptionPane.showMessageDialog(this, "Name can't start with " + keyword);
                return;
            }
        }
        progressBar.setValue(0);
        new TaskManager(file, this, settingsPanel.generateSettings().fill(vidName.getText(), WorldHandler.getWorldPath(world), background, Integer.parseInt(bgThreshold.getText())));
    }

    public void popupImage(BufferedImage image) {
        JLabel picLabel = new JLabel(new ImageIcon(image));
        JOptionPane.showMessageDialog(this, picLabel, "About", JOptionPane.PLAIN_MESSAGE, null);
    }

    private void createUIComponents() {
        settingsPanel = new SettingsPanel();
        settingsPanel.setEnabled(false);
        worldPanel = new WorldPanel(world);
    }

    public void setMaximumProgress(int max) {
        progressBar.setMaximum(max);
    }

    public void incrementProgress() {
        if (progressBar.getValue() < progressBar.getMaximum())
            progressBar.setValue(progressBar.getValue() + 1);
    }
}
