package de.flammenfuchs.utilities.bungeecord.common.commands;

import de.flammenfuchs.utilities.platform.common.command.PlatformArgsUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.Nullable;

public class ArgsUtil extends PlatformArgsUtil {


    @Nullable
    public static ProxiedPlayer getPlayer(int position, String[] args) {
        if (args.length > position) {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[position]);
            return player;
        }
        return null;
    }

}
