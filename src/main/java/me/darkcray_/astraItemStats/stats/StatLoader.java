package me.darkcray_.astraItemStats.stats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatLoader {

    public static Map<String, StatBase> loadAll(Plugin plugin) {
        Map<String, StatBase> stats = new HashMap<>();
        File dir = new File(plugin.getDataFolder(), "stats");

        if (!dir.exists()) dir.mkdirs();

        for (File file : Objects.requireNonNull(dir.listFiles((d, n) -> n.endsWith(".yml")))) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            StatBase def = new StatBase(cfg);
            stats.put(def.id, def);
        }

        return stats;
    }
}
