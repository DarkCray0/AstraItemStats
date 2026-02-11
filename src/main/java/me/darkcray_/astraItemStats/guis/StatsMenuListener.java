package me.darkcray_.astraItemStats.guis;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.stats.LoreUpdater;
import me.darkcray_.astraItemStats.stats.StatBase;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class StatsMenuListener implements Listener {

    private final AstraItemStats plugin;

    public StatsMenuListener(AstraItemStats plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(StatMenu.TITLE)) return;
        e.setCancelled(true);

        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;


        String statId = clicked.getPersistentDataContainer().get(new NamespacedKey(plugin, "stat"), PersistentDataType.STRING);

        StatBase stat = AstraItemStats.getStats().get(statId);
        if (stat == null) return;

        ItemStack hand = e.getWhoClicked().getInventory().getItemInMainHand();
        ItemMeta meta = hand.getItemMeta();
        assert stat.id != null;
        NamespacedKey key = new NamespacedKey(plugin, stat.id);

        if (meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
            meta.getPersistentDataContainer().remove(key);
        } else {
            meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, 0.0);
        }

        hand.setItemMeta(meta);
        LoreUpdater.update(hand, plugin);

        StatMenu.open((org.bukkit.entity.Player) e.getWhoClicked(), plugin);
    }
}
