package hy.pxreturnticket.vaild;

import hy.pxreturnticket.file.DataFile;
import hy.pxreturnticket.util.ColorCode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemValidator {
    public static FileConfiguration dataConfig = DataFile.get();

    public static String getValidItemKey(ItemStack item) {
        if (item != null) {
            for (String key : dataConfig.getKeys(false)) {
                ItemStack savedItem = dataConfig.getItemStack(key + ".item");
                if (savedItem == null || !savedItem.isSimilar(item)) {
                    continue;
                }

                return key;
            }
        }
        return null;
    }

    public static boolean isItemExists(ItemStack item) {
    for (String key : dataConfig.getKeys(false)) {
        ItemStack savedItem = dataConfig.getItemStack(key + ".item");
        if (savedItem != null && savedItem.isSimilar(item)) {
            return true;
        }
    }
    return false;
}
}