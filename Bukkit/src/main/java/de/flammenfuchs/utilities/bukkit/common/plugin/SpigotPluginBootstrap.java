package de.flammenfuchs.utilities.bukkit.common.plugin;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpigotPluginBootstrap {

    private boolean autoCommand = true;
    private boolean autoListener = true;
    private boolean autoConfig = true;
}
