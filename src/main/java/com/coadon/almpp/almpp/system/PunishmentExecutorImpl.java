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
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Date;

public final class PunishmentExecutorImpl implements PunishmentExecutor {
    private final ALMPP plugin;
    private final Logger logger;
    private final ComponentProvider formatter;

    public PunishmentExecutorImpl(ALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.formatter = plugin.getFormatter();
    }

    /**
     * Kicks a player out of the server.
     *
     * @param player the player to be punished
     * @param reason the reason to be punished
     */
    @Override
    public void kickPlayer(final @NotNull Player player, final @NotNull String reason) {
        broadcastKick(player);
        player.kick(formatter.generateKickMessage(reason, new Date().toString()));
        logger.info("Kicked " + player.getName() + " from the server");
    }

    /**
     * AFK Kicks a player out of the server.
     *
     * @param player the player to be punished
     */
    @Override
    public void afkKickPlayer(final @NotNull Player player) {
        broadcastAfk(player);
        player.kick(formatter.generateAfkKickMessage(new Date().toString()));
        logger.info("AFK Kicked " + player.getName() + " from the server");
    }

    /**
     * Permanently bans a player out of the server
     *
     * @param player the player to be punished
     * @param reason the reason to be punished
     * @param source the operator
     */
    @Override
    public void permBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source) {
        broadcastBan(player);
        player.kick(formatter.generateKickPermBanMessage(reason, new Date().toString()));
        player.banPlayer(reason, source);
        logger.info("Permanently banned " + player.getName() + " from the server");
    }

    /**
     * Temporarily bans a player out of the server.
     *
     * @param player the player to be punished
     * @param reason the reason to be punished
     * @param source the operator
     * @param expires the expiration date
     */
    @Override
    public void tempBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source, final @NotNull Date expires) {
        broadcastBan(player);
        player.kick(formatter.generateKickTempBanMessage(reason, new Date().toString(), expires.toString()));
        player.banPlayer(reason, expires, source);
        logger.info("Temporarily banned " + player.getName() + " from the server");
    }

    /**
     * Kicks all online players out of the server.
     *
     * @param reason the reason to be punished
     */
    @Override
    public void kickAllPlayer(final @NotNull String reason) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.kick(formatter.generateKickMessage(reason, new Date().toString())));
        logger.info("Kicked everyone from the server");
    }

    /**
     * Broadcast the server a player is terminated.
     *
     * @param target the player to broadcast
     */
    private void broadcastBan(Player target) {
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
    private void broadcastKick(Player target) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.sendMessage(formatter.getRemovalAnnouncementMessage(target.getName()))
        );
        Bukkit.getConsoleSender().sendMessage(formatter.getRemovalAnnouncementMessage(target.getName()));
    }

    /**
     * Broadcast the server a player is afk kicked.
     *
     * @param target the player to broadcast
     */
    private void broadcastAfk(Player target) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.sendMessage(formatter.getAfkKickAnnouncementMessage(target.getName()))
        );
        Bukkit.getConsoleSender().sendMessage(formatter.getAfkKickAnnouncementMessage(target.getName()));
    }
}
