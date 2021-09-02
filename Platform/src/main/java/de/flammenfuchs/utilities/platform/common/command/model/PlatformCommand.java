package de.flammenfuchs.utilities.platform.common.command.model;

import java.util.Map;

public interface PlatformCommand<T, U> {

    String getName();
    Map<String, SubCommand<T>> getSubCommands();
    void register(U u);
    void unregister();

}
