package me.darkcray_.astraItemStats.commands;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.guis.StatMenu;
import me.darkcray_.astraItemStats.lib.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class AstraItemStatsCommand implements CommandExecutor, TabCompleter {

    private final AstraItemStats plugin;

    public AstraItemStatsCommand(AstraItemStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.loadStats();
            Msg.init(plugin);
            Msg.send(sender, "config_reloaded");
            return true;
        }

        if (args[0].equalsIgnoreCase("menu") ) {
            if (sender instanceof Player p) {
                StatMenu.open(p, plugin);
                return true;
            } else {
                Msg.send(sender, "only_player");
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String alias,
            @NotNull String @NotNull [] args
    ) {

        if (args.length == 1) {
            return Arrays.asList("reload", "menu");
        }

        String root = args[0].toLowerCase();

        if ("reload".equals(root)) {
            return null;
        }

        if ("menu".equals(root)) {
            return null;
        }

        return null;
    }
}
