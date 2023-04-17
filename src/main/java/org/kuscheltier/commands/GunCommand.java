package org.kuscheltier.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.kuscheltier.enums.Items;

public class GunCommand implements CommandExecutor {

    private final String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "" + ChatColor.BOLD + "GUN" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " ";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory inventory = player.getInventory();
            if (inventory.contains(Items.GUN.getItem())) {
                player.getInventory().remove(Items.GUN.getItem());
                player.sendMessage(PREFIX + "Du hast deine Waffe weggeworfen!");
            } else {
                ItemStack item = Items.GUN.getItem();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GRAY + "M4");
                item.setItemMeta(meta);
                player.getInventory().addItem(item);
                player.sendMessage(PREFIX + "Du hast dir eine Waffe equippt!");
            }
        }

        return true;
    }
}
