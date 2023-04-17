package org.kuscheltier.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public class OOCCommand implements CommandExecutor {

    private final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "OOC" + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + " ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(" ").append(arg);
            }

            List<Entity> entities = player.getNearbyEntities(10, 10, 10);
            entities.add(player);
            for (Entity entity : entities) {
                if (entity instanceof Player) {
                    entity.sendMessage(PREFIX + ChatColor.GRAY + "" + player.getName() + ":" + ChatColor.ITALIC + message);
                }
            }
        }

        return true;
    }
}
