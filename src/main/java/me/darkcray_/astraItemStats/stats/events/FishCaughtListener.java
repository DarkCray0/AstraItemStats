package me.darkcray_.astraItemStats.stats.events;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishCaughtListener implements Listener {
    @EventHandler
    public void onFish(PlayerFishEvent e) {
        if (e.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;
        if (!(e.getPlayer() instanceof Player p)) return;

        StatApply.applyStat(
                p,
                "fish_caught",
                1
        );
    }
}
