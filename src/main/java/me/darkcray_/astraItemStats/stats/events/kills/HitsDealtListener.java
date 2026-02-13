package me.darkcray_.astraItemStats.stats.events.kills;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HitsDealtListener implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;

        StatApply.applyStat(p, "hits_dealt", 1);
    }

}
