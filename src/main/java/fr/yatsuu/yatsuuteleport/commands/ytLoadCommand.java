package fr.yatsuu.yatsuuteleport.commands;

import fr.yatsuu.yatsuuteleport.YatsuuTeleport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ytLoadCommand implements CommandExecutor {

    private final YatsuuTeleport plugin;

    public ytLoadCommand(YatsuuTeleport plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, @Nonnull String[] args) {

        if (!sender.hasPermission("yatsuuteleport.command.ytload")) {

            String no_perm = Objects.requireNonNull(plugin.getConfig().getString("messages.no_perm")).replace("{permission}", "yatsuuteleport.command.ytload");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', no_perm));

        } else {

            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.success_reload"))));

        }

        return true;

    }

}
