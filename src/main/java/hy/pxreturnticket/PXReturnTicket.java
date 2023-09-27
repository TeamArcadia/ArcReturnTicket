package hy.pxreturnticket;

import hy.pxreturnticket.command.ReturnTicketCmd;
import hy.pxreturnticket.command.tabcomplete.ReturnTicketTab;
import hy.pxreturnticket.file.DataFile;
import hy.pxreturnticket.listener.TicketClickListener;
import hy.pxreturnticket.message.MessageConfig;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PXReturnTicket extends JavaPlugin {

    private static  PXReturnTicket instance;

    @Override
    public void onEnable() {
        instance = this;
        /*--------------- CONFIG ---------------*/
        saveDefaultConfig();
        DataFile.setup();
        MessageConfig.setup();

        /* --------------- COMMAND ---------------*/
        PluginCommand returnTicketCmd = getCommand("rt");
        Objects.requireNonNull(returnTicketCmd).setExecutor(new ReturnTicketCmd(this));
        returnTicketCmd.setTabCompleter(new ReturnTicketTab());

        /* --------------- LISTENER ---------------*/
        getServer().getPluginManager().registerEvents(new TicketClickListener(this), this);


    }

  public static PXReturnTicket getInstance() {
        return instance;
    }
}
