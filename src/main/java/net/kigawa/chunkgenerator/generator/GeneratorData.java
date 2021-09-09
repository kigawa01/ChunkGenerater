package net.kigawa.chunkgenerator.generator;

import net.kigawa.chunkgenerator.util.plugin.all.recorder.RecorderData;

public class GeneratorData extends RecorderData {
    int sX;
    int sZ;
    int mX;
    int mZ;

    public int getmZ() {
        return mZ;
    }

    public void setmZ(int mZ) {
        this.mZ = mZ;
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getsX() {
        return sX;
    }

    public void setsX(int sX) {
        this.sX = sX;
    }

    public int getsZ() {
        return sZ;
    }

    public void setsZ(int sZ) {
        this.sZ = sZ;
    }
}
