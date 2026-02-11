package me.darkcray_.astraItemStats.stats.events;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.stats.LoreUpdater;
import me.darkcray_.astraItemStats.stats.StatBase;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class DamageListener implements Listener {

    private final Plugin plugin;

    public DamageListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;

        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;

        StatBase stat = AstraItemStats.getStats().get("on_damage");
        if (stat == null) return;

        ItemMeta meta = item.getItemMeta();
        assert stat.id != null;
        NamespacedKey key = new NamespacedKey(plugin, stat.id);

        double current = meta.getPersistentDataContainer()
                .getOrDefault(key, PersistentDataType.DOUBLE, 0.0);

        meta.getPersistentDataContainer().set(
                key,
                PersistentDataType.DOUBLE,
                current + (e.getFinalDamage())
        );

        item.setItemMeta(meta);
        LoreUpdater.update(item, plugin);
    }
}
