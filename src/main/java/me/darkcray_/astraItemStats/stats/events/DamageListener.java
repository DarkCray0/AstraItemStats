package me.darkcray_.astraItemStats.stats.events;

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
    private final Map<String, StatBase> stats;

    public DamageListener(Plugin plugin, Map<String, StatBase> stats) {
        this.plugin = plugin;
        this.stats = stats;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;

        ItemStack item = p.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;

        StatBase stat = stats.get("damage_dealt");
        if (stat == null) return;

        ItemMeta meta = item.getItemMeta();
        assert stat.id != null;
        NamespacedKey key = new NamespacedKey(plugin, stat.id);

        double current = meta.getPersistentDataContainer()
                .getOrDefault(key, PersistentDataType.DOUBLE, 0.0);

        meta.getPersistentDataContainer().set(
                key,
                PersistentDataType.DOUBLE,
                current + (e.getFinalDamage() * stat.multiplier)
        );

        item.setItemMeta(meta);
        me.example.stattrackers.LoreUpdater.update(item, plugin, stats);
    }
}
