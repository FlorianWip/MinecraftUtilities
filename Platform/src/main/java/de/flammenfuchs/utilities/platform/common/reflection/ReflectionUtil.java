package de.flammenfuchs.utilities.platform.common.reflection;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.stream.Stream;

public class ReflectionUtil {
    public static List<Class<?>> getClassesFromPackage(Object object) {
        return getClassesFromPackage(object, null);
    }

    public static boolean hasNoParamConstructor(Class clazz) {
        return hasNoParamConstructor(clazz, true);
    }

    public static boolean hasNoParamConstructor(Class clazz, boolean onlyPublic) {
        Constructor[] constructors = onlyPublic ? clazz.getConstructors() : clazz.getDeclaredConstructors();
        return Stream.of(constructors).anyMatch((constructor) -> constructor.getParameterCount() == 0);
    }

    public static List<Class<?>> getClassesFromPackage(Object object, Class... filters) {
        List<Class<?>> classes = Lists.newArrayList();
        ClassLoader loader = object.getClass().getClassLoader();
        try {
            for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
                if (info.getName().startsWith(object.getClass().getPackage().getName().concat("."))) {
                    Class clazz = info.load();
                    if (filters == null || filters.length == 0) {
                        classes.add(clazz);
                    } else {
                        boolean success = false;
                        for (Class<?> filter : filters) {
                            if (filter.isAssignableFrom(clazz)) {
                                success = true;
                            }
                        }
                        if (success) {
                            classes.add(clazz);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classes;
    }
}
