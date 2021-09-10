package net.kigawa.chunkgenerator.generator.command;

import net.kigawa.chunkgenerator.util.plugin.all.recorder.RecorderData;

public class PlayerData extends RecorderData {
    int x;
    int z;
    String world;
    boolean needTp=false;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public boolean getNeedTp() {
        return needTp;
    }

    public void setNeedTp(boolean needTp) {
        this.needTp = needTp;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
