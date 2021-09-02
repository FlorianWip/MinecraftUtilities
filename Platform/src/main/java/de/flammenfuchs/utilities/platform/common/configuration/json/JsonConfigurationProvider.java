package de.flammenfuchs.utilities.platform.common.configuration.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import de.flammenfuchs.utilities.platform.common.reflection.ReflectionUtil;
import lombok.SneakyThrows;

import javax.naming.ConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class JsonConfigurationProvider {

    @SneakyThrows
    public static <T extends JsonConfiguration> T loadConfiguration(Class<? extends JsonConfiguration> configurationClass) {
        if (!ReflectionUtil.hasNoParamConstructor(configurationClass)) {
            throw new ConfigurationException("ConfigurationClass needs no parameter constructor");
        }
        JsonConfiguration configuration = configurationClass.getConstructor().newInstance();
        File file = new File(configuration.getDirectory(), configuration.getFilename());
        File directory = new File(configuration.getDirectory());

        if (file.exists()) {
            return new GsonBuilder().disableHtmlEscaping().create().fromJson(new JsonReader(new FileReader(file)), configurationClass);
        } else {
            directory.mkdirs();
            file.createNewFile();
        }


        return (T) configuration;
    }

    @SneakyThrows
    public static void saveConfiguration(JsonConfiguration configuration) {
        File file = new File(configuration.getDirectory(), configuration.getFilename());
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        Writer fileWriter = new FileWriter(file.getPath());
        gson.toJson(configuration, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }
}
