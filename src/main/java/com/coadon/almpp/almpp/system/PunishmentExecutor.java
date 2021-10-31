/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft
 * Copyright (C) 2021 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.coadon.almpp.almpp.system;

import com.coadon.almpp.almpp.ALMPP;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Date;

public final class PunishmentExecutor implements IPunishmentExecutor {
    private final ALMPP plugin;
    private final Logger logger;
    private final IComponentProvider formatter;

    public PunishmentExecutor(ALMPP plugin) {
        this.plugin = plugin;
        this.logger = plugin.getSLF4JLogger();
        this.formatter = plugin.getFormatter();
    }

    /**
     * Kicks a player out of the server.
     *
     * @param player The player to be punished.
     * @param reason The reason to be punished.
     */
    @Override
    public void kickPlayer(final @NotNull Player player, final @NotNull String reason) {
        broadcastBan(player);
        player.kick(formatter.generateKickMessage(reason, new Date().toString()));
        logger.info("Kicked " + player.getName() + " from the server");
    }

    /**
     * AFK Kicks a player out of the server.
     *
     * @param player The player to be punished.
     */
    @Override
    public void afkKickPlayer(final @NotNull Player player) {
        broadcastAfk(player);
        player.kick(formatter.generateAfkKickMessage(new Date().toString()));
        logger.info("AFK Kicked " + player.getName() + " from the server");
    }

    /**
     * Permanently bans a player out of the server.
     *
     * @param player The player to be punished.
     * @param reason The reason to be punished.
     * @param source The operator.
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
     * @param player The player to be punished.
     * @param reason The reason to be punished.
     * @param source The executor.
     * @param expires The expiration date.
     */
    @Override
    public void tempBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source, final @NotNull Date expires) {
        broadcastBan(player);
        player.kick(formatter.generateKickTempBanMessage(reason, new Date().toString(), expires.toString()));
        player.banPlayer(reason, expires, source);
        logger.info("Temporarily banned " + player.getName() + " from the server");
    }

    /**
     * Kicks all online players out of the server
     *
     * @param reason The reason to be punished.
     */
    @Override
    public void kickAllPlayer(final @NotNull String reason) {
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.kick(formatter.generateKickMessage(reason, new Date().toString())));
        logger.info("Kicked everyone from the server");
    }

    /**
     * Broadcast the server a player is terminated, only if BroadcastBan is enabled.
     */
    private void broadcastBan(Player target) {
        if (plugin.willBroadcastBan()) {
            plugin.getServer().getOnlinePlayers().forEach(
                    player -> player.sendMessage(formatter.getTerminationAnnouncementMessage(target.getName()))
            );
        }
    }

    /**
     * Broadcast the server a player is afk kicked, only if BroadcastBan is enabled.
     */
    private void broadcastAfk(Player target) {
        if (plugin.willBroadcastBan()) {
            plugin.getServer().getOnlinePlayers().forEach(
                    player -> player.sendMessage(formatter.getAfkKickAnnouncementMessage(target.getName()))
            );
        }
    }
}
