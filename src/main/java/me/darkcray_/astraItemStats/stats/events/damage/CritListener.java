package me.darkcray_.astraItemStats.stats.events.damage;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CritListener implements Listener {
    @EventHandler
    public void onCrit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;
        if (!e.isCritical()) return;

        StatApply.applyStat(p, "on_crit_damage", e.getFinalDamage());
    }
}
