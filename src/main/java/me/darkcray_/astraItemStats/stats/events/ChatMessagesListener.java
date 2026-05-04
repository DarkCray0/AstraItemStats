package me.darkcray_.astraItemStats.stats.events;

import me.darkcray_.astraItemStats.stats.StatApply;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMessagesListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        StatApply.applyStat(
                p,
                "chat_messages",
                1
        );
    }
}
