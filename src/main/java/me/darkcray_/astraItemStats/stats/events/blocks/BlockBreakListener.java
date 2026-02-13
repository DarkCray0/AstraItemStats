package me.darkcray_.astraItemStats.stats.events.blocks;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        StatApply.applyStat(
                p,
                "blocks_broken",
                1
        );
    }
}
