package hy.pxreturnticket.listener;

import hy.pxreturnticket.PXReturnTicket;
import hy.pxreturnticket.file.DataFile;
import hy.pxreturnticket.message.MessageContent;
import hy.pxreturnticket.message.MessageType;
import hy.pxreturnticket.vaild.ItemValidator;
import hy.pxreturnticket.vaild.LocChange;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TicketClickListener implements Listener {
    private final JavaPlugin plugin;
    private FileConfiguration dataConfig;
    MessageContent msgContent = MessageContent.getInstance();

    public TicketClickListener(PXReturnTicket plugin) {
        this.plugin = plugin;
        this.dataConfig = DataFile.get();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack == null || itemStack.getType().equals(Material.AIR)) return;

        String validItemKey = ItemValidator.getValidItemKey(itemStack);

        if (validItemKey != null) {
            Location tpLocation = dataConfig.getLocation(validItemKey + ".location");
            int cooltime = dataConfig.getInt(validItemKey + ".cooltime") * 20;

            player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "click_ticket"));

            new BukkitRunnable() {
                int ticksPassed = 0;
                LocChange locChange = new LocChange(player);
                ItemStack originalItem = player.getInventory().getItemInMainHand();

                @Override
                public void run() {
                    ItemStack currentItem = player.getInventory().getItemInMainHand();

                    if (locChange.hasMoved()) {
                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "player_moved"));
                        this.cancel();

                    } else if (!originalItem.equals(currentItem)) {
                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "player_notinhand"));
                        this.cancel();

                    } else if (ticksPassed >= cooltime) {
                        player.teleport(tpLocation);

                        if (originalItem.getAmount() > 1) {
                            originalItem.setAmount(originalItem.getAmount() - 1);
                        } else {
                            player.getInventory().remove(originalItem);
                        }

                        player.sendMessage(msgContent.getMessage(MessageType.RETURNTICKET, "used_ticket"));
                        this.cancel();
                    }
                    ticksPassed++;
                }
            }.runTaskTimer(plugin, 0L, 1L);
        }
    }
}