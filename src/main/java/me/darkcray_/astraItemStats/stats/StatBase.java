package me.darkcray_.astraItemStats.stats;

import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;
import java.util.List;

public class StatBase {
    public final String id;
    public final String display;
    public final List<String> lore;
    public final double multiplier;
    public final DecimalFormat format;

    public StatBase(FileConfiguration cfg) {
        this.id = cfg.getString("id");
        this.display = cfg.getString("display");
        this.lore = cfg.getStringList("lore");
        this.multiplier = cfg.getDouble("multiplier", 1.0);
        this.format = new DecimalFormat(cfg.getString("format", "#.0"));
    }
}
