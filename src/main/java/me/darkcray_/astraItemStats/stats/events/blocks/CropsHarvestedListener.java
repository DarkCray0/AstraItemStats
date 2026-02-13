package me.darkcray_.astraItemStats.stats.events.blocks;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CropsHarvestedListener implements Listener {
    @EventHandler
    public void onHarvest(BlockBreakEvent e) {
        Material m = e.getBlock().getType();
        if (m != Material.WHEAT && m != Material.CARROTS && m != Material.POTATOES) return;

        StatApply.applyStat(e.getPlayer(), "crops_harvested", 1);
    }

}
