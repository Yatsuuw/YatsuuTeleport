package fr.yatsuu.yatsuuteleport;

import fr.yatsuu.yatsuuteleport.commands.*;
import fr.yatsuu.yatsuuteleport.events.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class YatsuuTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("YatsuuTeleport are ON.");

        // Save the default config if it doesn't exist
        saveDefaultConfig();

        // Register the event listener
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        // Register commands
        Objects.requireNonNull(getCommand("setteleport")).setExecutor(new SetTeleportCommand(this));
        Objects.requireNonNull(getCommand("teleport")).setExecutor(new TeleportCommand(this));
        Objects.requireNonNull(getCommand("ytload")).setExecutor(new ytLoadCommand(this));
        Objects.requireNonNull(getCommand("ythelp")).setExecutor(new ytHelpCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("YatsuuTeleport are OFF.");

    }

}
