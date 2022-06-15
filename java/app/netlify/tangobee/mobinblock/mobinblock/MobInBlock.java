package app.netlify.tangobee.mobinblock.mobinblock;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Random;

public final class MobInBlock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("\u00A70 Plugin is Started");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Location playLoc = event.getPlayer().getLocation();
        event.getPlayer().setHealth(20);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            event.getPlayer().playSound(playLoc,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,SoundCategory.MASTER,0.9f,0.4f);
            event.getPlayer().sendTitle("\u00A72 3 Seconds",null,0,20,0);
        }, 20L);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            event.getPlayer().playSound(playLoc,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,SoundCategory.MASTER,0.9f,0.4f);
            event.getPlayer().sendTitle("\u00A79 2 Seconds",null,0,20,0);
        }, 40L);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            event.getPlayer().playSound(playLoc,Sound.ENTITY_EXPERIENCE_ORB_PICKUP,SoundCategory.MASTER,0.9f,0.4f);
            event.getPlayer().sendTitle("\u00A7a 1 Seconds",null,0,20,0);
        }, 60L);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            event.getPlayer().playSound(playLoc,Sound.BLOCK_AMETHYST_BLOCK_BREAK,SoundCategory.MASTER,0.9f,0.4f);
            event.getPlayer().sendTitle("\u00A7e Spawn!!!",null,0,20,0);
            Location location = event.getBlock().getLocation();
            location = location.add(0,1,0);
            EntityType entityType = EntityType.values()[new Random().nextInt(EntityType.values().length)];
            Objects.requireNonNull(event.getBlock().getLocation().getWorld()).spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE,location,100);
            event.getPlayer().playSound(playLoc,Sound.ITEM_CHORUS_FRUIT_TELEPORT,SoundCategory.MASTER,0.9f,0.4f);
            Objects.requireNonNull(event.getPlayer().getLocation().getWorld()).spawnEntity(location, entityType);
        }, 80L);


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("donate")) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.RED + "Please donate if you can - " + "https://www.buymeacoffee.com/tangobee");
            }
        }

        return true;
    }
}
