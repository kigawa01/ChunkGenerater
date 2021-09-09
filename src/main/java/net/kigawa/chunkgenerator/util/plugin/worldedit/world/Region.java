package net.kigawa.chunkgenerator.util.plugin.worldedit.world;

import net.kigawa.chunkgenerator.util.plugin.worldedit.WorldEditUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Region {
    private double cX;
    private double cY;
    private double cZ;
    private double width;
    private double height;
    private double length;
    private String world;

    public Region(String world, double centerX, double centerY, double centerZ, double width, double height, double length) {
        this.world = world;
        this.cX = centerX;
        this.cY = centerY;
        this.cZ = centerZ;
        this.width = width;
        this.height = height;
        this.length = length;

        organizeEnds();
    }

    public Region(com.sk89q.worldedit.regions.Region region) {
        this(region.getWorld().getName(), region.getCenter().getX(), region.getCenter().getY(), region.getCenter().getZ(), region.getWidth(), region.getHeight(), region.getLength());
    }

    public Region(Player player) {
        this(WorldEditUtil.getRegion(player));
    }

    public abstract void organizeEnds();

    public int[] getIntCenter() {
        return new int[]{
                (int) cX, (int) cY, (int) cZ
        };
    }

    public double[] getCenter() {
        return new double[]{
                cX, cY, cZ
        };
    }

    public Block getCenterBlock(JavaPlugin plugin) {
        return new Location(plugin.getServer().getWorld(world), cX, cY, cZ).getBlock();
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public double getcX() {
        return cX;
    }

    public void setcX(double cX) {
        this.cX = cX;
    }

    public double getcY() {
        return cY;
    }

    public void setcY(double cY) {
        this.cY = cY;
    }

    public double getcZ() {
        return cZ;
    }

    public void setcZ(double cZ) {
        this.cZ = cZ;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
