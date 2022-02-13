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

package com.coadon.almpp.almpp.system;

import com.coadon.almpp.almpp.ALMPP;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Date;

public final class BanManagerImpl implements BanManager {
    private final ALMPP plugin;
    private final Logger logger;
    private final ComponentProvider formatter;

    public BanManagerImpl(ALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.formatter = plugin.getComponentProvider();
    }

    /**
     * Kicks a specified player from the server.
     *
     * @param player the player to be punished
     * @param reason the reason to be punished
     * @param broadcast whether to broadcast punishment
     */
    @Override
    public void kickPlayer(@NotNull Player player, @NotNull String reason, boolean broadcast) {
        if (broadcast)
            broadcastRemoval(player);

        player.kick(formatter.generateKickMessage(reason, new Date().toString()));
        logger.info("Kicked " + player.getName() + " from the server");
    }

    /**
     * Permanently bans a specified player from the server.
     *
     * @param player the player to be punished
     * @param reason the reason to be punished
     * @param source the operator
     * @param broadcast whether to broadcast punishment
     */
    @Override
    public void permBanPlayer(@NotNull Player player, @NotNull String reason, @NotNull String source, boolean broadcast) {
        if (broadcast)
            broadcastTermination(player);

        player.kick(formatter.generateKickPermBanMessage(reason, new Date().toString()));
        player.banPlayer(reason, source);
        logger.info("Permanently banned " + player.getName() + " from the server");
    }

    /**
     * Temporarily bans a specified player from the server.
     *
     * @param player the player to be punished
     * @param reason the reason to be punished
     * @param source the operator
     * @param expires the expiration date
     * @param broadcast whether to broadcast punishment
     */
    @Override
    public void tempBanPlayer(@NotNull Player player, @NotNull String reason, @NotNull String source, @NotNull Date expires, boolean broadcast) {
        if (broadcast)
            broadcastTermination(player);

        player.kick(formatter.generateKickTempBanMessage(reason, new Date().toString(), expires.toString()));
        player.banPlayer(reason, expires, source);
        logger.info("Temporarily banned " + player.getName() + " from the server");
    }

    /**
     * Kicks all online players from the server.
     *
     * @param reason the reason to be punished
     */
    @Override
    public void kickAllPlayer(@NotNull String reason) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.kick(formatter.generateKickMessage(reason, new Date().toString())));
        logger.info("Kicked everyone from the server");
    }

    @Override
    public void pardon(@NotNull String player) {
        plugin.getServer().getBanList(BanList.Type.NAME).pardon(player);
    }

    @Override
    public boolean isBanned(@NotNull String player) {
        return plugin.getServer().getBanList(BanList.Type.NAME).isBanned(player);
    }

    /**
     * Broadcast the server a player is terminated.
     *
     * @param target the player to broadcast
     */
    private void broadcastTermination(Player target) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.sendMessage(formatter.getTerminationAnnouncementMessage(target.getName()))
        );
        Bukkit.getConsoleSender().sendMessage(formatter.getTerminationAnnouncementMessage(target.getName()));
    }

    /**
     * Broadcast the server a player is removed.
     *
     * @param target the player to broadcast
     */
    private void broadcastRemoval(Player target) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.sendMessage(formatter.getRemovalAnnouncementMessage(target.getName()))
        );
        Bukkit.getConsoleSender().sendMessage(formatter.getRemovalAnnouncementMessage(target.getName()));
    }
}
