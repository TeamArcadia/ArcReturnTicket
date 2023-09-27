    package hy.pxreturnticket.vaild;

    import hy.pxreturnticket.message.MessageContent;
    import hy.pxreturnticket.message.MessageType;
    import org.bukkit.entity.Player;

    public class PermissionValidator {

        public static Boolean hasPermission(Player player, String permission) {
            MessageContent msgContent = MessageContent.getInstance();
             if (player.hasPermission("px.returnticket." + permission)) {
                return true;
             } else {
                 player.sendMessage(msgContent.getMessage(MessageType.ERROR, "no_permission"));
                 return false;
             }
        }
    }
