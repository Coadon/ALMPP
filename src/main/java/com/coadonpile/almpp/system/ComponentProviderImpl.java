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

package com.coadonpile.almpp.system;

import com.coadonpile.almpp.ALMPP;
import com.coadonpile.almpp.config.ConfigOptions;
import com.coadonpile.almpp.utils.PluginConfigUtil;
import com.coadonpile.almpp.utils.DurationUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public final class ComponentProviderImpl implements ComponentProvider {
    private final PluginConfigUtil cfg;
    private final MiniMessage mm;

    public ComponentProviderImpl(ALMPP plugin) {
        this.mm = MiniMessage.miniMessage();
        this.cfg = plugin.getConfigHandler();
    }

    @Override
    public @NotNull Component generateKickScreen(@NotNull String reason, @NotNull Date date) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_REMOVAL).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        return mm.deserialize(source);
    }

    @Override
    public @NotNull Component generateKickPermNameBanScreen(@NotNull String reason, @NotNull Date date) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_PERM_NAME_BAN).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        return mm.deserialize(source);
    }

    @Override
    public @NotNull Component generateKickTempNameBanScreen(@NotNull String reason, @NotNull Date date, @NotNull Date expiry) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_TEMP_NAME_BAN).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        source = source.replaceAll("\\[expiry]", expiry.toString());
        source = source.replaceAll("\\[duration]", DurationUtil.compileDurationString(expiry));
        return mm.deserialize(source);
    }

    @Override
    public @NotNull Component generateKickPermIpBanScreen(@NotNull String reason, @NotNull Date date) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_PERM_IP_BAN).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        return mm.deserialize(source);
    }

    @Override
    public @NotNull Component generateKickTempIpBanScreen(@NotNull String reason, @NotNull Date date, @NotNull Date expiry) {
        String source = StringUtils.join(cfg.getStringList(ConfigOptions.SCREEN_TEMP_IP_BAN).toArray(), "\n");
        source = source.replaceAll("\\[reason]", reason);
        source = source.replaceAll("\\[date]", date.toString());
        source = source.replaceAll("\\[expiry]", expiry.toString());
        source = source.replaceAll("\\[duration]", DurationUtil.compileDurationString(expiry));
        return mm.deserialize(source);
    }

    @Override
    public @Nullable Component getBanAnnouncementMessage(@NotNull String targetName) {
        // Check if the option is left empty, if it is, return null
        if (cfg.getStringList(ConfigOptions.ANNOUNCE_BAN).isEmpty())
            return null;

        String source = StringUtils.join(cfg.getStringList(ConfigOptions.ANNOUNCE_BAN).toArray(), "\n");
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
