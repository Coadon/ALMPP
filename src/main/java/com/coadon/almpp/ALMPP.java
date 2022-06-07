/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021-2022 Coadon
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

package com.coadon.almpp;

import com.coadon.almpp.commands.*;
import com.coadon.almpp.config.PluginConfigHandler;
import com.coadon.almpp.listeners.PlayerLoginListeners;
import com.coadon.almpp.services.BanManager;
import com.coadon.almpp.services.BanManagerImpl;
import com.coadon.almpp.services.ComponentProvider;
import com.coadon.almpp.services.ComponentProviderImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.Objects;

public final class ALMPP extends JavaPlugin {
    private final Logger logger = getSLF4JLogger();
    private final String VERSION = getDescription().getVersion();

    private PluginConfigHandler configHandler;
    private ComponentProvider componentProvider;
    private BanManager banManager;

    @Override
    public void onEnable() {
        logger.info("Advanced Lightweight Minecraft Punishment Plugin");
        logger.info("Version ALMPP " + VERSION);

        // Save config
        saveDefaultConfig();

        configHandler = new PluginConfigHandler(getConfig());
        componentProvider = new ComponentProviderImpl(this);
        banManager = new BanManagerImpl(this);

        registerAllCommandsAndListeners();
    }

    @Override
    public void onDisable() {
        logger.info("Goodbye!");
    }

    private void registerAllCommandsAndListeners() {
        // Command registries.
        registerCommand("kick", new Commandkick(this));
        registerCommand("ban", new Commandban(this));
        registerCommand("kickall", new Commandkickall(this));
        registerCommand("unban", new Commandunban(this));
        registerCommand("tempban", new Commandtempban(this));

        // Event Listener Registries.
        registerListeners(new PlayerLoginListeners(this));
    }

    private void registerCommand(String commandLabel, TabExecutor commandExe) {
        try {
            Objects.requireNonNull(getCommand(commandLabel)).setExecutor(commandExe);
            Objects.requireNonNull(getCommand(commandLabel)).setTabCompleter(commandExe);
        } catch (NullPointerException e) {
            logger.error("Command '" + commandLabel + "' failed to load properly.");
        }
    }

    private void registerListeners(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public ComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public PluginConfigHandler getConfigHandler() {
        return configHandler;
    }

    public String getVersion() {
        return VERSION;
    }
}