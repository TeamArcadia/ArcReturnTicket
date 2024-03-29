package hy.pxreturnticket.valid;

import hy.pxreturnticket.message.Message;
import hy.pxreturnticket.message.MessageKey;
import org.bukkit.entity.Player;

public class PermissionValidator {

    public static Boolean hasPermission(Player player, String permission) {
        Message msgData = Message.getInstance();
         if (player.hasPermission("arc.returnticket." + permission)) {
            return true;
         } else {
             player.sendMessage(msgData.getMessage(MessageKey.NO_PERMISSION));
             return false;
         }
    }
}
