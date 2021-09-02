package de.flammenfuchs.utilities.bukkit.common.plugin;

import de.flammenfuchs.utilities.bukkit.common.commands.CommandRegistery;
import de.flammenfuchs.utilities.platform.common.configuration.json.JsonConfiguration;
import de.flammenfuchs.utilities.platform.common.configuration.json.JsonConfigurationProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SpigotPlugin extends JavaPlugin {

    private static SpigotPlugin plugin;

    private CommandRegistery commandRegistery;
    private Map<String, JsonConfiguration> jsonConfigurations = new HashMap<>();
    private Map<String, Class<? extends JsonConfiguration>> jsonCfgClasses = new HashMap<>();

    public static SpigotPlugin get() {return plugin;}

    public abstract SpigotPluginBootstrap initialize();

    public abstract void enable();

    public abstract void disable();

    @Override
    public void onEnable() {
        plugin = this;
        commandRegistery = new CommandRegistery(this);
        getLogger().info("Initialize " + getName() + " v" + getVersion());
        SpigotPluginBootstrap bootstrap = initialize();
        if (bootstrap.isAutoCommand()) {
            getLogger().info("Register commands...");
            commandRegistery.registerCommands();
        }
        if (bootstrap.isAutoListener()) {
            getLogger().info("Register listeners...");
            commandRegistery.registerListeners();
        }
        if (bootstrap.isAutoConfig()) {
            getLogger().info("Load configs...");
            for (Map.Entry<String, Class<? extends JsonConfiguration>> entry : jsonCfgClasses.entrySet()) {
                JsonConfiguration configuration = JsonConfigurationProvider.loadConfiguration(entry.getValue());
                this.jsonConfigurations.put(entry.getKey(), configuration);
            }
        }
        getLogger().info("Enable...");
        enable();
        getLogger().info("Initialization done.");
    }

    public void onDisable() {
        getLogger().info("Disable " + getName() + " v" + getVersion());
        disable();
        getLogger().info("Unregister commands...");
        this.commandRegistery.unregisterCommands();
        getLogger().info("Save configurations...");
        for (JsonConfiguration configuration : jsonConfigurations.values()) {
            JsonConfigurationProvider.saveConfiguration(configuration);
        }
        getLogger().info("Disabling done...");
    }

    public String getVersion() {
        return getDescription().getVersion();
    }

    public void addJsonConfig(String internalName, Class<? extends JsonConfiguration> configuration) {
        jsonCfgClasses.put(internalName.toLowerCase(), configuration);
    }

    @Nullable
    public <T extends JsonConfiguration> T getConfig(String name) {
        return (T) jsonConfigurations.get(name.toLowerCase());
    }
}
