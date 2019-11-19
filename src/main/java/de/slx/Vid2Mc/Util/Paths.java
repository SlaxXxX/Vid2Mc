package de.slx.Vid2Mc.Util;

public enum Paths {
    base("/datapacks/Vid2Mc"),
    dataTemplate("DataPack_Template"),
    resTemplate("ResPack_Template"),
    data("/data/"),
    functions("/functions/"),
    framesFolder("frames/"),
    videomanager("videomanager.mcfunction"),
    tick("tick.mcfunction"),
    place("place.mcfunction"),
    remove("remove.mcfunction"),
    removeAll("removeall.mcfunction"),
    start("start.mcfunction"),
    startAll("startall.mcfunction"),
    stop("stop.mcfunction"),
    stopAll("stopall.mcfunction");

    public final String path;

    Paths(String path) {
        this.path = path;
    }
}
