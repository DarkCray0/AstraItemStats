package me.darkcray_.astraItemStats.stats.events.blocks;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlocksPlacedListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        StatApply.applyStat(
                p,
                "blocks_placed",
                1
        );
    }
}
