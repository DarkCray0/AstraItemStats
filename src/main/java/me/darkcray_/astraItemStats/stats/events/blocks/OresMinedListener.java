package me.darkcray_.astraItemStats.stats.events.blocks;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OresMinedListener implements Listener {
    @EventHandler
    public void onOreBreak(BlockBreakEvent e) {
        if (!e.getBlock().getType().name().endsWith("_ORE")) return;

        StatApply.applyStat(e.getPlayer(), "ores_mined", 1);
    }

}
