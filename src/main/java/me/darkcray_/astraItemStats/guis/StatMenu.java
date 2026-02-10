package me.darkcray_.astraItemStats.guis;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.stats.StatBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class StatMenu {

    public static final String TITLE = "§8Stat Trackers";

    public static void open(Player player, AstraItemStats plugin) {
        Inventory inv = Bukkit.createInventory(null, 27, TITLE);

        ItemStack hand = player.getInventory().getItemInMainHand();
        ItemMeta handMeta = hand.getItemMeta();

        for (StatBase stat : plugin.getStats().values()) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();

            NamespacedKey key = new NamespacedKey(plugin, stat.id);
            boolean enabled = handMeta != null &&
                    handMeta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE);

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', stat.display));

            meta.setLore(java.util.List.of(
                    enabled ? "§a✔ Активен" : "§c✖ Не активен",
                    "§7Клик — переключить"
            ));

            item.setItemMeta(meta);
            inv.addItem(item);
        }

        player.openInventory(inv);
    }
}
