package hy.pxreturnticket.message;

import hy.pxreturnticket.util.ColorCode;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class MessageContent {
    private static MessageContent instance;
    private Map<MessageType, Map<String, String>> msgMap = new HashMap<>();

    FileConfiguration msgConfig = MessageConfig.getMessageConfig();

    private MessageContent() {
    }

    public static MessageContent getInstance() {
        if (instance == null) instance = new MessageContent();
        return instance;
    }

    public String getPrefix() {
        return msgConfig.getString("normalMessage.prefix");
    }

    public String getMessage(MessageType type, String key) {
        ConfigurationSection section = msgConfig.getConfigurationSection(String.valueOf(type.getKey()));

        String prefix = getPrefix();
        String message = section.getString(key);

        return ColorCode.colorCodes(prefix + message);
    }
}