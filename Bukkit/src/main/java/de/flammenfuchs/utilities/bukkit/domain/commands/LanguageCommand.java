package de.flammenfuchs.utilities.bukkit.domain.commands;

import de.flammenfuchs.utilities.bukkit.common.commands.ArgsUtil;
import de.flammenfuchs.utilities.bukkit.common.commands.ConsoleCommand;
import de.flammenfuchs.utilities.bukkit.common.commands.ConsoleSubCommand;
import de.flammenfuchs.utilities.platform.common.command.HelpPattern;
import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import de.flammenfuchs.utilities.platform.common.language.LanguageProvider;
import de.flammenfuchs.utilities.platform.common.text.ChatColor;
import de.flammenfuchs.utilities.platform.common.text.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LanguageCommand extends ConsoleCommand {
    public LanguageCommand() {
        super("language");
        this.setAliases("lang");
        this.setPermission("minecraftutilities.language");
        this.setDescription("Language Management Command");
        this.setUsage("Type /lang for usage");
        this.addSubCommand(listLang());
        this.addSubCommand(copyLang());
        this.addSubCommand(deleteLang());
        this.addSubCommand(editLang());
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            ((Player) sender).spigot().sendMessage(HelpPattern.build(this, ChatColor.AQUA));
            return;
        }
        sender.sendMessage(HelpPattern.rawBuild(this, ChatColor.AQUA));
    }

    public SubCommand<CommandSender> listLang() {
        return ConsoleSubCommand.name("list").description("List all Languages").executor((sender, args) -> {
            if (LanguageProvider.getLang() == null) {
                sender.sendMessage("LanguageProvider not available!");
                return true;
            }
            sender.sendMessage("§cAll languages:");
            for (String lang : LanguageProvider.getLang().getCachedLanguages().keySet()) {
                sender.sendMessage( "  §8" + MessageUtils.ARROW_RIGHT + " §e" + lang);
            }
            return true;
        });
    }

    public SubCommand<CommandSender> copyLang() {
        return ConsoleSubCommand.name("copy").aliases("cp").description("Copy a language").syntax("<lang> <new lang>").executor((sender, args) -> {
            try {
                LanguageProvider.getLang().copyLanguage(args[1], args[2]);
                sender.sendMessage("§aSuccessfully copied language!");
            } catch (IllegalArgumentException ex) {
                sender.sendMessage("§4The given language does not exist!");
                return true;
            } catch (ArrayIndexOutOfBoundsException ex) {
                return false;
            } catch (NullPointerException ex) {
                sender.sendMessage("§4LanguageProvider not available!");
                return true;
            }
            return true;
        });
    }

    public SubCommand<CommandSender> deleteLang() {
        return ConsoleSubCommand.name("delete").aliases("rm").description("Delete a Language").syntax("<lang>").executor((sender, args) -> {
            try {
                LanguageProvider.getLang().deleteLanguage(args[1]);
                sender.sendMessage("§cSuccessfully deleted language!");
            } catch (IllegalArgumentException ex) {
                sender.sendMessage("§4The given language does not exist!");
                return true;
            } catch (ArrayIndexOutOfBoundsException ex) {
                return false;
            } catch (NullPointerException ex) {
                sender.sendMessage("§4LanguageProvider not available!");
                return true;
            }
            return true;
        });
    }

    public SubCommand<CommandSender> editLang() {
        return ConsoleSubCommand.name("edit").description("Edit a Text").syntax("<lang> <key> <message>").executor((sender, args) -> {
            try {
                LanguageProvider.getLang().updateText(args[1], args[2], ArgsUtil.getAsString(3, args));
                sender.sendMessage("§aSuccessfully updated language!");
            } catch (IllegalArgumentException ex) {
                sender.sendMessage("§4The given language does not exist!");
                return true;
            } catch (IndexOutOfBoundsException ex) {
                return false;
            } catch (NullPointerException ex) {
                sender.sendMessage("§4LanguageProvider not available!");
                return true;
            }
            return true;
        });
    }
}
