package de.slx.Vid2Mc.Util;

import java.io.File;

public class WorldHandler {
    public static String getWorldPath(String name) {
        return System.getenv("APPDATA") + "/.minecraft/saves/" + name;
    }

    public static File[] getWorlds() {
        File saveFolder = new File(System.getenv("APPDATA") + "/.minecraft/saves");
        if (saveFolder.exists())
            return saveFolder.listFiles();
        else return null;
    }
}
