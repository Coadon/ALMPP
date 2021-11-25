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

package com.coadon.almpp.almpp.system;

import com.coadon.almpp.almpp.ALMPP;
import com.coadon.almpp.almpp.utils.StringCombiner;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public final class ComponentProvider implements IComponentProvider {
    private final ALMPP plugin;

    public ComponentProvider(ALMPP plugin) {
        this.plugin = plugin;
    }

    /**
     * Generates a kick message component with provided arguments.
     *
     * @param reason The text to be displayed as the reason.
     * @param date The text to be displayed as the date.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull Component generateKickMessage(final @NotNull String reason, final @NotNull String date) {
        return Component.text("")
                .append(Component.text("You are kicked from this server!\n\n").color(NamedTextColor.RED))
                .append(Component.text("Reason: ").color(NamedTextColor.GRAY))
                .append(Component.text(reason + "\n").color(NamedTextColor.WHITE))
                .append(Component.text("Date: ").color(NamedTextColor.GRAY))
                .append(Component.text(date).color(NamedTextColor.WHITE));
    }

    /**
     * Generates an AFK kick message component with provided arguments.
     *
     * @param date The text to be displayed as the date.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull Component generateAfkKickMessage(final @NotNull String date) {
        return Component.text("")
                .append(Component.text("You are AFK kicked from this server!\n\n").color(NamedTextColor.GOLD))
                .append(Component.text("Date: ").color(NamedTextColor.GRAY))
                .append(Component.text(date).color(NamedTextColor.WHITE));
    }

    /**
     * Generates a permanently banned message component with provided arguments.
     *
     * @param reason The text to be displayed as the reason.
     * @param date The text to be displayed as the date.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull Component generateKickPermBanMessage(final @NotNull String reason, final @NotNull String date) {
        return Component.text("")
                .append(Component.text("You are permanently banned from this server!\n\n").color(NamedTextColor.RED))
                .append(Component.text("Reason: ").color(NamedTextColor.GRAY))
                .append(Component.text(reason + "\n").color(NamedTextColor.WHITE))
                .append(Component.text("Date: ").color(NamedTextColor.GRAY))
                .append(Component.text(date + "\n").color(NamedTextColor.WHITE));
    }

    /**
     * Generates a temporarily banned message component with provided arguments.
     *
     * @param reason The text to be displayed as the reason.
     * @param date The text to be displayed as the date.
     * @param expires The text to be displayed as the expires date.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull Component generateKickTempBanMessage(final @NotNull String reason, final @NotNull String date, final @NotNull String expires) {
        return Component.text("")
                .append(Component.text("You are temporarily banned until ").color(NamedTextColor.RED))
                .append(Component.text(expires).color(NamedTextColor.WHITE))
                .append(Component.text(" from this server!\n\n").color(NamedTextColor.RED))
                .append(Component.text("Reason: ").color(NamedTextColor.GRAY))
                .append(Component.text(reason + "\n").color(NamedTextColor.WHITE))
                .append(Component.text("Date: ").color(NamedTextColor.GRAY))
                .append(Component.text(date + "\n").color(NamedTextColor.WHITE));

    }

    /**
     * Generates a player termination announcement message component.
     *
     * @param targetName The name to be displayed as the target.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull String getTerminationAnnouncementMessage(final @NotNull String targetName) {
        String output = StringCombiner.combine(plugin.getConfig().getStringList("broadcast-ban-message.termination").toArray(), "\n");
        output = output.replaceAll("\\[player]", targetName);
        output = ChatColor.translateAlternateColorCodes('&', output);
        return output;
    }

    /**
     * Generates a player AFK kicked announcement message component.
     *
     * @param targetName The name to be displayed as the target.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull String getAfkKickAnnouncementMessage(final @NotNull String targetName) {
        String output = StringCombiner.combine(plugin.getConfig().getStringList("broadcast-ban-message.afk-kick").toArray(), "\n");
        output = output.replaceAll("\\[player]", targetName);
        output = ChatColor.translateAlternateColorCodes('&', output);
        return output;
    }
}
