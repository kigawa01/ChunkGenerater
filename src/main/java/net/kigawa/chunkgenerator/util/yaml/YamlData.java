package net.kigawa.chunkgenerator.util.yaml;

import net.kigawa.chunkgenerator.util.all.Named;

public interface YamlData extends Named {
    String name = null;
    public String getName();
    public void setName(String name);
}
