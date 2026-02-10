package me.darkcray_.astraItemStats.commands;

import me.darkcray_.astraItemStats.AstraItemStats;
import me.darkcray_.astraItemStats.guis.StatMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AstraItemStatsCommand implements CommandExecutor {

    private final AstraItemStats plugin;

    public AstraItemStatsCommand(AstraItemStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.loadStats();
            sender.sendMessage("§aStatTrackers перезагружен.");
            return true;
        }

        if (args[0].equalsIgnoreCase("menu") && sender instanceof Player p) {
            StatMenu.open(p, plugin);
            return true;
        }

        return false;
    }
}
