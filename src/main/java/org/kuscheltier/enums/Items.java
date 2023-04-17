package org.kuscheltier.enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {
    GUN(new ItemStack(Material.DIAMOND_BARDING));

    private final ItemStack item;

    Items(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return this.item;
    }
}
