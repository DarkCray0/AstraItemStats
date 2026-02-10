package me.example.stattrackers;

import me.darkcray_.astraItemStats.stats.StatBase;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoreUpdater {

    public static void update(
            ItemStack item,
            Plugin plugin,
            Map<String, StatBase> stats
    ) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        List<String> newLore = new ArrayList<>();

        for (StatBase stat : stats.values()) {
            NamespacedKey key = new NamespacedKey(plugin, stat.id);

            double value = meta.getPersistentDataContainer()
                    .getOrDefault(key, PersistentDataType.DOUBLE, 0.0);

            String display = ChatColor.translateAlternateColorCodes(
                    '&',
                    stat.display.replace("%value%", stat.format.format(value))
            );

            for (String line : stat.lore) {
                newLore.add(ChatColor.translateAlternateColorCodes(
                        '&',
                        line.replace("%display%", display)
                ));
            }
        }

        meta.setLore(newLore);
        item.setItemMeta(meta);
    }
}
