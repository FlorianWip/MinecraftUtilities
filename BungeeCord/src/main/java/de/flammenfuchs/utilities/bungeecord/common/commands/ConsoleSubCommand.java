package de.flammenfuchs.utilities.bungeecord.common.commands;


import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import net.md_5.bungee.api.CommandSender;

public class ConsoleSubCommand extends SubCommand<CommandSender> {

    public static ConsoleSubCommand name(String name) {return new ConsoleSubCommand(name);}

    public ConsoleSubCommand(String name) {
        super(name);
    }
}
