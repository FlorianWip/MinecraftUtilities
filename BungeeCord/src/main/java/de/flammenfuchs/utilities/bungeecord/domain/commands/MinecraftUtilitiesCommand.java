package de.flammenfuchs.utilities.bungeecord.domain.commands;

import de.flammenfuchs.utilities.bungeecord.common.commands.ConsoleCommand;
import net.md_5.bungee.api.CommandSender;

public class MinecraftUtilitiesCommand extends ConsoleCommand {
    protected MinecraftUtilitiesCommand() {
        super("minecraftutilities", null, "mcutils", "bmcutils", "bungeeminecraftutils");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
    }
}
