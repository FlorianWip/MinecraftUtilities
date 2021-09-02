package de.flammenfuchs.utilities.bukkit.domain.commands;

import de.flammenfuchs.utilities.bukkit.common.commands.ConsoleCommand;
import de.flammenfuchs.utilities.platform.common.text.MessageUtils;
import org.bukkit.command.CommandSender;

public class MinecraftUtilitiesCommand extends ConsoleCommand {
    public MinecraftUtilitiesCommand() {
        super("minecraftutilities");
        this.setAliases("mcutils");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(MessageUtils.topFrame("§aMC-Utils"));
        sender.sendMessage("§7This server uses §cMinecraftUtilities");
        sender.sendMessage(" ");
        sender.sendMessage(" §8" + MessageUtils.ARROW_RIGHT + " §aVersion " + MessageUtils.ARROW_RIGHT
                + " §e" + getPlugin().getDescription().getVersion());
        sender.sendMessage(" §8" + MessageUtils.ARROW_RIGHT + " §aAuthor " + MessageUtils.ARROW_RIGHT +
                " §eFlammenfuchs_YT");
        sender.sendMessage( " ");
        sender.sendMessage(MessageUtils.downFrame("§aMC-Utils"));
    }
}
