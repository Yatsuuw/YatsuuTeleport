package fr.yatsuu.yatsuuteleport.commands;

import fr.yatsuu.yatsuuteleport.YatsuuTeleport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CommandTeleport implements CommandExecutor {

    private final YatsuuTeleport plugin;

    public CommandTeleport(YatsuuTeleport plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @Nonnull String[] args) {

        if (!sender.hasPermission("yatsuuteleport.command.teleport")) {

            String no_perm = Objects.requireNonNull(plugin.getConfig().getString("messages.no_perm")).replace("{permission}", "yatsuuteleport.command.teleport");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', no_perm));

        } else {

            if (args.length > 1) {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.usage_teleport"))));
                return true;

            }

            Player target;

            if (args.length == 1) {

                target = Bukkit.getPlayer(args[0]);

                if (target == null) {

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.player_not_found"))));
                    return true;

                }

            } else if (sender instanceof Player) {

                target = (Player) sender;

            } else {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.not_player_send_command"))));
                return true;

            }

            // Get coordinates and direction from config
            double x = plugin.getConfig().getDouble("teleport_coordinates.x");
            double y = plugin.getConfig().getDouble("teleport_coordinates.y");
            double z = plugin.getConfig().getDouble("teleport_coordinates.z");

            float yaw = (float) plugin.getConfig().getDouble("teleport_coordinates.yaw");
            float pitch = (float) plugin.getConfig().getDouble("teleport_coordinates.pitch");

            String worldName = plugin.getConfig().getString("teleport_coordinates.world");

            // Get the world object
            assert worldName != null;
            Location location = new Location(Bukkit.getWorld(worldName), x, y , z, yaw, pitch);

            // Teleport the player
            target.teleport(location);

            if (sender != target) {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("messages.sender_teleport")).replace("{player}", target.getName()))));
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.target_teleport"))));

            } else {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.target_teleport"))));

            }

        }

        return true;

    }

}
