package de.flammenfuchs.utilities.bukkit.common.commands;

import de.flammenfuchs.utilities.bukkit.common.commands.model.BukkitCommand;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends BukkitCommand<Player> {
    public PlayerCommand(String name) {
        super(name, Player.class);
    }

    @Override
    public void send(Player player, String[] args) {
        onCommand(player, args);
    }

    public abstract void onCommand(Player player, String[] args);


}
