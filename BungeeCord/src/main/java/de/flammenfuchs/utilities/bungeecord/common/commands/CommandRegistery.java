package de.flammenfuchs.utilities.bungeecord.common.commands;

import com.google.common.base.Preconditions;
import de.flammenfuchs.utilities.bungeecord.common.commands.model.BungeeCommand;
import de.flammenfuchs.utilities.platform.common.command.PlatformCommandRegistery;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.List;

public class CommandRegistery extends PlatformCommandRegistery<BungeeCommand, Plugin, Listener> {

    public CommandRegistery(Plugin plugin) {
        super(plugin, BungeeCommand.class, Listener.class);
    }

    public CommandRegistery(Plugin plugin, String defaultPermissionMessage) {
        super(plugin, BungeeCommand.class, Listener.class, defaultPermissionMessage);
    }

    @Override
    public void registerListener(Listener listener) {
        ProxyServer.getInstance().getPluginManager().registerListener(getPlugin(), listener);
    }

    @Override
    public void setDefaultPermissionMessages(String message) {
        getCommands().forEach(command -> setDefaultPermissionMessage(command, message));
    }

    @Override
    public void setDefaultPermissionMessage(BungeeCommand bungeeCommand, String message) {
        if (bungeeCommand.getPermissionMessage() == null) {
            bungeeCommand.setPermissionMessage(message);
        }
    }

    /**
     * Set default permission message
     * NOTE: It only affects already registered commands!
     *
     * @param message default permission message
     */
}
