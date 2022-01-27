/*
 * ALMPP - The advanced lightweight punishment plugin for Minecraft servers
 * Copyright (C) 2022 Coadon
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

package com.coadon.almpp.almpp.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;

public class ConfigHandlerImpl implements ConfigHandler {
    private final String defaultPunishReason;
    private final List<String> terminationBroadcastMsg;
    private final List<String> removalBroadcastMsg;
    private final List<String> afkkickBroadcastMsg;
    private final List<String> commonPunishReasons;
    private final boolean debugMode;

    public ConfigHandlerImpl(FileConfiguration cfg, Logger logger) {
        this.defaultPunishReason = cfg.getString("default-punish-reason");
        this.terminationBroadcastMsg = cfg.getStringList("broadcast-ban-message.termination");
        this.removalBroadcastMsg = cfg.getStringList("broadcast-ban-message.removal");
        this.afkkickBroadcastMsg = cfg.getStringList("broadcast-ban-message.afk-kick");
        this.commonPunishReasons = cfg.getStringList("common-punish-reasons");
        this.debugMode = cfg.getBoolean("debug-mode");
    }

    @Override
    public @NotNull String getDefaultPunishReason() {
        return defaultPunishReason;
    }

    @Override
    public @NotNull List<String> getTerminationMessage() {
        return terminationBroadcastMsg;
    }

    @Override
    public @NotNull List<String> getRemovalMessage() {
        return removalBroadcastMsg;
    }

    @Override
    public @NotNull List<String> getAfkKickMessage() {
        return afkkickBroadcastMsg;
    }

    @Override
    public @NotNull List<String> getCommonPunishReasons() {
        return commonPunishReasons;
    }

    @Override
    public boolean isDebugMode() {
        return debugMode;
    }
}
