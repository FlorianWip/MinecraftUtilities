package de.flammenfuchs.utilities.bungeecord.domain;

import de.flammenfuchs.utilities.bungeecord.common.commands.CommandRegistery;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {

    @Getter private static BungeeMain instance;

    @Getter private CommandRegistery commandRegistery;

    @Override
    public void onEnable() {
        instance = this;
        commandRegistery = new CommandRegistery(this);
        commandRegistery.registerCommands();
    }

    @Override
    public void onDisable() {
        commandRegistery.unregisterCommands();
    }
}
