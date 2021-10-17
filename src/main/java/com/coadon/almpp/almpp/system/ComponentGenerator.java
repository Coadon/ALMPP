/*
 * ALMPP - a bukkit plugin
 * Copyright (C) 2021 Coadon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.coadon.almpp.almpp.system;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;

public final class ComponentGenerator implements IComponentGenerator {

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
    public @NotNull Component generateTerminationAnnouncementMessage(final @NotNull String targetName) {
        return Component.text("")
                .append(Component.text("Player ").color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .append(Component.text(targetName).color(NamedTextColor.RED).decorate(TextDecoration.ITALIC))
                .append(Component.text(" has been terminated from this server due to hacking, griefing, spamming, or abuse.\n").color(NamedTextColor.RED).decorate(TextDecoration.BOLD));
    }

    /**
     * Generates a player AFK kicked announcement message component.
     *
     * @param targetName The name to be displayed as the target.
     * @return The combined and generated component.
     */
    @Override
    public @NotNull Component generateAfkKickAnnouncementMessage(final @NotNull String targetName) {
        return Component.text("")
                .append(Component.text("Player ").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD))
                .append(Component.text(targetName).color(NamedTextColor.YELLOW).decorate(TextDecoration.ITALIC))
                .append(Component.text(" has been removed from this server due to inactivity.\n").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD));
    }
}
