package hy.pxmegaphone.command;

import hy.pxmegaphone.PXMegaPhone;
import hy.pxmegaphone.message.Message;
import hy.pxmegaphone.message.MessageKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class MegaPhoneCmd implements CommandExecutor {

    private final JavaPlugin plugin;

    public MegaPhoneCmd(PXMegaPhone plugin) {
        this.plugin = plugin;
    }


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Message msgData  = Message.getInstance();

        if (!(sender instanceof Player)) {
            sender.sendMessage(msgData.getMessage(MessageKey.PLAYER_ONLY));
            return false;
        }

        Player player = (Player) sender;


        if (args.length == 0) {
            player.sendMessage(msgData.getMessage(MessageKey.WRONG_COMMAND));
            return false;
        }

        if (player.isOp()) {
            switch (args[0]) {
                case "리로드", "reload" -> {
                    if (!PermissionValidator.hasPermission(player, "reload")) return false;

                    plugin.reloadConfig();
                    DataFile.reload();
                    MessageConfig.reload();

                    player.sendMessage(msgData.getMessage(MessageKey.RELOAD_CONFIG));
                }

}
