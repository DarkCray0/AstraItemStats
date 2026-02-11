package me.darkcray_.astraItemStats.stats;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.stats.StatBase;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoreUpdater {

    public static void update(
            ItemStack item,
            Plugin plugin
    ) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        List<String> newLore = new ArrayList<>();

        for (StatBase stat : AstraItemStats.getStats().values()) {
            NamespacedKey key = new NamespacedKey(plugin, stat.id);

            if (!meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
                meta.setLore(newLore);
            } else {
                double value = meta.getPersistentDataContainer()
                        .getOrDefault(key, PersistentDataType.DOUBLE, 0.0);

                BigDecimal bd = new BigDecimal(Double.toString(value));
                bd = bd.setScale(1, RoundingMode.HALF_UP);
                double roundedValue = bd.doubleValue();

                newLore.add(ChatColor.translateAlternateColorCodes(
                        '&',
                        stat.format.replace("%value%", String.valueOf(roundedValue))
                ));
            }
        }

        meta.setLore(newLore);
        item.setItemMeta(meta);
    }
}
