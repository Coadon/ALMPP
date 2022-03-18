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
import com.coadon.almpp.almpp.config.ConfigOptions;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerLoginListeners extends PluginEventListener {
    public PlayerLoginListeners(ALMPP plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(PlayerLoginEvent event) {
        BanList banList = getBanList();

        if (!(banList.isBanned(event.getPlayer().getName())))
            return;

        // Get the ban entry of the banned player.
        @NotNull BanEntry entry =
                Objects.requireNonNull(banList.getBanEntry(event.getPlayer().getName()), "Ban entry is null.");
        // No ban entry for a banned player, weird huh. ¯\_(ツ)_/¯

        String banReason;
        if (entry.getReason() == null) {
            banReason = cfg.getString(ConfigOptions.DEFAULT_PUNISH_REASON);
            // Only in situation where the ban is triggered by vanilla Minecraft or another plugin with no reason.
            // We will be working on a feature that supports no reason punishment.
        } else
            banReason = entry.getReason();

        if (entry.getExpiration() == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, plugin.getComponentProvider().generateKickPermBanMessage(
                    banReason, entry.getCreated().toString()));
        } else {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, plugin.getComponentProvider().generateKickTempBanMessage(
                    banReason, entry.getCreated().toString(), entry.getExpiration().toString()));
        }
    }
}
