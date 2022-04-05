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

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public interface ComponentProvider {

    @NotNull Component generateKickMessage(@NotNull String reason, @NotNull Date date);

    @NotNull Component generateKickPermBanMessage(@NotNull String reason, @NotNull Date date);

    @NotNull Component generateKickTempBanMessage(@NotNull String reason, @NotNull Date date, @NotNull Date expiry);

    @Nullable Component getTerminationAnnouncementMessage(@NotNull String targetName);

    @Nullable Component getRemovalAnnouncementMessage(@NotNull String targetName);
}
