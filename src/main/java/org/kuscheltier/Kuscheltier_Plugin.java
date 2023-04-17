package org.kuscheltier;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kuscheltier.commands.GunCommand;
import org.kuscheltier.commands.OOCCommand;
import org.kuscheltier.handlers.GunHandler;

public final class Kuscheltier_Plugin extends JavaPlugin {

    private final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Server" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " ";

    @Override
    public void onEnable() {
        registerCommands();
        registerHandlers();

        Bukkit.broadcastMessage(PREFIX + "Der Server wurde neu geladen!");
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        getCommand("ooc").setExecutor(new OOCCommand());
        getCommand("gun").setExecutor(new GunCommand());
    }

    private void registerHandlers() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GunHandler(), this);
    }
}
