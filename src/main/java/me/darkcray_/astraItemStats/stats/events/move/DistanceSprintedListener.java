package me.darkcray_.astraItemStats.stats.events.move;

import me.darkcray_.astraItemStats.lib.MoveUtil;
import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DistanceSprintedListener implements Listener {
    @EventHandler
    public void onSprint(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!p.isSprinting()) return;

        double dist = MoveUtil.distance(e);
        if (dist <= 0) return;

        StatApply.applyStat(p, "distance_sprinted", dist);
    }

}
