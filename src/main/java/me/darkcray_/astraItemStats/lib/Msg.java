package me.darkcray_.astraItemStats.lib;

import me.darkcray_.astraItemStats.AstraItemStats;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Msg {
    private static File LANG_FILE;
    private static YamlConfiguration LANG;

    public static void init(AstraItemStats plugin) {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }

        String[] langs = {"ru_RU.yml", "en_US.yml"};

        for (String langFile : langs) {
            File outFile = new File(langFolder, langFile);
            if (!outFile.exists()) {
                plugin.saveResource("lang/" + langFile, false);
            }
        }

        String lang = plugin.getConfig().getString("Language.use", "en_US");
        File file = new File(langFolder, lang + ".yml");

        if (!file.exists()) {
            file = new File(langFolder, "en_US.yml");
        }

        LANG_FILE = file;
        LANG = YamlConfiguration.loadConfiguration(LANG_FILE);
    }

    public static void send(CommandSender p, String key) {
        String msg = LANG.getString(key, "");
        if (msg == null || msg.equals("-")) return;
        if (AstraItemStats.getInstance().getConfig().getBoolean("Language.enable_prefix", true)) {
            msg = LANG.getString("prefix", "") + msg;
        }
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    @NotNull
    public static String get(String key, Boolean remove_prefix) {
        String msg = LANG.getString(key, "");
        if (msg == null || msg.equals("-")) return "Translation not found!";
        if (AstraItemStats.getInstance().getConfig().getBoolean("Language.enable_prefix", true)) {
            if (!remove_prefix) {
                msg = LANG.getString("prefix", "") + msg;
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
