package hy.pxreturnticket.command;

import hy.pxreturnticket.PXReturnTicket;
import hy.pxreturnticket.file.DataFile;
import hy.pxreturnticket.message.MessageConfig;
import hy.pxreturnticket.message.MessageContent;
import hy.pxreturnticket.message.MessageType;
import hy.pxreturnticket.vaild.ItemValidator;
import hy.pxreturnticket.vaild.PermissionValidator;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public class ReturnTicketCmd implements CommandExecutor {

    private final JavaPlugin plugin;

    public ReturnTicketCmd(PXReturnTicket plugin) {
        this.plugin = plugin;
    }


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        MessageContent msgContent = MessageContent.getInstance();
        if (!(sender instanceof Player)) {
            sender.sendMessage(msgContent.getMessage(MessageType.ERROR, "player_only"));
            return false;
        }

        Player player = (Player) sender;


        if (args.length == 0) {
            player.sendMessage(msgContent.getMessage(MessageType.ERROR, "wrong_command"));
            return false;
        }

        if (player.isOp()) {
            switch (args[0]) {
                case "리로드", "reload" -> {
                    if (!PermissionValidator.hasPermission(player, "reload")) return false;

                    plugin.reloadConfig();
                    DataFile.reload();
                    MessageConfig.reload();

                    player.sendMessage(msgContent.getMessage(MessageType.NORMAL, "reload_config"));
                }
                case "생성", "create" -> {
                    if (!PermissionValidator.hasPermission(player, "create")) return false;
                    if (args.length != 3) {
                        player.sendMessage(msgContent.getMessage(MessageType.ERROR, "wrong_command"));
                        return false;
                    }

                    String warpName = args[1];
                    int coolTime = Integer.parseInt(args[2]);
                    ItemStack itemStack = player.getItemInHand().clone();
                    itemStack.setAmount(1);
                    Location currentLoc = player.getLocation();

                    if (ItemValidator.isItemExists(itemStack)) {
                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "same_item_name"));
                        return false;
                    }


                    FileConfiguration dataConfig = DataFile.get();

                    if (dataConfig.contains(warpName)) {
                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "same_warp_name"));
                        return false;
                    }

                    ConfigurationSection dataSection = dataConfig.createSection(warpName);

                    dataSection.set("item", itemStack);
                    dataSection.set("cooltime", coolTime);
                    dataSection.set("location", currentLoc);

                    try {
                        DataFile.save();
                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "create_ticket"));

                    } catch (Exception e) {
                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "failed_create_ticket"));
                    }
                }


                case "삭제", "delete" -> {
                    if (!PermissionValidator.hasPermission(player, "delete")) return false;
                    if (args.length > 1) {
                        String warpName = args[1];

                        if (DataFile.get().contains(warpName)) {
                            DataFile.get().set(warpName, null);
                            DataFile.save();

                            player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "warp_delete"));
                        } else {
                            player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "warp_cant_find"));
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }
}