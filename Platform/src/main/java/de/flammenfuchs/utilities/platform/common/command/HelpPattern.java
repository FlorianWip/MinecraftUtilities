package de.flammenfuchs.utilities.platform.common.command;

import de.flammenfuchs.utilities.platform.common.command.model.PlatformCommand;
import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import de.flammenfuchs.utilities.platform.common.text.ChatColor;
import de.flammenfuchs.utilities.platform.common.text.MessageUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.StringJoiner;

public class HelpPattern {

    public static TextComponent[] build(PlatformCommand command, ChatColor color) {
        TextComponent[] textComponents = new TextComponent[4+command.getSubCommands().size()];
        String capitalized = command.getName().substring(0, 1).toUpperCase() + command.getName().substring(1);
        textComponents[0] = new TextComponent(TextComponent.fromLegacyText(MessageUtils.topFrame(color + "§l" + capitalized) + "\n"));
        textComponents[1] = new TextComponent(TextComponent.fromLegacyText("  §8" + MessageUtils.ARROW_RIGHT + " " + color + capitalized + "\n"));
        textComponents[2] = new TextComponent(" \n");
        int count = 0;
        for (Object subCommandObject : command.getSubCommands().values()) {
            SubCommand subCommand = (SubCommand) subCommandObject;
            String syntax = subCommand.getSyntax() != null ? " " + subCommand.getSyntax() + " " : "";
            TextComponent textComponent = new TextComponent(TextComponent.fromLegacyText(" §8" + MessageUtils.ARROW_RIGHT
                    + " /" + color + command.getName() + " " + subCommand.getName() + syntax + "\n"));
            if (subCommand.getDescription() != null) {
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7" + subCommand.getDescription()).create()));
            }
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + command.getName() + " " + subCommand.getName() + " "));
            textComponents[3+count] = textComponent;
            count++;
        }
        textComponents[3+count] = new TextComponent(TextComponent.fromLegacyText(MessageUtils.downFrame(color + "§l" + capitalized)));

        return textComponents;
    }

    public static String rawBuild(PlatformCommand command, ChatColor color) {
        return TextComponent.toLegacyText(build(command, color));
    }

    public static String flatBuild(PlatformCommand command, ChatColor color) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Object subCommandObject : command.getSubCommands().values()) {
            SubCommand subCommand = (SubCommand) subCommandObject;
            String syntax = subCommand.getSyntax() != null ? " " + subCommand.getSyntax() : " ";
            String description = subCommand.getDescription() != null ? " §8| §7" + subCommand.getDescription() : "";
            stringJoiner.add(" §8" + MessageUtils.ARROW_RIGHT + "/" + color + command.getName() + " " + subCommand.getName() + syntax + description);
         }
        return stringJoiner.toString();
    }
}
