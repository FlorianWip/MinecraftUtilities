package de.flammenfuchs.utilities.platform.common.language;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Getter
public abstract class LanguageProvider {

    @Getter @Setter
    private static LanguageProvider lang;
    private Map<String, Map<String, String>> cachedLanguages;
    private final String fallbackLanguage;
    private String directory;
    private String fileName;

    public static LanguageProvider get() {return lang;}

    public abstract void setPlayerLanguage(UUID uuid, String newLanguage);

    public abstract String getPlayerLanguage(UUID uuid);

    private File jsonFile;

    @SneakyThrows
    public LanguageProvider(String directory, String fileName, String fallbackLanguage) {
        this.fallbackLanguage = fallbackLanguage;
        this.directory = directory;
        this.fileName = fileName;
        File directoryFile = new File(directory);
        File file = new File(directory, fileName);

        if (file.exists()) {
            Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            cachedLanguages = new GsonBuilder().disableHtmlEscaping().create().fromJson(new JsonReader(reader), HashMap.class);
            if (cachedLanguages == null) {
                Logger.getLogger("LanguageProvider").warning("Unable to read messages file! Copying file to old_" + file.getName() + "!");
                Files.copy(Paths.get(file.getPath()), Paths.get(new File(this.directory, "old_" + file.getName()).getPath()));
                cachedLanguages = new HashMap<>();
            }
        } else {
            cachedLanguages = new HashMap<>();
            directoryFile.mkdirs();
            file.createNewFile();
        }
        this.jsonFile = file;
    }

    public void registerText(String lang, String key, String text) {
        if (!cachedLanguages.containsKey(lang.toLowerCase())) {
            cachedLanguages.put(lang.toLowerCase(), new HashMap<>());
        }
        if (!cachedLanguages.get(lang.toLowerCase()).containsKey(key.toLowerCase())) {
            cachedLanguages.get(lang.toLowerCase()).put(key.toLowerCase(), text);
        }
    }

    public void registerSection(LanguageSection section) {
        String lang = section.getName().toLowerCase();
        if (!cachedLanguages.containsKey(lang.toLowerCase())) {
            cachedLanguages.put(lang.toLowerCase(), new HashMap<>());
        }
        section.getTexts().entrySet().forEach(entry ->
                registerText(lang, entry.getKey(), entry.getValue()));
    }

    @SneakyThrows
    public void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        Writer fileWriter = new OutputStreamWriter(new FileOutputStream(jsonFile), "UTF-8");
        gson.toJson(cachedLanguages, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    public String getText(String lang, String key, Object... placeholders) {
        if (!cachedLanguages.containsKey(lang.toLowerCase())) {
            lang = fallbackLanguage;
            if (!cachedLanguages.containsKey(this.fallbackLanguage.toLowerCase())) {
                return "null";
            }
        }
        String text = cachedLanguages.get(lang.toLowerCase()).get(key.toLowerCase());
        for (int i = 0; i < placeholders.length; i++) {
            text = text.replaceAll("\\{" + i + "}", placeholders[i].toString());
        }
        return text;
    }

    public String getText(UUID uuid, String key, Object... placeholders) {
        return getText(getPlayerLanguage(uuid), key, placeholders);
    }

    @Nullable
    public Map<String, String> getLanguage(String lang) {
        if (!cachedLanguages.containsKey(lang.toLowerCase())) {
            return null;
        }
        return ImmutableMap.copyOf(cachedLanguages.get(lang.toLowerCase()));
    }

    public Map<String, Map<String, String>> getCachedLanguages() {
        return ImmutableMap.copyOf(cachedLanguages);
    }

    public boolean existsLanguage(String lang) {
        return cachedLanguages.containsKey(lang.toLowerCase());
    }

    public void copyLanguage(String lang, String newLang) throws IllegalArgumentException {
        Preconditions.checkArgument(existsLanguage(lang));
        cachedLanguages.put(newLang.toLowerCase(), cachedLanguages.get(lang.toLowerCase()));
    }

    public void renameLanguage(String oldName, String newName) throws IllegalArgumentException {
        copyLanguage(oldName, newName);
        cachedLanguages.remove(oldName.toLowerCase());
    }

    public void updateText(String lang, String key, String text) {
        if (!cachedLanguages.containsKey(lang.toLowerCase())) {
            cachedLanguages.put(lang.toLowerCase(), new HashMap<>());
        }
        cachedLanguages.get(lang.toLowerCase()).put(key.toLowerCase(), text);
    }

    public void deleteLanguage(String lang) throws IllegalArgumentException{
        Preconditions.checkArgument(existsLanguage(lang));
        cachedLanguages.remove(lang.toLowerCase());
    }
}
