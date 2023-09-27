package hy.pxreturnticket.message;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageConfig {

    private static File messageFile;
    private static FileConfiguration messageConfig;


    public static void setup() {
        if (messageFile == null) {
            messageFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PXReturnTicket").getDataFolder(), "message.yml");
        }

        if (!messageFile.exists()) {
            Bukkit.getServer().getPluginManager().getPlugin("PXReturnTicket").saveResource("message.yml", false);
        }

        messageConfig = YamlConfiguration.loadConfiguration(messageFile);
    }



    public static FileConfiguration getMessageConfig() {
        return YamlConfiguration.loadConfiguration(messageFile);
    }

    public static void reload() {
        try {
            if (messageFile != null) {
                messageConfig = YamlConfiguration.loadConfiguration(messageFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
