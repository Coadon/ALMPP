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

package com.coadon.almpp.services;

import com.coadon.almpp.ALMPPInterface;
import com.coadon.almpp.config.ConfigHandler;
import com.coadon.almpp.config.ConfigOptions;
import net.kyori.adventure.text.Component;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class BanManagerImpl implements BanManager {
    private final ALMPPInterface plugin;
    private final ComponentProvider formatter;
    private final ConfigHandler cfg;

    public BanManagerImpl(ALMPPInterface plugin) {
        this.plugin = plugin;
        this.formatter = plugin.getComponentProvider();
        this.cfg = plugin.getConfigHandler();
    }

    @Override
    public void kickPlayer(@NotNull Player player, @NotNull String reason, boolean broadcast) {
        // Broadcast if punish announcement is enabled
        if (broadcast)
            broadcastRemoval(player);

        // Execute
        player.kick(formatter.generateKickMessage(reason, new Date()));

        // Log
        logToConsole("Kicked " + player.getName() + " from the server.");
    }

    @Override
    public void permBanPlayer(@NotNull Player player, @NotNull String reason, @NotNull String source, boolean broadcast) {
        // Broadcast if punish announcement is enabled
        if (broadcast)
            broadcastTermination(player);

        // Execute
        player.kick(formatter.generateKickPermBanMessage(reason, new Date()));
        player.banPlayer(reason, source);

        // Log
        logToConsole("Permanently banned " + player.getName() + " from the server.");
    }

    @Override
    public void tempBanPlayer(@NotNull Player player, @NotNull String reason, @NotNull String source, @NotNull Date expires, boolean broadcast) {
        // Broadcast if punish announcement is enabled
        if (broadcast)
            broadcastTermination(player);

        // Execute
        player.kick(formatter.generateKickTempBanMessage(reason, new Date(), expires));
        player.banPlayer(reason, expires, source);

        // Log
        logToConsole("Temporarily banned " + player.getName() + " from the server.");
    }

    @Override
    public void kickAllPlayer(@NotNull String reason) {
        // Execute for each player online
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.kick(formatter.generateKickMessage(reason, new Date())));

        // Log
        logToConsole("Kicked everyone from the server.");
    }

    @Override
    public void pardon(@NotNull String player) {
        plugin.getServer().getBanList(BanList.Type.NAME).pardon(player);
    }

    @Override
    public boolean isBanned(@NotNull String player) {
        return plugin.getServer().getBanList(BanList.Type.NAME).isBanned(player);
    }

    private void broadcastTermination(Player target) {
        Component msg = formatter.getTerminationAnnouncementMessage(target.getName());

        if (msg == null) {
            // Disabled
            return;
        }

        // Send a message to every player online
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.sendMessage(msg)
        );
    }

    private void broadcastRemoval(Player target) {
        Component msg = formatter.getRemovalAnnouncementMessage(target.getName());

        if (msg == null) {
            // Disabled
            return;
        }

        // Send a message to every player online
        plugin.getServer().getOnlinePlayers().forEach(
                player -> player.sendMessage(msg)
        );
    }

    private void logToConsole(String message) {
        if (cfg.getBoolean(ConfigOptions.ENABLE_LOGGING))
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + message);
    }
}
