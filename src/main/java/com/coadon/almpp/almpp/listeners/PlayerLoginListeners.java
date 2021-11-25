/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2021  Coadon
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
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListeners extends PluginEventListener {
    public PlayerLoginListeners(ALMPP plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(PlayerLoginEvent event) {
        BanList banList = getBanList();
        if (banList.isBanned(event.getPlayer().getName())) {
            BanEntry entry = banList.getBanEntry(event.getPlayer().getName());
            if (entry == null) {
                NullPointerException exception = new NullPointerException("Ban entry is null.");
                logger.error("An NullPointerException occurred in " + getClass().getName() + ": " + exception.getMessage());
                throw exception;
            }

            String banReason;
            if (entry.getReason() == null) {
                banReason = plugin.DEFAULT_PUNISH_REASON;
                // This condition is currently impossible to be met because the ban reason is automatically set,
                // only in situation when the ban is triggered by vanilla Minecraft or other plugin(s).
                // We will be working on a feature that supports no reason punishment.
            } else {
                banReason = entry.getReason();
            }
            if (entry.getExpiration() == null) {
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, plugin.getFormatter().generateKickPermBanMessage(
                        banReason, entry.getCreated().toString()));
            } else {
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, plugin.getFormatter().generateKickTempBanMessage(
                        banReason, entry.getCreated().toString(), entry.getExpiration().toString()));
            }
        }
    }
}
