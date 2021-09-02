package de.flammenfuchs.utilities.platform.common.command.model;

public interface SubCommandExecutor<T> {

    boolean execute(T t, String[] args);
}
