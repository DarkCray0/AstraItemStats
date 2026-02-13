package me.darkcray_.astraItemStats.stats.events.damage;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageBlockedListener implements Listener {
    @EventHandler
    public void onBlock(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player p)) return;
        if (!p.isBlocking()) return;

        StatApply.applyStat(p, "damage_blocked", e.getFinalDamage());
    }

}
