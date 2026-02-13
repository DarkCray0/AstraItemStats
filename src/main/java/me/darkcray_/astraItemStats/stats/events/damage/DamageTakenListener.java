package me.darkcray_.astraItemStats.stats.events.damage;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class DamageTakenListener implements Listener {
    @EventHandler
    public void onDamageTaken(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;

        StatApply.applyStat(
                p,
                "damage_taken",
                e.getFinalDamage()
        );
    }
}
