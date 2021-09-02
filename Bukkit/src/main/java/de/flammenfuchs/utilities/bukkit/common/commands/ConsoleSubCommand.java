package de.flammenfuchs.utilities.bukkit.common.commands;

import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import org.bukkit.command.CommandSender;

public class ConsoleSubCommand extends SubCommand<CommandSender> {

    public static ConsoleSubCommand name(String name) {return new ConsoleSubCommand(name);}

    public ConsoleSubCommand(String name) {
        super(name);
    }
}
