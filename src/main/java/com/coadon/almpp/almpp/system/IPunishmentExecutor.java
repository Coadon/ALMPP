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

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface IPunishmentExecutor {

    void kickPlayer(final @NotNull Player player, final @NotNull String reason);

    void afkKickPlayer(final @NotNull Player player);

    void permBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source);

    void tempBanPlayer(final @NotNull Player player, final @NotNull String reason, final @NotNull String source, final @NotNull Date expires);

    void kickAllPlayer(final @NotNull String reason);
}
