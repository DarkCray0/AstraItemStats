package me.darkcray_.astraItemStats.stats.events;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class ExperienceCollectedListener implements Listener {
    @EventHandler
    public void onExp(PlayerExpChangeEvent e) {
        Player p = e.getPlayer();
        int amount = e.getAmount();
        if (amount <= 0) return;

        StatApply.applyStat(
                p,
                "experience_collected",
                amount
        );
    }
}
