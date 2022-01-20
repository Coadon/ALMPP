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

package com.coadon.almpp.almpp.listeners;

import com.coadon.almpp.almpp.ALMPP;
import com.coadon.almpp.almpp.system.ComponentProvider;
import com.coadon.almpp.almpp.config.ConfigHandler;
import com.coadon.almpp.almpp.system.PunishmentExecutor;
import org.bukkit.BanList;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public abstract class PluginEventListener implements Listener {
    protected final ALMPP plugin;
    protected final Logger logger;
    protected final ConfigHandler cfg;

    public PluginEventListener(ALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.cfg = plugin.getConfigHandler();
    }

    protected @Nullable Player getPlayer(final String playerName) {
        return plugin.getServer().getPlayer(playerName);
    }

    protected @Nullable Player getPlayer(final UUID id) {
        return plugin.getServer().getPlayer(id);
    }

    protected Collection<? extends Player> getOnlinePlayers() {
        return plugin.getServer().getOnlinePlayers();
    }

    protected Server getServer() {
        return plugin.getServer();
    }

    protected World getWorld(String name) {
        return plugin.getServer().getWorld(name);
    }

    protected List<World> getWorlds() {
        return plugin.getServer().getWorlds();
    }

    protected BukkitScheduler getBukkitScheduler() {
        return plugin.getServer().getScheduler();
    }

    protected PunishmentExecutor getPunisher() {
        return plugin.getPunisher();
    }

    protected ComponentProvider getFormatter() {
        return plugin.getFormatter();
    }

    /**
     * Get the server ban list.
     *
     * @param type The ban list type.
     * @return The server ban list.
     */
    protected BanList getBanList(BanList.Type type) {
        return plugin.getServer().getBanList(type);
    }

    /**
     * Get the name type server ban list.
     *
     * @return The name type server ban list.
     */
    protected BanList getBanList() {
        return plugin.getServer().getBanList(BanList.Type.NAME);
    }
}
