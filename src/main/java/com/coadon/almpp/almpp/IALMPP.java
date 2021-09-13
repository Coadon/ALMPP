package com.coadon.almpp.almpp;

import com.coadon.almpp.almpp.system.IComponentGenerator;
import com.coadon.almpp.almpp.system.IPunishmentExecutor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public interface IALMPP {

    void registerCommand(final String commandLabel, final CommandExecutor commandExe);

    void registerListeners(final Listener listener);

    IComponentGenerator getFormatter();

    IPunishmentExecutor getPunisher();

    boolean willBroadcastBan();

    void setWillBroadcastBan(boolean bool);
}
