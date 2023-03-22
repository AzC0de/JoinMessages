package me.azc0de.joinmessages;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class customlisteners implements Listener {

    private main plugin;

    public customlisteners(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Mensaje de bienvenida en el chat
        if (plugin.getConfig().getBoolean("welcome-message.enabled")) {
            List<String> welcomeMessageLines = plugin.getConfig().getStringList("welcome-message.lines");
            for (String line : welcomeMessageLines) {
                String welcomeMessage = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, line));
                player.sendMessage(welcomeMessage);
            }
        }

        // Mensaje de entrada al servidor
        if (plugin.getConfig().getBoolean("join-message.enabled")) {
            String joinMessage = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString("join-message.text")));
            event.setJoinMessage(joinMessage);
        } else {
            event.setJoinMessage(null);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Mensaje de salida del servidor
        if (plugin.getConfig().getBoolean("quit-message.enabled")) {
            String quitMessage = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString("quit-message.text")));
            event.setQuitMessage(quitMessage);
        } else {
            event.setQuitMessage(null);
        }
    }
}
