package de.slx.Vid2Mc.Util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

import de.slx.Vid2Mc.Main;
import de.slx.Vid2Mc.Task.Settings;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {

    public static void generateStructure(Settings settings) {
        File base = new File(settings.basePath + Paths.base.path);
        if (!base.exists()) {
            try {
                URL url = Main.class.getClassLoader().getResource(Paths.dataTemplate.path);
                File folder = java.nio.file.Paths.get(url.toURI()).toFile();
                FileUtils.copyDirectory(folder, base);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }

        File vidRectory;
        if ((vidRectory = getFile(settings, settings.name, "")).exists()) {
            try {
                FileUtils.deleteDirectory(vidRectory);
                FileUtils.deleteDirectory(getFile(settings, settings.id, ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getFile(settings, settings.id, Paths.framesFolder.path).mkdirs();
        getFile(settings, settings.name, "").mkdirs();

        try {
            FileUtils.writeStringToFile(
                    getFile(settings, settings.id, Paths.videomanager.path),
                    "execute as @e[type=armor_stand,name=\"" + settings.name + "\",tag=slx_play] at @s run function " + settings.id + ":tick",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.id, Paths.tick.path), "scoreboard players add @s slx_frame 1\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.place.path), "summon minecraft:armor_stand ~ ~ ~ {Invisible:1b,Marker:1b,CustomName:\"{\\\"text\\\":\\\"" + settings.name + "\\\"}\"}\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.remove.path), "kill @e[type=armor_stand,name=\"" + settings.name + "\",sort=nearest,limit=1]\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.removeAll.path), "kill @e[type=armor_stand,name=\"" + settings.name + "\"]\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.start.path), "tag @e[type=armor_stand,name=\"" + settings.name + "\",sort=nearest,limit=1] add slx_play\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.startAll.path), "tag @e[type=armor_stand,name=\"" + settings.name + "\"] add slx_play\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.stop.path), "tag @e[type=armor_stand,name=\"" + settings.name + "\",sort=nearest,limit=1] remove slx_play\n",
                    StandardCharsets.UTF_8, true);

            FileUtils.writeStringToFile(
                    getFile(settings, settings.name, Paths.stopAll.path), "tag @e[type=armor_stand,name=\"" + settings.name + "\"] remove slx_play\n",
                    StandardCharsets.UTF_8, true);

            List<String> lines = Files.readAllLines(getFile(settings, "slx", Paths.tick.path).toPath());
            if (!lines.stream().anyMatch(s -> (s.contains(settings.name))))
                FileUtils.writeStringToFile(
                        getFile(settings, "slx", Paths.tick.path), "function " + settings.id + ":videomanager\n",
                        StandardCharsets.UTF_8, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getInstalledVids(String world) {
        File slxTick = getFile(WorldHandler.getWorldPath(world), "slx", Paths.tick.path);
        if (!slxTick.exists())
            return null;
        try {
            List<String> lines = Files.readAllLines(slxTick.toPath());
            return lines.stream().map(line -> line.substring(9, line.length() - 13)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteInstalledVid(String world, String vidId) {

    }

    public static File getFile(Settings settings, String namespace, String function) {
        return getFile(settings.basePath, namespace, function);
    }

    public static File getFile(String worldpath, String namespace, String function) {
        return new File(worldpath + Paths.base.path + Paths.data.path + namespace + Paths.functions.path + function);
    }
}