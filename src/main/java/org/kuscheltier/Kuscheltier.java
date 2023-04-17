package org.kuscheltier;

import org.bukkit.plugin.java.JavaPlugin;
import org.kuscheltier.commands.OOCCommand;

public final class Kuscheltier extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        getCommand("ooc").setExecutor(new OOCCommand());
    }
}
