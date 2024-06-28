package fr.yatsuu.yatsuuteleport.events;

import fr.yatsuu.yatsuuteleport.YatsuuTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoinListener implements Listener {

    private final YatsuuTeleport plugin;

    public PlayerJoinListener(YatsuuTeleport plugin) {

        this.plugin = plugin;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if (!Objects.requireNonNull(plugin.getConfig().getString("status")).equalsIgnoreCase("on")) {
            return;
        }

        Player player = event.getPlayer();

        // Get coordinates from config
        double x = plugin.getConfig().getDouble("teleport_coordinates.x");
        double y = plugin.getConfig().getDouble("teleport_coordinates.y");
        double z = plugin.getConfig().getDouble("teleport_coordinates.z");

        float yaw = (float) plugin.getConfig().getDouble("teleport_coordinates.yaw");
        float pitch = (float) plugin.getConfig().getDouble("teleport_coordinates.pitch");

        String worldName = plugin.getConfig().getString("teleport_coordinates.world");

        // Get the world object
        assert worldName != null;
        Location location = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);

        // Teleport the player
        player.teleport(location);

    }

}
