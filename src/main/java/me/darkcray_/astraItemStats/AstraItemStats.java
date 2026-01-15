package me.darkcray_.astraItemStats;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class AstraItemStats extends JavaPlugin {

    @Getter
    private static AstraItemStats instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {}
}
