package de.flammenfuchs.utilities.bukkit.domain;

import de.flammenfuchs.utilities.bukkit.common.commands.CommandRegistery;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitMain extends JavaPlugin {

    @Getter private static CommandRegistery commandRegistery;

    @Override
    public void onEnable() {
        commandRegistery = new CommandRegistery(this);

        commandRegistery.registerCommands();
    }

    @Override
    public void onDisable() {
        commandRegistery.unregisterCommands();
    }
}
