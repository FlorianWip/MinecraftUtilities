package de.flammenfuchs.utilities.platform.common.command;

import de.flammenfuchs.utilities.platform.common.command.model.IgnoreListener;
import de.flammenfuchs.utilities.platform.common.command.model.PlatformCommand;
import de.flammenfuchs.utilities.platform.common.reflection.ReflectionUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class PlatformCommandRegistery<T extends PlatformCommand, U, V> {

    private final U plugin;
    private final Class<? extends T> commandClass;
    private final Class<? extends V> listenerClass;
    private String defaultPermissionMessage;

    public PlatformCommandRegistery(U plugin, Class<? extends T> commandClass, Class<? extends V> listenerClass) {
        this.plugin = plugin;
        this.commandClass = commandClass;
        this.listenerClass = listenerClass;

    }

    public PlatformCommandRegistery(U plugin, Class<? extends T> commandClass, Class<? extends V> listenerClass, String defaultPermissionMessage) {
        this.plugin = plugin;
        this.commandClass = commandClass;
        this.listenerClass = listenerClass;
        this.defaultPermissionMessage = defaultPermissionMessage;
    }

    private List<T> commands = new ArrayList<>();

    public void registerCommand(T t) {
        t.register(plugin);
        commands.add(t);
        if (defaultPermissionMessage != null) {
            setDefaultPermissionMessage(t, defaultPermissionMessage);
        }
    }

    ;

    public void registerCommands() {
        List<Class<?>> classes = ReflectionUtil.getClassesFromPackage(plugin, commandClass);
        for (Class clazz : classes) {
            if (ReflectionUtil.hasNoParamConstructor(clazz)) {
                try {
                    T command = (T) clazz.getConstructor().newInstance();
                    this.registerCommand(command);
                    System.out.println("[INFO] Register command /" + command.getName());
                } catch (Exception ex) {
                    System.out.println("[SEVERE] Failed to register command " + clazz.getSimpleName() + " " + ex.getClass().getSimpleName()
                            + ": " + ex.getMessage());
                }
            }
        }
    }

    public void unregisterCommands() {
        commands.forEach(command -> command.unregister());
    }


    @SneakyThrows
    public void registerListeners() {
        List<Class<?>> classes = ReflectionUtil.getClassesFromPackage(plugin, listenerClass);
        for (Class clazz : classes) {
            if (ReflectionUtil.hasNoParamConstructor(clazz)) {
                if (!clazz.isAnnotationPresent(IgnoreListener.class)) {
                    V v = (V) clazz.getConstructor().newInstance();
                    registerListener(v);
                }
            }
        }
    }

    public abstract void registerListener(V v);

    public abstract void setDefaultPermissionMessages(String message);

    public abstract void setDefaultPermissionMessage(T t, String message);
}
