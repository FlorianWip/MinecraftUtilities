package de.flammenfuchs.utilities.platform.common.command;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.StringJoiner;

public class PlatformArgsUtil {


    public static Optional<Integer> getInt(int position, String[] args) {
        if (args.length > position) {
            try {
                int i = Integer.parseInt(args[position]);
                return Optional.of(i);
            } catch (Exception ex) {
            }
        }
        return Optional.empty();
    }

    public static Optional<Long> getLong(int position, String[] args) {
        if (args.length > position) {
            try {
                long i = Long.parseLong(args[position]);
                return Optional.of(i);
            } catch (Exception ex) {
            }
        }
        return Optional.empty();
    }

    public static Optional<Double> getDouble(int position, String[] args) {
        if (args.length > position) {
            try {
                double i = Double.parseDouble(args[position]);
                return Optional.of(i);
            } catch (Exception ex) {
            }
        }
        return Optional.empty();
    }

    @Nullable
    public static String getAsString(int beginIndex, int endIndex, @NonNull String[] args) throws IndexOutOfBoundsException{
        if (endIndex < beginIndex) {
            int temp = endIndex;
            endIndex = beginIndex;
            beginIndex = temp;
        }
        try {
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int i = beginIndex; i < endIndex; i++) {
                stringJoiner.add(args[i]);
            }
            return stringJoiner.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw exception;
        }
    }

    @Nullable
    public static String getAsString(int beginIndex, @NonNull String[] args) throws IndexOutOfBoundsException {
        try {
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (int i = beginIndex; i < args.length; i++) {
                stringJoiner.add(args[i]);
            }
            return stringJoiner.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw exception;
        }
    }

    public static String getAsString(String[] args) {
        return String.join(" ", args);
    }
}
