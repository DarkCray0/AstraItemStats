package me.darkcray_.astraItemStats.stats.events.kills;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class MobKillListener implements Listener {
    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        if (!(e.getEntity().getKiller() instanceof Player p)) return;

        StatApply.applyStat(
                p,
                "mob_kills",
                1
        );
    }
}
