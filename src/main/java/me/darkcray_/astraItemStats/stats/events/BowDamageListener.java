package me.darkcray_.astraItemStats.stats.events;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BowDamageListener implements Listener {
    @EventHandler
    public void onBowDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player p)) return;

        StatApply.applyStat(p, "bow_damage", e.getFinalDamage());
    }

}
