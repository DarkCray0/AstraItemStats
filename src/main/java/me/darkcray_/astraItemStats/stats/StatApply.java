package me.darkcray_.astraItemStats.stats;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.stats.StatBase;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class StatApply {

    public static void applyStat(
            Player player,
            String statId,
            double amount
    ) {

        StatBase stat = AstraItemStats.getStats().get(statId);
        if (stat == null) return;

        NamespacedKey key = new NamespacedKey(AstraItemStats.getInstance(), stat.id);

        applyToItem(
                player.getInventory().getItemInMainHand(),
                key,
                amount,
                AstraItemStats.getInstance(),
                false
        );

        applyToItem(
                player.getInventory().getItemInOffHand(),
                key,
                amount,
                AstraItemStats.getInstance(),
                false
        );

        for (ItemStack armor : player.getInventory().getArmorContents()) {
            applyToItem(
                    armor,
                    key,
                    amount,
                    AstraItemStats.getInstance(),
                    true
            );
        }
    }

    private static void applyToItem(
            ItemStack item,
            NamespacedKey key,
            double amount,
            AstraItemStats plugin,
            boolean updateLore
    ) {
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();

        if (!meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
            return;
        }

        double current = meta.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE);

        meta.getPersistentDataContainer().set(
                key,
                PersistentDataType.DOUBLE,
                current + amount
        );

        item.setItemMeta(meta);
        if (updateLore) {
            LoreUpdater.update(item, plugin);
        }
    }
}
