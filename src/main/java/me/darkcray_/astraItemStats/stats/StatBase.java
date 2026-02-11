package me.darkcray_.astraItemStats.stats;

import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;
import java.util.List;

public class StatBase {
    public final String id;
    public final List<String> triggers;
    public final List<String> applicable_to;
    public final String format;

    public StatBase(FileConfiguration cfg) {
        this.id = cfg.getString("id");
        this.triggers = cfg.getStringList("triggers");
        this.applicable_to = cfg.getStringList("applicable-to");
        this.format = cfg.getString("format");
    }
}
