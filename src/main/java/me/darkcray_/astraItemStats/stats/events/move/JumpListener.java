package me.darkcray_.astraItemStats.stats.events.move;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class JumpListener implements Listener {
    @EventHandler
    public void onJump(PlayerMoveEvent e) {
        if (e.getTo().getY() <= e.getFrom().getY()) return;

        Player p = e.getPlayer();
        if (p.isFlying() || p.isGliding()) return;

        StatApply.applyStat(p, "jumps", 1);
    }

}
