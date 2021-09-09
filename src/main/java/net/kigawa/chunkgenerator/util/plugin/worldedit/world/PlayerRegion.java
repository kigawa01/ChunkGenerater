package net.kigawa.chunkgenerator.util.plugin.worldedit.world;

import net.kigawa.chunkgenerator.util.plugin.worldedit.WorldEditUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerRegion extends Region {
    private double sX;
    private double sY;
    private double sZ;
    private double eX;
    private double eY;
    private double eZ;


    public PlayerRegion(String world, double centerX, double centerY, double centerZ, double width, double height, double length) {
        super(world, centerX, centerY, centerZ, width, height, length);
    }

    public PlayerRegion(com.sk89q.worldedit.regions.Region region) {
        this(region.getWorld().getName(), region.getCenter().getX()+0.5, region.getCenter().getY()+0.5, region.getCenter().getZ()+0.5, region.getWidth(), region.getHeight(), region.getLength());
    }

    public PlayerRegion(Player player) {
        this(WorldEditUtil.getRegion(player));
    }

    @Override
    public void organizeEnds() {
        sX = ((getcX()) - (getWidth() / 2));
        sY = ((getcY()) - (getHeight() / 2));
        sZ = ((getcZ()) - (getLength() / 2));
        eX = ((getcX()) + (getWidth() / 2));
        eY = ((getcY()) + (getHeight() / 2));
        eZ = ((getcZ()) + (getLength() / 2));

    }

    public double[] getCoordinates() {
        return new double[]{ sX,  sY,  sZ,  eX,  eY,  eZ};
    }

    public boolean contain(Player player) {
        Location loc = player.getLocation();
        if (sX <= loc.getX() && loc.getX() <= eX) {
            if (sY <= loc.getY() && loc.getY() <= eY) {
                if (sZ <= loc.getZ() && loc.getZ() <= eZ) {
                    return true;
                }
            }
        }
        return false;
    }

    public double getsZ() {
        return sZ;
    }

    public double getsY() {
        return sY;
    }

    public double getsX() {
        return sX;
    }

    public double geteZ() {
        return eZ;
    }

    public double geteY() {
        return eY;
    }

    public double geteX() {
        return eX;
    }
}