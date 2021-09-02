package de.flammenfuchs.utilities.platform.common.language.impl;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import de.flammenfuchs.utilities.platform.common.language.LanguageProvider;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleLanguageProvider extends LanguageProvider {

    private Map<String, String> playerLanguages = new HashMap<>();
    private File file;

    public SimpleLanguageProvider(String directory, String fileName, String fallbackLanguage) {
        super(directory, fileName, fallbackLanguage);
        init();
    }

    public SimpleLanguageProvider() {
        super("plugins/Language", "language.json", "en");
        init();
    }

    @SneakyThrows
    private void init() {
        file = new File(getDirectory(), "players.json");
        if (file.exists()) {
            playerLanguages = new GsonBuilder().disableHtmlEscaping().create().fromJson(new JsonReader(new FileReader(file)), HashMap.class);
        } else {
            file.createNewFile();
        }
    }

    @Override
    public void setPlayerLanguage(UUID uuid, String newLanguage) throws IllegalArgumentException {
        Preconditions.checkArgument(getCachedLanguages().containsKey(newLanguage.toLowerCase()));
        playerLanguages.put(uuid.toString(), newLanguage);
    }

    @Override
    public String getPlayerLanguage(UUID uuid) {
        if (!playerLanguages.containsKey(uuid.toString())) {
            setPlayerLanguage(uuid, getFallbackLanguage());
            return getFallbackLanguage();
        }
        return playerLanguages.get(uuid.toString());
    }

    @SneakyThrows
    @Override
    public void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        Writer fileWriter = new FileWriter(file);
        gson.toJson(playerLanguages, fileWriter);
        fileWriter.flush();
        fileWriter.close();
        super.save();
    }
}
