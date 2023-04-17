package org.kuscheltier.handlers;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.kuscheltier.enums.Items;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GunHandler implements Listener {

    public static final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public static final HashMap<UUID, Integer> cooldowntimes = new HashMap<>();

    @EventHandler
    public static void onInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().equals(Items.GUN.getItem())) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (cooldowns.containsKey(event.getPlayer().getUniqueId())) {
                    long secondsLeft = cooldowns.get(player.getUniqueId()) + cooldowntimes.get(player.getUniqueId()) - System.currentTimeMillis();
                    if (secondsLeft > 0L) {
                        return;
                    }
                }
                cooldowns.put(player.getUniqueId(), System.currentTimeMillis());

                Vector playerDirection = player.getLocation().getDirection();
                Arrow bullet = player.launchProjectile(Arrow.class, playerDirection);

                bullet.setGravity(false);
                bullet.setVelocity(bullet.getVelocity().multiply(3.5));
                bullet.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                cooldowntimes.put(player.getUniqueId(), 0);

                List<Entity> entities = player.getNearbyEntities(50, 50, 50);
                entities.add(player);
                for (Entity entity : entities) {
                    if (entity instanceof Player) {
                        ((Player) entity).playSound(entity.getLocation(), Sound.ENTITY_FIREWORK_BLAST, (float) (50 / entity.getLocation().distance(player.getLocation())), 0.55F);

                        Location origin = player.getEyeLocation().add(0, 0.2, 0);
                        Vector direction = origin.getDirection();
                        Location loc = origin.add(direction);
                        player.spawnParticle(Particle.SMOKE_NORMAL, loc.add(direction.clone().multiply(0.5D)), 1, 0.05D, 0.05D, 0.05D, 0.0D);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockhit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            event.getEntity().remove();
        }
    }
}
