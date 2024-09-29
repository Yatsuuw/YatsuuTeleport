package fr.yatsuu.yatsuuteleport.commands;

import fr.yatsuu.yatsuuteleport.YatsuuTeleport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SetTeleportCommand implements CommandExecutor {

    private final YatsuuTeleport plugin;

    public SetTeleportCommand(YatsuuTeleport plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @Nonnull String[] args) {

        if (!sender.hasPermission("yatsuuteleport.command.setteleport")) {

            String no_perm = Objects.requireNonNull(plugin.getConfig().getString("messages.no_perm")).replace("{permission}", "yatsuuteleport.command.setteleport");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', no_perm));

        } else {

            if (!(sender instanceof Player)) {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.not_player_send_command"))));
                return true;

            }

            Player player = (Player) sender;
            Location loc = player.getLocation();

            // Save the current location and orientation to the config
            plugin.getConfig().set("teleport_coordinates.world", Objects.requireNonNull(loc.getWorld()).getName());

            plugin.getConfig().set("teleport_coordinates.x", loc.getX());
            plugin.getConfig().set("teleport_coordinates.y", loc.getY());
            plugin.getConfig().set("teleport_coordinates.z", loc.getZ());

            plugin.getConfig().set("teleport_coordinates.yaw", loc.getYaw());
            plugin.getConfig().set("teleport_coordinates.pitch", loc.getPitch());
            plugin.saveConfig();

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.teleport_point"))));

        }

        return true;

    }
}
