package me.darkcray_.astraItemStats;

import lombok.Getter;
import me.darkcray_.astraItemStats.commands.AstraItemStatsCommand;
import me.darkcray_.astraItemStats.guis.StatsMenuListener;
import me.darkcray_.astraItemStats.lib.ConfigSyncUtil;
import me.darkcray_.astraItemStats.lib.Metrics;
import me.darkcray_.astraItemStats.lib.Msg;
import me.darkcray_.astraItemStats.stats.StatBase;
import me.darkcray_.astraItemStats.stats.StatLoader;
import me.darkcray_.astraItemStats.stats.events.*;
import me.darkcray_.astraItemStats.stats.events.blocks.*;
import me.darkcray_.astraItemStats.stats.events.damage.*;
import me.darkcray_.astraItemStats.stats.events.kills.*;
import me.darkcray_.astraItemStats.stats.events.move.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public final class AstraItemStats extends JavaPlugin {

    @Getter
    private static AstraItemStats instance;
    @Getter
    private static Map<String, StatBase> stats;

    // TODO:
    // - Оптимизировать ивенты
    // - Добавить новые ивенты
    // - Прыжки криво работают

    @Override
    public void onEnable() {
        instance = this;
        new Metrics(this, 29467);

        Msg.init(this);
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        saveDefaultConfig();

        ConfigSyncUtil configsyncUtil = new ConfigSyncUtil(this);

        try {
            configsyncUtil.syncConfig("config.yml");
            configsyncUtil.syncLang("ru_RU.yml");
            configsyncUtil.syncLang("en_US.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        loadStatFile("damage_dealt.yml");
        getServer().getPluginManager().registerEvents(new BowDamageListener(), this);
        loadStatFile("bow_damage.yml");
        getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);
        loadStatFile("players_killed.yml");
        getServer().getPluginManager().registerEvents(new MobKillListener(), this);
        loadStatFile("mobs_killed.yml");
        getServer().getPluginManager().registerEvents(new HitsDealtListener(), this);
        loadStatFile("hits_dealt.yml");
        getServer().getPluginManager().registerEvents(new DamageTakenListener(), this);
        loadStatFile("damage_taken.yml");
        getServer().getPluginManager().registerEvents(new DamageBlockedListener(), this);
        loadStatFile("damage_blocked.yml");
        getServer().getPluginManager().registerEvents(new CritListener(), this);
        loadStatFile("crit_damage.yml");
        getServer().getPluginManager().registerEvents(new BowShootListener(), this);
        loadStatFile("arrows_shot.yml");
        getServer().getPluginManager().registerEvents(new OresMinedListener(), this);
        loadStatFile("ores_mined.yml");
        getServer().getPluginManager().registerEvents(new CropsHarvestedListener(), this);
        loadStatFile("crops_harvested.yml");
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        loadStatFile("blocks_broken.yml");
        getServer().getPluginManager().registerEvents(new BlocksPlacedListener(), this);
        loadStatFile("blocks_placed.yml");
        getServer().getPluginManager().registerEvents(new DistanceFlownListener(), this);
        loadStatFile("distance_flown.yml");
        getServer().getPluginManager().registerEvents(new DistanceSprintedListener(), this);
        loadStatFile("distance_sprinted.yml");
        getServer().getPluginManager().registerEvents(new DistanceWalkedListener(), this);
        loadStatFile("distance_walked.yml");
        getServer().getPluginManager().registerEvents(new FishCaughtListener(), this);
        loadStatFile("fish_caught.yml");
        getServer().getPluginManager().registerEvents(new ExperienceCollectedListener(), this);
        loadStatFile("experience_collected.yml");
        getServer().getPluginManager().registerEvents(new ChatMessagesListener(), this);
        loadStatFile("chat_messages.yml");
        //getServer().getPluginManager().registerEvents(new JumpListener(), this);
        //loadStatFile("jumps.yml");



        AstraItemStatsCommand cmd = new AstraItemStatsCommand(this);
        getCommand("astraitemstats").setExecutor(cmd);
        getCommand("astraitemstats").setTabCompleter(cmd);
        getServer().getPluginManager().registerEvents(new StatsMenuListener(this), this);

        sendStartInfo();
        loadStats();
    }


    public void sendStartInfo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formatted = now.format(formatter);

        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §5+--------------------------+§r");
        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §3       AstraItemStats§r");
        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §8Version: §r" + getPluginMeta().getVersion());
        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §5+--------------------------+§r");


        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §rFully loaded at: " + formatted);
    }

    public static void loadStatFile(String fileName) {

        File outFile = new File(getInstance().getDataFolder(), "stats/" + fileName);
        if (outFile.exists()) return;

        outFile.getParentFile().mkdirs();

        try (InputStream in = getInstance().getResource("stats/" + fileName)) {

            if (in == null) {
                return;
            }

            Files.copy(in, outFile.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {}

    public void loadStats() {
        stats = StatLoader.loadAll(this);
        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §rReloading...!");
        Bukkit.getConsoleSender().sendMessage("§3> [AstraItemStats] §rSuccessfully loaded " + stats.size() + " stats!");
    }
}
