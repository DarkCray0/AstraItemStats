package me.darkcray_.astraItemStats;

import lombok.Getter;
import me.darkcray_.astraItemStats.commands.AstraItemStatsCommand;
import me.darkcray_.astraItemStats.guis.StatsMenuListener;
import me.darkcray_.astraItemStats.lib.ConfigSyncUtil;
import me.darkcray_.astraItemStats.lib.Metrics;
import me.darkcray_.astraItemStats.lib.Msg;
import me.darkcray_.astraItemStats.stats.StatBase;
import me.darkcray_.astraItemStats.stats.StatLoader;
import me.darkcray_.astraItemStats.stats.events.DamageListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.Objects;

public final class AstraItemStats extends JavaPlugin {

    @Getter
    private static AstraItemStats instance;
    @Getter
    private Map<String, StatBase> stats;

    @Override
    public void onEnable() {
        instance = this;
        new Metrics(this, 29467);

        Msg.init(this);
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        saveDefaultConfig();
        loadStats();

        ConfigSyncUtil configsyncUtil = new ConfigSyncUtil(this);

        try {
            configsyncUtil.syncConfig("config.yml");
            configsyncUtil.syncLang("ru_RU.yml");
            configsyncUtil.syncLang("en_US.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(
                new DamageListener(this, stats),
                this
        );

        AstraItemStatsCommand cmd = new AstraItemStatsCommand(this);
        getCommand("astraitemstats").setExecutor(cmd);
        getServer().getPluginManager().registerEvents(new StatsMenuListener(this), this);

        getLogger().info("Loaded " + stats.size() + " stat(s).");
    }

    @Override
    public void onDisable() {}

    public void loadStats() {
        this.stats = StatLoader.loadAll(this);
    }
}
