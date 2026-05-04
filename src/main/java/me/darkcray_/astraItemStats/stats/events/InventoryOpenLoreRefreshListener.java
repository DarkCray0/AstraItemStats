package me.darkcray_.astraItemStats.stats.events;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.stats.LoreUpdater;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryOpenLoreRefreshListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (!(e.getPlayer() instanceof Player p)) return;

        for (ItemStack item : p.getInventory().getStorageContents()) {
            LoreUpdater.update(item, AstraItemStats.getInstance());
        }

        for (ItemStack armor : p.getInventory().getArmorContents()) {
            LoreUpdater.update(armor, AstraItemStats.getInstance());
        }

        LoreUpdater.update(p.getInventory().getItemInOffHand(), AstraItemStats.getInstance());
    }
}
