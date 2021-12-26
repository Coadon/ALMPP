/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.coadon.almpp.almpp;

import com.coadon.almpp.almpp.commands.*;
import com.coadon.almpp.almpp.listeners.PlayerLoginListeners;
import com.coadon.almpp.almpp.system.ComponentProviderImpl;
import com.coadon.almpp.almpp.system.ComponentProvider;
import com.coadon.almpp.almpp.system.PunishmentExecutor;
import com.coadon.almpp.almpp.system.PunishmentExecutorImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.Objects;

public final class ALMPP extends JavaPlugin implements IALMPP{
    private final Logger logger = getSLF4JLogger();

    public final String DEFAULT_PUNISH_REASON = getConfig().getString("default-punish-reason");
    private boolean willBroadcastBan = getConfig().getBoolean("will-broadcast-ban-by-default");

    private final ComponentProvider componentGenerator = new ComponentProviderImpl(this);
    private final PunishmentExecutor punishmentExecutor = new PunishmentExecutorImpl(this);

    @Override
    public void onEnable() {
        logger.info("Advanced Lightweight Minecraft Punish Plugin");
        logger.info("Version ALMPP " + getDescription().getVersion());

        // Save config
        saveDefaultConfig();

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
        registerCommand("almpp", new Commandalmpp(this));

        // Event Listener Registries.
        registerListeners(new PlayerLoginListeners(this));
    }

    @Override
    public void registerCommand(String commandLabel, TabExecutor commandExe) {
        Objects.requireNonNull(getCommand(commandLabel)).setExecutor(commandExe);
        Objects.requireNonNull(getCommand(commandLabel)).setTabCompleter(commandExe);
    }

    @Override
    public void registerListeners(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public ComponentProvider getFormatter() {
        return componentGenerator;
    }

    @Override
    public PunishmentExecutor getPunisher() {
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
