package de.flammenfuchs.utilities.bungeecord.common.commands;

import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PlayerSubCommand extends SubCommand<ProxiedPlayer> {

    public static PlayerSubCommand name(String name) {return new PlayerSubCommand(name);}

    public PlayerSubCommand(String name) {
        super(name);
    }
}
