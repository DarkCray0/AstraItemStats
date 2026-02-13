package me.darkcray_.astraItemStats.stats.events.kills;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerKillListener implements Listener {
    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer == null) return;

        StatApply.applyStat(
                killer,
                "player_kills",
                1
        );
    }
}
