package me.azc0de.joinmessages;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        instance = this;

        // Guardar y cargar la configuración
        saveDefaultConfig();

        // Registrar eventos del CustomListener
        Bukkit.getPluginManager().registerEvents(new customlisteners(this), this);

        // Comprobar compatibilidad con PlaceholderAPI y registrar expansión si es necesario
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            // Registrar expansión aquí, si es necesario
            // new PAPIExpansion(this).register();
        } else {
            getLogger().warning("No se encontró PlaceholderAPI! Los placeholders personalizados no funcionarán.");
        }
    }

    @Override
    public void onDisable() {
        // Acciones necesarias para la desactivación del plugin (si las hubiera)
    }

    public static main getInstance() {
        return instance;
    }
}