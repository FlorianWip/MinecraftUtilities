package de.flammenfuchs.utilities.bungeecord.common.commands;


import de.flammenfuchs.utilities.bungeecord.common.commands.model.BungeeCommand;
import net.md_5.bungee.api.CommandSender;

public abstract class ConsoleCommand extends BungeeCommand<CommandSender> {

    protected ConsoleCommand(String name, String permission, String... aliases) {
        super(name, permission, CommandSender.class, aliases);
    }

    @Override
    public void send(CommandSender sender, String[] args) {
        onCommand(sender, args);
    }

    public abstract void onCommand(CommandSender sender, String[] args);
}
