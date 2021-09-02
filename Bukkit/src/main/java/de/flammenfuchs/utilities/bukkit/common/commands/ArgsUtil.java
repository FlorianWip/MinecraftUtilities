package de.flammenfuchs.utilities.bukkit.common.commands;

import de.flammenfuchs.utilities.bukkit.common.items.ItemBuilder;
import de.flammenfuchs.utilities.platform.common.command.PlatformArgsUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class ArgsUtil extends PlatformArgsUtil {

    @Nullable
    public static Player getPlayer(int position, String[] args) {
        if (args.length > position) {
            Player player = Bukkit.getPlayer(args[position]);
            return player;
        }
        return null;
    }

}
