package de.flammenfuchs.utilities.platform.common.text;

public class MessageUtils {
    public static String PREFIX = "§8[§b§lServer§8] §7";
    public static String PLAYER_NOT_ONLINE = "§cDieser Spieler ist nicht online!";
    public static String NO_PERM = "§cDafür hast du §4keine §cRechte!";
    public static String TOP_LEFT_FRAME = "§8▛▀▀▀▀▀▀▀▀▀▀ §r";
    public static String TOP_RIGHT_FRAME = " §8▀▀▀▀▀▀▀▀▀▀▜§r";
    public static String DOWN_LEFT_FRAME = "§8▙▄▄▄▄▄▄▄▄▄▄ §r";
    public static String DOWN_RIGHT_FRAME = "§8 ▄▄▄▄▄▄▄▄▄▄▟§r";
    public static String ARROW_RIGHT = "»";
    public static String ARROW_LEFT = "«";
    public static String DOT = "•";

    public static String topFrame(String middleText) {
        return TOP_LEFT_FRAME + middleText + TOP_RIGHT_FRAME;
    }

    public static String downFrame(String middleText) {
        return DOWN_LEFT_FRAME + middleText + DOWN_RIGHT_FRAME;
    }
}
