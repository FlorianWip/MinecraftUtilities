package de.flammenfuchs.utilities.bukkit.common.commands;

import de.flammenfuchs.utilities.bukkit.common.commands.model.BukkitCommand;
import de.flammenfuchs.utilities.platform.common.command.PlatformCommandRegistery;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegistery extends PlatformCommandRegistery<BukkitCommand, JavaPlugin, Listener> {

    public CommandRegistery(JavaPlugin plugin) {
        super(plugin, BukkitCommand.class, Listener.class);
    }

    public CommandRegistery(JavaPlugin plugin, String defaultPermissionMessage) {
        super(plugin, BukkitCommand.class, Listener.class, defaultPermissionMessage);
    }

    @Override
    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getPlugin());
        getPlugin().getLogger().info("Registered Listener " + listener.getClass().getSimpleName());
    }

    @Override
    public void setDefaultPermissionMessages(String message) {
        getCommands().forEach(bukkitCommand -> setDefaultPermissionMessage(bukkitCommand, message));
    }

    @Override
    public void setDefaultPermissionMessage(BukkitCommand bukkitCommand, String message) {
        if (bukkitCommand.getPermissionMessage() == null) {
            bukkitCommand.setPermissionMessage(message);
        }
    }
}
