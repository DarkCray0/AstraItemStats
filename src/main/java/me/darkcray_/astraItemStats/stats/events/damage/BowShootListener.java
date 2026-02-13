package me.darkcray_.astraItemStats.stats.events.damage;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.Plugin;

public class BowShootListener implements Listener {
    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;

        StatApply.applyStat(
                p,
                "arrows_shot",
                1
        );
    }
}
