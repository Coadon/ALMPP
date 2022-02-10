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

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface ComponentProvider {

    @NotNull Component generateKickMessage(final @NotNull String reason, final @NotNull String date);

    @NotNull Component generateKickPermBanMessage(final @NotNull String reason, final @NotNull String date);

    @NotNull Component generateKickTempBanMessage(final @NotNull String reason, final @NotNull String date, final @NotNull String expires);

    @NotNull String getTerminationAnnouncementMessage(final @NotNull String targetName);

    @NotNull String getRemovalAnnouncementMessage(final @NotNull String targetName);
}
