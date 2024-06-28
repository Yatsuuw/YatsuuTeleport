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
            sender.sendMessage(ChatColor.RED + no_perm);

        } else {

            sender.sendMessage(ChatColor.GREEN + plugin.getConfig().getString("messages.commands"));

            plugin.getDescription().getCommands().forEach((cmd, desc) -> sender.sendMessage(ChatColor.YELLOW + "/" + cmd + ": " + desc.get("description") ));

        }

        return true;

    }

}
