package de.flammenfuchs.utilities.bungeecord.common.commands.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import de.flammenfuchs.utilities.platform.common.command.model.PlatformCommand;
import de.flammenfuchs.utilities.platform.common.command.model.SubCommand;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BungeeCommand<T> extends Command implements PlatformCommand<T, Plugin> {

    @Getter @Setter
    private static String senderNotValid = "Du kannst diesen Befehl nicht ausführen!";

    private final String name;
    private final Class<? extends T> senderClass;

    @Setter private String description;
    @Setter private String usage;

    private Map<String, SubCommand<T>> subCommands = new HashMap<>();
    private Plugin plugin;

    protected BungeeCommand(String name, String permission, Class<? extends T> senderClass, String... aliases) {
        super(name, permission, aliases);
        this.name = name;
        this.senderClass = senderClass;
    }

    public abstract void send(T t, String[] args);

    /**
     * @return immutable map of subCommands
     */
    public Map<String, SubCommand<T>> getSubCommands() {
        return ImmutableMap.copyOf(subCommands);
    }

    public void addSubCommand(@NonNull SubCommand<T> subCommand) throws IllegalStateException, IllegalArgumentException {
        Preconditions.checkNotNull(subCommand.getExecutor());
        Preconditions.checkState(subCommands.get(subCommand.getName().toLowerCase()) == null,
                "SubCommand already registered");
        subCommands.put(subCommand.getName().toLowerCase(), subCommand);
        if (subCommand.getAliases() != null) {
            for (String alias : subCommand.getAliases()) {
                if (!subCommands.containsKey(alias.toLowerCase())) {
                    subCommands.put(alias.toLowerCase(), subCommand);
                }
            }
        }
    }

    public void register(Plugin plugin) {
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, this);
    }

    public void unregister() {
        ProxyServer.getInstance().getPluginManager().unregisterCommand(this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!senderClass.isAssignableFrom(commandSender.getClass())) {
            commandSender.sendMessage(senderNotValid);
            return;
        }
        boolean executeMainSend = true;
        if (!subCommands.isEmpty() && strings.length > 0) {
            SubCommand<T> subCommand = subCommands.get(strings[0].toLowerCase());
            if (subCommand != null) {
                if (subCommand.getPermission() != null) {
                    if (!commandSender.hasPermission(subCommand.getPermission())) {
                        commandSender.sendMessage(getPermissionMessage());
                        return;
                    }
                }
                executeMainSend = !subCommand.getExecutor().execute((T) commandSender, strings);
            }
        }
        if (executeMainSend) {
            send((T) commandSender, strings);
        }
        return;
    }

    public void setPermissionMessage(String string) {
        super.setPermissionMessage(string);
    }
}
