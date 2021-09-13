package com.coadon.almpp.almpp;

import com.coadon.almpp.almpp.commands.*;
import com.coadon.almpp.almpp.listeners.PlayerLoginListeners;
import com.coadon.almpp.almpp.system.ComponentGenerator;
import com.coadon.almpp.almpp.system.IComponentGenerator;
import com.coadon.almpp.almpp.system.IPunishmentExecutor;
import com.coadon.almpp.almpp.system.PunishmentExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.Objects;

public final class ALMPP extends JavaPlugin implements IALMPP{
    private final Logger logger = getSLF4JLogger();

    public final String DEFAULT_PUNISH_REASON = "The Ban Hammer had spoken.";
    private boolean willBroadcastBan = true;

    private final IComponentGenerator componentGenerator = new ComponentGenerator();
    private final IPunishmentExecutor punishmentExecutor = new PunishmentExecutor(this);

    @Override
    public void onEnable() {
        logger.info("Advanced Lightweight Minecraft Punish Plugin");
        logger.info("Version ALMPP " + getDescription().getVersion());

        registerAllCommandsAndListeners();
    }

    @Override
    public void onDisable() {

    }

    private void registerAllCommandsAndListeners() {
        // Command registries.
        registerCommand("kick", new Commandkick(this));
        registerCommand("ban", new Commandban(this));
        registerCommand("kickall", new Commandkickall(this));
        registerCommand("afkkick", new Commandafkkick(this));
        registerCommand("warn", new Commandwarn(this));
        registerCommand("notice", new Commandnotice(this));
        registerCommand("brdcstban", new Commandbrdcstban(this));
        registerCommand("tempban", new Commandtempban(this));

        // Event Listener Registries.
        registerListeners(new PlayerLoginListeners(this));
    }

    @Override
    public void registerCommand(String commandLabel, CommandExecutor commandExe) {
        Objects.requireNonNull(getCommand(commandLabel)).setExecutor(commandExe);
    }

    @Override
    public void registerListeners(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public IComponentGenerator getFormatter() {
        return componentGenerator;
    }

    @Override
    public IPunishmentExecutor getPunisher() {
        return punishmentExecutor;
    }

    @Override
    public boolean willBroadcastBan() {
        return willBroadcastBan;
    }

    @Override
    public void setWillBroadcastBan(boolean bool) {
        this.willBroadcastBan = bool;
    }
}
