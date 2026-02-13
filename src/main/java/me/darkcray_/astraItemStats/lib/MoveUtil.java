package me.darkcray_.astraItemStats.lib;

import org.bukkit.event.player.PlayerMoveEvent;

public class MoveUtil {
    public static double distance(PlayerMoveEvent e) {
        if (e.getFrom().getWorld() != e.getTo().getWorld()) return 0;
        if (e.getFrom().getBlockX() == e.getTo().getBlockX()
                && e.getFrom().getBlockY() == e.getTo().getBlockY()
                && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) return 0;

        return e.getFrom().toVector().distance(e.getTo().toVector());
    }
}
