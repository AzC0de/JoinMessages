package me.azc0de.joinmessages;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.Sound;
import java.util.List;

public class customlisteners implements Listener {

    private main plugin;

    public customlisteners(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

        if (plugin.getConfig().getBoolean("welcome-message.enabled")) {
            List<String> welcomeMessageLines = plugin.getConfig().getStringList("welcome-message.lines");
            for (String line : welcomeMessageLines) {
                String welcomeMessage = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, line));
                player.sendMessage(welcomeMessage);
            }
        } else {
            event.setJoinMessage(null);
        }

        if (player.hasPermission("minecraft.receivejoinleave") && plugin.getConfig().getBoolean("join-message.enabled")) {
            event.setJoinMessage(null); // Omitir el mensaje de entrada personalizado si el jugador ha optado por no recibir mensajes de entrada predeterminados
        } else if (plugin.getConfig().getBoolean("join-message.enabled")) {
            String joinMessage = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString("join-message.text")));
            event.setJoinMessage(joinMessage);
        } else {
            event.setJoinMessage(null);
        }

        String title = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-messages.title"));
        String subtitle = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("title-messages.subtitle"));
        int fadeIn = plugin.getConfig().getInt("title-messages.fade-in");
        int stay = plugin.getConfig().getInt("title-messages.stay");
        int fadeOut = plugin.getConfig().getInt("title-messages.fade-out");

        if (plugin.getConfig().getBoolean("title-messages.enabled")) {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }




    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("minecraft.receivejoinleave") && plugin.getConfig().getBoolean("quit-message.enabled")) {
            event.setQuitMessage(null); // Omitir el mensaje de salida personalizado si el jugador ha optado por no recibir mensajes de salida predeterminados
        } else if (plugin.getConfig().getBoolean("quit-message.enabled")) {
            String quitMessage = ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(player, plugin.getConfig().getString("quit-message.text")));
            event.setQuitMessage(quitMessage);
        } else {
            event.setQuitMessage(null);
        }
    }

}
