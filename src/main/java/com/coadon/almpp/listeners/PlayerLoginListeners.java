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

package com.coadon.almpp.listeners;

import com.coadon.almpp.ALMPP;
import com.coadon.almpp.config.ConfigOptions;
import com.coadon.almpp.utils.DurationUtil;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class PlayerLoginListeners extends PluginEventListener {

    public PlayerLoginListeners(ALMPP plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(PlayerLoginEvent event) {
        // Check if the player is banned
        if (event.getResult() != PlayerLoginEvent.Result.KICK_BANNED)
            return;

        // Ban entry initialization
        BanEntry banEntry;

        // Get the ban entry by name (uuid)
        banEntry = plugin.getServer().getBanList(BanList.Type.NAME).getBanEntry(event.getPlayer().getName());

        // Check if a name ban entry exist for the player. If not, then the banned player is IP banned.
        if (banEntry != null) {
            // Get the ban reason
            String banReason;
            if (banEntry.getReason() == null)
                banReason = cfg.getString(ConfigOptions.DEFAULT_PUNISH_REASON);
            else
                banReason = banEntry.getReason();

            // Get the ban expiry
            final Date banExpiry = banEntry.getExpiration();
            if (banExpiry == null) {
                // Ban is permanent
                event.kickMessage(
                        plugin.getComponentProvider().generateKickPermNameBanScreen(
                                banReason, banEntry.getCreated()));
            } else {
                // Ban is temporary
                event.kickMessage(
                        plugin.getComponentProvider().generateKickTempNameBanScreen(
                                banReason, banEntry.getCreated(), banEntry.getExpiration()));
            }
        } else {
            // Player is IP banned, switch to IP ban entry.
            banEntry = Objects.requireNonNull(
                    plugin.getServer().getBanList(BanList.Type.IP).getBanEntry(event.getAddress().getHostAddress()),
                    "Ban entry is null!");
            // No ban entry for a banned player. Weird. ¯\_(ツ)_/¯

            // Get the ban reason
            String banReason;
            if (banEntry.getReason() == null)
                banReason = cfg.getString(ConfigOptions.DEFAULT_PUNISH_REASON);
            else
                banReason = banEntry.getReason();

            // Get the ban expiry
            final Date banExpiry = banEntry.getExpiration();
            if (banExpiry == null) {
                // Ban is permanent
                event.kickMessage(
                        plugin.getComponentProvider().generateKickPermIpBanScreen(
                                banReason, banEntry.getCreated()));
            } else {
                // Ban is temporary
                event.kickMessage(
                        plugin.getComponentProvider().generateKickTempIpBanScreen(
                                banReason, banEntry.getCreated(), banEntry.getExpiration()));
            }
        }
    }
}
