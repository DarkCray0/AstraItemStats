package me.darkcray_.astraItemStats.lib;

import me.darkcray_.astraItemStats.AstraItemStats;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.ConfigurationSection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class ConfigSyncUtil {

    private final AstraItemStats plugin;

    public ConfigSyncUtil(AstraItemStats plugin) {
        this.plugin = plugin;
    }

    public void syncConfig(String resourcePath) throws IOException {
        File file = new File(plugin.getDataFolder(), resourcePath);
        ensureParent(file);

        YamlConfiguration defaultConfig = loadFromJar(resourcePath);
        YamlConfiguration userConfig = file.exists() ? YamlConfiguration.loadConfiguration(file) : new YamlConfiguration();

        addMissingKeys(defaultConfig, userConfig);

        userConfig.save(file);

        plugin.reloadConfig();
    }

    public void syncLang(String langFileName) throws IOException {
        File langDir = new File(plugin.getDataFolder(), "lang");
        if (!langDir.exists()) langDir.mkdirs();

        File file = new File(langDir, langFileName);
        YamlConfiguration defaultConfig = loadFromJar("lang/" + langFileName);
        YamlConfiguration userConfig = file.exists() ? YamlConfiguration.loadConfiguration(file) : new YamlConfiguration();

        addMissingKeys(defaultConfig, userConfig);

        userConfig.save(file);

        plugin.reloadConfig();
    }

    private YamlConfiguration loadFromJar(String resourcePath) throws IOException {
        InputStream jarStream = plugin.getResource(resourcePath);
        if (jarStream == null) throw new FileNotFoundException("Resource not found in JAR: " + resourcePath);
        return YamlConfiguration.loadConfiguration(new InputStreamReader(jarStream, StandardCharsets.UTF_8));
    }

    private void addMissingKeys(ConfigurationSection source, ConfigurationSection target) {
        Set<String> keys = source.getKeys(false);
        for (String key : keys) {
            if (!target.contains(key)) {
                target.set(key, source.get(key));
            } else if (source.isConfigurationSection(key) && target.isConfigurationSection(key)) {
                addMissingKeys(source.getConfigurationSection(key), target.getConfigurationSection(key));
            }
        }
    }

    private void ensureParent(File file) throws IOException {
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) throw new IOException("Could not create folder: " + parent.getAbsolutePath());
    }
}
