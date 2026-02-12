package me.darkcray_.astraItemStats.guis;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.lib.Msg;
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

import java.util.List;

public class StatMenu {

    public static void open(Player player, AstraItemStats plugin) {
        Inventory inv = Bukkit.createInventory(null, 27, Msg.get("menu.title", true));

        ItemStack hand = player.getInventory().getItemInMainHand();
        ItemMeta handMeta = hand.getItemMeta();

        for (StatBase stat : plugin.getStats().values()) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();

            NamespacedKey key = new NamespacedKey(plugin, stat.id);
            boolean enabled = handMeta != null &&
                    handMeta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE);

            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', stat.format));

            if (isApplicable(hand, stat.applicable_to)) {
                meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "stat"), PersistentDataType.STRING, stat.id);

                meta.setLore(java.util.List.of(
                        enabled ? Msg.get("menu.active", true) : Msg.get("menu.inactive", true),
                        Msg.get("menu.switch", true)
                ));
            } else {
                meta.setLore(java.util.List.of(
                        Msg.get("menu.not_applicable", true)
                ));
            }

            item.setItemMeta(meta);
            inv.addItem(item);
        }

        player.openInventory(inv);
    }

    public static boolean isApplicable(ItemStack item, List<String> applicable_to) {
        if (item == null || item.getType() == Material.AIR) return false;
        if (applicable_to == null || applicable_to.isEmpty()) return true;

        String material = item.getType().name().toLowerCase();

        for (String rule : applicable_to) {
            String regex = rule
                    .toLowerCase()
                    .replace("*", ".*");

            if (material.matches(regex)) {
                return true;
            }
        }
        return false;
    }

}
