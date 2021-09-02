package de.flammenfuchs.utilities.platform.common.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatColor {

    BLACK("§0", 0),
    DARK_BLUE("§1", 1),
    DARK_GREEN("§2", 2),
    DARK_AQUA("§3", 3),
    DARK_RED("§4", 4),
    DARK_PURPLE("§5", 5),
    GOLD("§6", 6),
    GRAY("§7", 7),
    DARK_GRAY("§8", 8),
    BLUE("§9", 9),
    GREEN("§a", 10),
    AQUA("§b", 11),
    RED("§c", 12),
    LIGHT_PURPLE("§d", 13),
    YELLOW("§e", 14),
    WHITE("§f", 15),
    MAGIC("§k", 16),
    BOLD("§l", 17),
    STRIKETHROUGH("§m", 18),
    UNDERLINE("§n", 19),
    ITALIC("§o", 20),
    RESET("§r", 21);

    private final String colorCode;
    private final int colorCodeID;

    @Override
    public String toString() {
        return colorCode;
    }
}
