package de.flammenfuchs.utilities.bungeecord.common.commands;

import de.flammenfuchs.utilities.bungeecord.common.commands.model.BungeeCommand;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public abstract class PlayerCommand extends BungeeCommand<ProxiedPlayer> {
    protected PlayerCommand(String name, String permission, String... aliases) {
        super(name, permission, ProxiedPlayer.class, aliases);
    }

    @Override
    public void send(ProxiedPlayer player, String[] args) {
        onCommand(player, args);
    }

    public abstract void onCommand(ProxiedPlayer player, String[] args);


}
