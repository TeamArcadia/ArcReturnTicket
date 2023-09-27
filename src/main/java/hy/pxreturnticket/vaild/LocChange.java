package hy.pxreturnticket.vaild;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocChange {
    private Location originalLocation;
    private Player player;

    public LocChange(Player player) {
        this.player = player;
        this.originalLocation = player.getLocation();
    }

    public boolean hasMoved() {
        Location currentLocation = player.getLocation();

        return currentLocation.getX() != originalLocation.getX()
                || currentLocation.getY() != originalLocation.getY()
                || currentLocation.getZ() != originalLocation.getZ();
    }
}