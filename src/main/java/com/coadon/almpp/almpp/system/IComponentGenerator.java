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
import org.jetbrains.annotations.NotNull;

public interface IComponentGenerator {

    @NotNull Component generateKickMessage(final @NotNull String reason, final @NotNull String date);

    @NotNull Component generateAfkKickMessage(final @NotNull String date);

    @NotNull Component generateKickPermBanMessage(final @NotNull String reason, final @NotNull String date);

    @NotNull Component generateKickTempBanMessage(final @NotNull String reason, final @NotNull String date, final @NotNull String expires);

    @NotNull Component generateTerminationAnnouncementMessage(final @NotNull String targetName);

    @NotNull Component generateAfkKickAnnouncementMessage(final @NotNull String targetName);
}
