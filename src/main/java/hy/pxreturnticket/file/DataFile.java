package hy.pxreturnticket.file;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private static File file;
    private static FileConfiguration dataFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("PXReturnTicket").getDataFolder(), "data.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return dataFile;
    }

    public static void save() {
        try {
            dataFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        try {
            if (file != null) {
                dataFile = YamlConfiguration.loadConfiguration(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
