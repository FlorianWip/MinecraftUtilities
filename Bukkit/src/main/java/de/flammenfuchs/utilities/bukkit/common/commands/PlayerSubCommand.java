package de.flammenfuchs.utilities.bukkit.common.commands;

import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import org.bukkit.entity.Player;

public class PlayerSubCommand extends SubCommand<Player> {

    public static PlayerSubCommand name(String name) {return new PlayerSubCommand(name);}

    public PlayerSubCommand(String name) {
        super(name);
    }
}
