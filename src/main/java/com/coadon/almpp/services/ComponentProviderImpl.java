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

import com.coadon.almpp.ALMPP;
import com.coadon.almpp.config.ConfigHandler;
import com.coadon.almpp.config.ConfigOptions;
import com.coadon.almpp.utils.DurationUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public final class ComponentProviderImpl implements ComponentProvider {
    private final ConfigHandler cfg;
    private final MiniMessage mm;

    public ComponentProviderImpl(ALMPP plugin) {
        this.mm = MiniMessage.miniMessage();
        this.cfg = plugin.getConfigHandler();
    }

    @Override
    public @NotNull Component generateKickMessage(@NotNull String reason, @NotNull Date date) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_REMOVAL).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        return mm.deserialize(source);
    }

    @Override
    public @NotNull Component generateKickPermBanMessage(@NotNull String reason, @NotNull Date date) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_PERM_TERM).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        return mm.deserialize(source);
    }

    @Override
    public @NotNull Component generateKickTempBanMessage(@NotNull String reason, @NotNull Date date, @NotNull Date expiry) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_TEMP_TERM).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        source = source.replaceAll("\\[expiry]", expiry.toString());
        source = source.replaceAll("\\[duration]", DurationUtil.compileDurationString(expiry));
        return mm.deserialize(source);
    }

    @Override
    public @Nullable Component getTerminationAnnouncementMessage(@NotNull String targetName) {
        // Check if the option is left empty, if it is, return null
        if (cfg.getStringList(ConfigOptions.ANNOUNCE_TERMINATION).isEmpty())
            return null;

        String source = StringUtils.join(cfg.getStringList(ConfigOptions.ANNOUNCE_TERMINATION).toArray(), "\n");
        source = source.replaceAll("\\[player]", targetName);
        return mm.deserialize(source);
    }

    @Override
    public @Nullable Component getRemovalAnnouncementMessage(@NotNull String targetName) {
        // Check if the option is left empty, if it is, return null
        if (cfg.getStringList(ConfigOptions.ANNOUNCE_REMOVAL).isEmpty())
            return null;

        String source = StringUtils.join(cfg.getStringList(ConfigOptions.ANNOUNCE_REMOVAL).toArray(), "\n");
        source = source.replaceAll("\\[player]", targetName);
        return mm.deserialize(source);
    }
}
