package fr.yatsuu.yatsuuteleport.commands;

import fr.yatsuu.yatsuuteleport.YatsuuTeleport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class HelpCommand implements CommandExecutor {

    private final YatsuuTeleport plugin;

    public HelpCommand(YatsuuTeleport plugin) {

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, @NonNull Command command, @NonNull String label, @Nonnull String[] args) {

        if (!sender.hasPermission("yatsuuteleport.command.help")) {

            String no_perm = Objects.requireNonNull(plugin.getConfig().getString("messages.no_perm")).replace("{permission}", "yatsuuteleport.command.help");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', no_perm));

        } else {

            StringBuilder response = new StringBuilder();

            response.append(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.commands")))).append("\n");

            String commandFormat = plugin.getConfig().getString("messages.command_format");

            plugin.getDescription().getCommands().forEach((cmd, desc) -> {

                assert commandFormat != null;
                String formattedCommand = commandFormat.replace("{command}", cmd).replace("{description}", (CharSequence) desc.get("description"));

                response.append(ChatColor.translateAlternateColorCodes('&', formattedCommand)).append("\n");

            });

            sender.sendMessage(response.toString());

        }

        return true;

    }

}
