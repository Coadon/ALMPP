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

package net.coadonpile.almpp;

import net.coadonpile.almpp.commands.*;
import net.coadonpile.almpp.utils.PluginConfigUtil;
import net.coadonpile.almpp.listeners.PlayerLoginListeners;
import net.coadonpile.almpp.system.BanManager;
import net.coadonpile.almpp.system.BanManagerImpl;
import net.coadonpile.almpp.system.ComponentProvider;
import net.coadonpile.almpp.system.ComponentProviderImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.util.Objects;

public final class ALMPP extends JavaPlugin {
    private static ALMPP instance;

    private final Logger logger = getSLF4JLogger();
    private final String VERSION = getDescription().getVersion();

    private PluginConfigUtil pluginConfig;
    private ComponentProvider componentProvider;
    private BanManager banManager;

    @Override
    public void onEnable() {
        instance = this;

        $().info("Advanced Lightweight Minecraft Punishment Plugin");
        $().info("Version ALMPP " + VERSION);

        // Save config
        saveDefaultConfig();

        pluginConfig = new PluginConfigUtil(getConfig());
        componentProvider = new ComponentProviderImpl();
        banManager = new BanManagerImpl();

        registerAllCommandsAndListeners();
    }

    @Override
    public void onDisable() {
        logger.info("Goodbye!");
    }

    private void registerAllCommandsAndListeners() {
        // Command registries.
        registerCommand("kick", new Commandkick());
        registerCommand("ban", new Commandban());
        registerCommand("banip", new Commandbanip());
        registerCommand("kickall", new Commandkickall());
        registerCommand("unban", new Commandunban());
        registerCommand("tempban", new Commandtempban());
        registerCommand("tempbanip", new Commandtempbanip());
        registerCommand("unbanip", new Commandunbanip());

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

    public PluginConfigUtil getPluginConfig() {
        return pluginConfig;
    }

    public String getVersion() {
        return VERSION;
    }

    public static ALMPP getInstance() {
        return instance;
    }

    /**
     * Get Logger
     * @return Plugin logger
     */
    public static Logger $() {
        return getInstance().logger;
    }
}
